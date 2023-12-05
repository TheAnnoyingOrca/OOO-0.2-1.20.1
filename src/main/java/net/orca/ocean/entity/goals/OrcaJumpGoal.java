package net.orca.ocean.entity.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.orca.ocean.entity.custom.OrcaEntity;

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
            Direction $$0 = this.orcaentity.getMotionDirection();
            int $$1 = $$0.getStepX();
            int $$2 = $$0.getStepZ();
            BlockPos $$3 = this.orcaentity.blockPosition();
            int[] var5 = STEPS_TO_CHECK;
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                int $$4 = var5[var7];
                if (!this.waterIsClear($$3, $$1, $$2, $$4) || !this.surfaceIsClear($$3, $$1, $$2, $$4)) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean waterIsClear(BlockPos pPos, int pDx, int pDz, int pScale) {
        BlockPos $$4 = pPos.offset(pDx * pScale, 0, pDz * pScale);
        return this.orcaentity.level.getFluidState($$4).is(FluidTags.WATER) && !this.orcaentity.level.getBlockState($$4).getMaterial().blocksMotion();
    }

    private boolean surfaceIsClear(BlockPos pPos, int pDx, int pDz, int pScale) {
        return this.orcaentity.level.getBlockState(pPos.offset(pDx * pScale, 1, pDz * pScale)).isAir() && this.orcaentity.level.getBlockState(pPos.offset(pDx * pScale, 2, pDz * pScale)).isAir();
    }

    public boolean canContinueToUse() {
        double $$0 = this.orcaentity.getDeltaMovement().y;
        return (!($$0 * $$0 < 0.029999999329447746) || this.orcaentity.getXRot() == 0.0F || !(Math.abs(this.orcaentity.getXRot()) < 10.0F) || !this.orcaentity.isInWater()) && !this.orcaentity.isOnGround();
    }

    public boolean isInterruptable() {
        return false;
    }

    public void start() {
        Direction $$0 = this.orcaentity.getMotionDirection();
        this.orcaentity.setDeltaMovement(this.orcaentity.getDeltaMovement().add((double)$$0.getStepX() * 0.6, 0.7, (double)$$0.getStepZ() * 0.6));
        this.orcaentity.getNavigation().stop();
    }

    public void stop() {
        this.orcaentity.setXRot(0.0F);
    }

    public void tick() {
        boolean $$0 = this.breached;
        if (!$$0) {
            FluidState $$1 = this.orcaentity.level.getFluidState(this.orcaentity.blockPosition());
            this.breached = $$1.is(FluidTags.WATER);
        }

        if (this.breached && !$$0) {
            this.orcaentity.playSound(SoundEvents.DOLPHIN_JUMP, 4.0F, 0.75F);
        }

        Vec3 $$20 = this.orcaentity.getDeltaMovement();
        if ($$20.y * $$20.y < 0.029999999329447746 && this.orcaentity.getXRot() != 0.0F) {
            this.orcaentity.setXRot(Mth.rotlerp(this.orcaentity.getXRot(), 0.0F, 0.2F));
        } else if ($$20.length() > 9.999999747378752E-6) {
            double $$30 = $$20.horizontalDistance();
            double $$4 = Math.atan2(-$$20.y, $$30) * 57.2957763671875;
            this.orcaentity.setXRot((float)$$4);
        }

    }
}