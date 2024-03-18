package net.orca.oceanoverhaul.entity.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.orca.oceanoverhaul.entity.custom.OrcaEntity;

public class OrcaJumpGoal extends JumpGoal {
    private static final int[] STEPS_TO_CHECK = new int[]{0, 1, 4, 5, 6, 7};
    private final OrcaEntity orcaentity;
    private final int interval;
    private boolean breached;

    public OrcaJumpGoal(OrcaEntity pOrcaEntity, int pInterval) {
        this.orcaentity = pOrcaEntity;
        this.interval = reducedTickDelay(pInterval);
    }

    public boolean canUse() {
        if (this.orcaentity.getRandom().nextInt(this.interval) != 0) {
            return false;
        } else {
            Direction direction = this.orcaentity.getMotionDirection();
            int i = direction.getStepX();
            int j = direction.getStepZ();
            BlockPos blockpos = this.orcaentity.blockPosition();

            for(int k : STEPS_TO_CHECK) {
                if (!this.waterIsClear(blockpos, i, j, k) || !this.surfaceIsClear(blockpos, i, j, k)) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean waterIsClear(BlockPos pPos, int pDx, int pDz, int pScale) {
        BlockPos blockpos = pPos.offset(pDx * pScale, 0, pDz * pScale);
        return this.orcaentity.level().getFluidState(blockpos).is(FluidTags.WATER) && !this.orcaentity.level().getBlockState(blockpos).blocksMotion();
    }

    private boolean surfaceIsClear(BlockPos pPos, int pDx, int pDz, int pScale) {
        return this.orcaentity.level().getBlockState(pPos.offset(pDx * pScale, 1, pDz * pScale)).isAir() && this.orcaentity.level().getBlockState(pPos.offset(pDx * pScale, 2, pDz * pScale)).isAir();
    }

    public boolean canContinueToUse() {
        double $$0 = this.orcaentity.getDeltaMovement().y;
        return (!($$0 * $$0 < 0.029999999329447746) || this.orcaentity.getXRot() == 0.0F || !(Math.abs(this.orcaentity.getXRot()) < 10.0F) || !this.orcaentity.isInWater()) && !this.orcaentity.onGround();
    }

    public boolean isInterruptable() {
        return false;
    }

    public void start() {
        Direction $$0 = this.orcaentity.getMotionDirection();
        float scale = (float) 1.2;
        this.orcaentity.setDeltaMovement(this.orcaentity.getDeltaMovement().add((double)$$0.getStepX() * scale, 1.0, (double)$$0.getStepZ() * scale));
        this.orcaentity.getNavigation().stop();
    }

    public void stop() {
        this.orcaentity.setXRot(0.0F);
    }

    public void tick() {
        boolean $$0 = this.breached;
        if (!$$0) {
            FluidState $$1 = this.orcaentity.level().getFluidState(this.orcaentity.blockPosition());
            this.breached = $$1.is(FluidTags.WATER);
        }

        if (this.breached && !$$0) {
            this.orcaentity.playSound(SoundEvents.DOLPHIN_JUMP, 4.0F, 0.75F);
        }

        Vec3 $$20 = this.orcaentity.getDeltaMovement();
        if ($$20.y * $$20.y < 0.029999999329447746 && this.orcaentity.getXRot() != 0.0F) {
            this.orcaentity.setXRot(Mth.rotLerp(this.orcaentity.getXRot(), 0.0F, 0.2F));
        } else if ($$20.length() > 9.999999747378752E-6) {
            double $$30 = $$20.horizontalDistance();
            double $$4 = Math.atan2(-$$20.y, $$30) * 57.2957763671875;
            this.orcaentity.setXRot((float)$$4);
        }

    }
}