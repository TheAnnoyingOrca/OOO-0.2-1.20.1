package net.orca.oceanoverhaul.entity.client.orca;

import java.util.Arrays;
import java.util.Comparator;

public enum saddlePatch {
    NONE(0),
    MISCHIEF(1),
    WORRYWORT(2),
    STYLISH(3),
    SLENDER(4),
    SCARRED(5),
    ;

    private static final saddlePatch[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(saddlePatch::getId)).toArray((p_30873_) -> {
        return new saddlePatch[p_30873_];
    });
    private final int id;

    private saddlePatch(int pId) {
        this.id = pId;
    }

    public int getId() {
        return this.id;
    }

    public static saddlePatch byId(int pId) {
        return BY_ID[pId % BY_ID.length];
    }
}