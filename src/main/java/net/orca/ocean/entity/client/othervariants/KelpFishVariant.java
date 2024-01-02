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
    RONQUIL(4, "ronquil", "ronquil");

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
