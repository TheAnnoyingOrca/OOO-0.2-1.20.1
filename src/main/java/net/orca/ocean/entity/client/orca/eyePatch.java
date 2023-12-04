package net.orca.ocean.entity.client.orca;

import java.util.Arrays;
import java.util.Comparator;

public enum eyePatch {
    MISCHIEF(1),
    WORRYWORT(2),
    STYLISH(3),
    SLENDER(4);

    private static final net.orca.ocean.entity.client.orca.eyePatch[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(net.orca.ocean.entity.client.orca.eyePatch::getId)).toArray((p_30873_) -> {
        return new net.orca.ocean.entity.client.orca.eyePatch[p_30873_];
    });
    private final int id;

    private eyePatch(int pId) {
        this.id = pId;
    }

    public int getId() {
        return this.id;
    }

    public static net.orca.ocean.entity.client.orca.eyePatch byId(int pId) {
        return BY_ID[pId % BY_ID.length];
    }
}