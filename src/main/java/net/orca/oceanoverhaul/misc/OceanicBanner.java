package net.orca.oceanoverhaul.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.orca.oceanoverhaul.OceanOverhaul;

public class OceanicBanner {
    public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, OceanOverhaul.MOD_ID);

    public static final RegistryObject<BannerPattern> ORCA = BANNER_PATTERNS.register("orca", () -> new BannerPattern("ooo"));
    public static final RegistryObject<BannerPattern> LANCET = BANNER_PATTERNS.register("lancet", () -> new BannerPattern("ool"));

}