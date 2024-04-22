package net.orca.oceanoverhaul.misc;

import com.mojang.blaze3d.shaders.Effect;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OceanicMod extends ForgeMod {
    private static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, "oceanic");
    //public static final RegistryObject<Codec<ForgeBiomeModifiers.AddFeaturesBiomeModifier>> CHANGE_EFFECT_BIOME_MODIFIER_TYPE = BIOME_MODIFIER_SERIALIZERS.register("change_effect", () ->
           // RecordCodecBuilder.create(builder -> builder.group(
                   // Biome.LIST_CODEC.fieldOf("biomes").forGetter(ForgeBiomeModifiers.AddFeaturesBiomeModifier::biomes),
                   // Effects.LIST_CODEC.fieldOf("effects").forGetter(ForgeBiomeModifiers.AddFeaturesBiomeModifier::features)
           // )
   // ));
}
