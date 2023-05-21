package net.florial.features.upgrades;

import dev.morphia.annotations.Transient;
import lombok.Getter;
import lombok.Setter;
import net.florial.species.SpecieType;

public enum Upgrade {

    // general

    HASTE(1, false, 1),
    DOUBLEHEALTH(2, false, 2),
    NATUREIMMUNITY(3, false, 3),
    SELLINCREASE(5, false, 5),


    //instincts

    SNEAKY(4, false, 4),
    FOCUS(5, false, 5),
    NEARDEATH(6, false, 6),
    FALL(7, false, 7),
    METABOLIZER(8, false, 8),
    STRIKER(9, false, 9),
    RESISTANT(10, false, 10),
    FLESHEATER(11, false, 11),
    SOPHISTICATION(12, false, 12),

    STRONGNOSE(13, false, 13);


    @Getter private final int upgrade;

    @Getter private final int id;

    @Transient @Setter  @Getter private boolean has;

    Upgrade(int upgrade, boolean has, int id) {

        this.upgrade = upgrade;
        this.has = has;
        this.id = id;
    }


    public static Upgrade fromID(int id) {
        for (Upgrade e : values())
            if (e.id == id) return e;

        return null;
    }

    public static int randomInstinct(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }


}
