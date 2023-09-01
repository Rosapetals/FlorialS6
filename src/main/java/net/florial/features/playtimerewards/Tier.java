package net.florial.features.playtimerewards;

import lombok.Getter;

public enum Tier {

    TIER1(1, 10, 25, 2, 25, 20000),
    TIER2(2, 15, 25, 3, 50,5000),
    TIER3(3, 25,25, 3, 50,5000),
    TIER4(4, 50, 50, 4, 100,5000),
    TIER5(5, 100, 50, 5, 500, 100000),
    TIER6(6, 500,500, 64, 1000, 1000000);

    @Getter
    private final int id;

    @Getter
    private final int requiredHours;

    @Getter private final int flories;

    @Getter private final int seasonalKeys;
    @Getter private final int dna;

    @Getter private final int money;


    Tier(int id, int requiredHours, int flories, int seasonalKeys, int dna, int money) {
        this.id = id;
        this.flories = flories;
        this.requiredHours = requiredHours;
        this.seasonalKeys = seasonalKeys;
        this.dna = dna;
        this.money = money;
    }

    public static Tier fromID(int id) {
        for (Tier e : values())
            if (e.id == id) return e;

        return null;
    }
}
