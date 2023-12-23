package net.orca.ocean.entity.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.BreathAirGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.orca.ocean.entity.custom.OrcaEntity;

import java.util.EnumSet;

public class OrcaBreathAirGoal extends Goal {
    private final OrcaEntity orcaentity;

    public OrcaBreathAirGoal(OrcaEntity pOrcaEntity) {
        this.orcaentity = pOrcaEntity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        return this.orcaentity.getAirSupply() < 140;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        return this.canUse();
    }

    public boolean isInterruptable() {
        return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.findAirPosition();
    }

    private void findAirPosition() {
        Iterable<BlockPos> iterable = BlockPos.betweenClosed(Mth.floor(this.orcaentity.getX() - 1.0D), this.orcaentity.getBlockY(), Mth.floor(this.orcaentity.getZ() - 1.0D), Mth.floor(this.orcaentity.getX() + 1.0D), Mth.floor(this.orcaentity.getY() + 9.0D), Mth.floor(this.orcaentity.getZ() + 1.0D));
        BlockPos blockpos = null;

        for(BlockPos blockpos1 : iterable) {
            if (this.givesAir(this.orcaentity.level(), blockpos1)) {
                blockpos = blockpos1;
                break;
            }
        }

        if (blockpos == null) {
            blockpos = BlockPos.containing(this.orcaentity.getX(), this.orcaentity.getY() + 9.0D, this.orcaentity.getZ());
        }

        this.orcaentity.getNavigation().moveTo((double)blockpos.getX(), (double)(blockpos.getY() + 4), (double)blockpos.getZ(), 1.0D);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        this.findAirPosition();
        this.orcaentity.moveRelative(0.02F, new Vec3((double)this.orcaentity.xxa, (double)this.orcaentity.yya, (double)this.orcaentity.zza));
        this.orcaentity.move(MoverType.SELF, this.orcaentity.getDeltaMovement());
    }

    private boolean givesAir(LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        return (pLevel.getFluidState(pPos).isEmpty() || blockstate.is(Blocks.BUBBLE_COLUMN)) && blockstate.isPathfindable(pLevel, pPos, PathComputationType.LAND);
    }
}
