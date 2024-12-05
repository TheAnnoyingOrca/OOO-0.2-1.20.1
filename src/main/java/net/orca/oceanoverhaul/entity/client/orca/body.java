package net.orca.oceanoverhaul.entity.client.orca;

import java.util.Arrays;
import java.util.Comparator;

public enum body {
    HOMEY(4),
    SCARRED(2),
    YELLER(3),
    SNUBBY(1);

    private static final body[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(body::getId)).toArray((p_30873_) -> {
        return new net.orca.oceanoverhaul.entity.client.orca.body[p_30873_];
    });
    private final int id;

    private body(int pId) {
        this.id = pId;
    }

    public int getId() {
        return this.id;
    }

    public static body byId(int pId) {
        return BY_ID[pId % BY_ID.length];
    }
}