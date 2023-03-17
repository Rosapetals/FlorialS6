package net.florial.features.upgrades;

import dev.morphia.annotations.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public enum Upgrade {

    // general

    HASTE(1, false),
    DOUBLEHEALTH(2, false);

    //instincts

    @Getter private final int upgrade;

    @Transient @Setter  @Getter private boolean has;

    Upgrade(int upgrade, boolean has) {

        this.upgrade = upgrade;
        this.has = has;
    }

}
