package net.orca.oceanoverhaul.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.orca.oceanoverhaul.OceanOverhaul;

public class OceanTags {
    public static final TagKey<BannerPattern> PATTERN_ITEM_ORCA = patternTag("pattern_item/orca");
    public static final TagKey<BannerPattern> PATTERN_ITEM_LANCET = patternTag("pattern_item/lancet");

    private static TagKey<BannerPattern> patternTag(String path) {
        return TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(OceanOverhaul.MOD_ID, path));
    }
}
