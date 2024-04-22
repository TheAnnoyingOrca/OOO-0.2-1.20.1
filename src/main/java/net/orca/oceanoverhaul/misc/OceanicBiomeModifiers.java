package net.orca.oceanoverhaul.misc;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public class OceanicBiomeModifiers {
    private OceanicBiomeModifiers() {}
  //  public static record ChangeEffectBiomeModifier(HolderSet<Biome> biomes, HolderSet<PlacedFeature> effects) implements BiomeModifier
  //  {
      //  @Override
      //  public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder)
     //   {
      //      if (phase == Phase.MODIFY && this.biomes.contains(biome))
       //     {
        //        BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
        //        this.effects.forEach(holder -> generationSettings.build());
         //   }
      //  }

       // @Override
       // public Codec<? extends BiomeModifier> codec()
       // {
      //      return OceanicMod.CHANGE_EFFECT_BIOME_MODIFIER_TYPE.get();
       // }
   // }
}
