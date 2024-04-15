package net.orca.oceanoverhaul.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.Shapes;

public class WildKelpBlock extends GrowingPlantHeadBlock implements LiquidBlockContainer {
    public WildKelpBlock(BlockBehaviour.Properties p_54323_) {
        super(p_54323_, Direction.EAST, Shapes.block(), true, 0.14D);
    }
    public boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return false;
    }

    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        return false;
    }


    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource pRandom) {
        return 0;
    }

    @Override
    protected boolean canGrowInto(BlockState pState) {
        return false;
    }

    @Override
    protected Block getBodyBlock() {
        return null;
    }
}
