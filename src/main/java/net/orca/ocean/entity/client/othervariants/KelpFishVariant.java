package net.orca.ocean.entity.client.othervariants;

import com.mojang.serialization.Codec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.orca.ocean.entity.custom.KelpFishEntity;

import java.util.function.IntFunction;

public enum KelpFishVariant implements StringRepresentable {

    BLUE_ROCKFISH(0, "blue_rockfish", "rockfish"),
    QUILL_ROCKFISH(1, "quill_rockfish", "rockfish"),
    GILDED_ROCKFISH(2, "gilded_rockfish", "rockfish"),
    FIRE_ROCKFISH(3, "fire_rockfish", "rockfish"),
    RONQUIL(4, "ronquil", "ronquil"),
    SCULPIN(5, "sculpin", "sculpin"),
    PAINTED_GREENLING(6, "painted_greenling", "greenling"),
    KELP_GREENLING(7, "kelp_greenling", "greenling"),
    STRIPED_SEA_PERCH(8, "striped_sea_perch", "perch"),
    JEWELED_SEA_PERCH(9, "jeweled_sea_perch", "perch"),
    WARTY_POACHER(10, "warty_poacher", "poacher"),
    ROCK_POACHER(11, "rock_poacher", "poacher"),
    SAILFIN(12, "sailfin", "sailfin"),
    LUMPSUCKER(13, "lumpsucker", "lump"),
    RATFISH(14, "ratfish", "ratfish"),
    CABEZON(15, "cabezon", "cabezon"),
    WARBONNET(16, "warbonnet", "warbonnet"),
    CATSHARK(17, "catshark", "catshark"),
    DOGFISH(18, "dogfish", "dogfish"),
    LEOPARD(19, "leopard", "leopard"),
    LINGCOD(20, "lingcod", "lingcod"),
    SLENDER_SNIPE_EEL(21, "slender_snipe_eel", "snipe"),
    DEEP_LANCETFISH(22, "deep_lancetfish", "lancet"),
    NOCTURN_LANCETFISH(23, "nocturn_lancetfish", "lancet"),
    ELDER_WOLF_EEL(24, "elder_wolf_eel", "wolf"),
    YOUTHFUL_WOLF_EEL(25, "youthful_wolf_eel", "wolf"),
    SHADOW_ROCKFISH(26, "shadow_rockfish", "rockfish"),
    VERMILLION_ROCKFISH(27, "vermillion_rockfish", "rockfish"),
    SILVERGRAY_ROCKFISH(28, "silvergray_rockfish", "rockfish"),
    YELLOWTAIL_ROCKFISH(29, "yellowtail_rockfish", "rockfish"),
    BLACKFIN_POACHER(30, "blackfin_poacher", "poacher"),
    FLUFFY_SCULPIN(31, "fluffy_sculpin", "sailfin"),
    MOSSHEAD_SCULPIN(32, "mosshead_sculpin", "sailfin"),
    THREADFIN_SCULPIN(33, "threadfin_sculpin", "sailfin"),
    MOSSY_WARBONNET(34, "mossy_warbonnet", "warbonnet"),
    POMPANO(35, "pompano", "perch"),
    PRICKLEBACK(36, "prickleback", "ronquil"),
    ROCK_GREENLING(37, "rock_greenling", "greenling"),
    SURFPERCH(38, "surfperch", "perch"),
    SABLEFISH(39, "sablefish", "lingcod");

    private static final IntFunction<KelpFishVariant> BY_ID;
    public static final Codec<KelpFishVariant> CODEC;
    final int id;
    private final String name;
    private final String type;

    KelpFishVariant(int j, String string, String type) {
        this.id = j;
        this.name = string;
        this.type = type;
    }
    public String getType() {
        return this.type;
    }
    @Override
    public String getSerializedName() {
        return this.name;
    }

    public String getBodyPlan() {
        return this.type;
    }

    public int id() {
        return this.id;
    }
    public static KelpFishVariant byId(int i) {
        return BY_ID.apply(i);
    }
    static {
        BY_ID = ByIdMap.sparse(KelpFishVariant::id, KelpFishVariant.values(), RONQUIL);
        CODEC = StringRepresentable.fromEnum(KelpFishVariant::values);
    }

}
