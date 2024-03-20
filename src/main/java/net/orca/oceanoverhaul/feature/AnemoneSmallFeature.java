package net.orca.oceanoverhaul.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.orca.oceanoverhaul.block.OceanicBlocks;

import java.util.Random;

public class AnemoneSmallFeature extends Feature<CountConfiguration> {

    public AnemoneSmallFeature(Codec<CountConfiguration> p_i231987_1_) {
        super(p_i231987_1_);
    }

    public boolean place(FeaturePlaceContext<CountConfiguration> context) {
        Random rand = (Random) context.level().getRandom();
        WorldGenLevel world = context.level();
        BlockPos pos = world.getHeightmapPos(Heightmap.Types.OCEAN_FLOOR, context.origin());
        //if (ConfigFeatureControl.dimensionFeatureBlacklist.get().contains(world.getLevel().dimension().location().toString()))
            //return false;

        if (pos.getY() > 44 && pos.getY() < 62) {
            int i = 0;
            int m = rand.nextInt(3);
            Block type = switch (m) {
                case 1 -> OceanicBlocks.ANEMONE_MAGENTA.get();
                default -> OceanicBlocks.ANEMONE_GREEN.get();
            };
            for(int j = 0; j < context.config().count().sample((RandomSource) rand); ++j) {
                int k = rand.nextInt(8) - rand.nextInt(8);
                int l = rand.nextInt(8) - rand.nextInt(8);
                int i1 = world.getHeight(Heightmap.Types.OCEAN_FLOOR, pos.getX() + k, pos.getZ() + l);
                BlockPos blockpos = new BlockPos(pos.getX() + k, i1, pos.getZ() + l);
                BlockState blockstate = type.defaultBlockState();
                if (i1 < 62 && world.getBlockState(blockpos).is(Blocks.WATER) && blockstate.canSurvive(world, blockpos)) {
                    world.setBlock(blockpos, blockstate, 2);
                    ++i;
                }
            }

            return i > 0;
        }
        return false;
    }
}