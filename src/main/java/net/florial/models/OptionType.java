package net.florial.models;

import dev.morphia.annotations.Transient;
import lombok.Getter;
import lombok.Setter;

public enum OptionType {

    NIGHT_VISION(1, true),
    ALL_EFFECTS(2, true);

    @Getter
    private final int id;

    @Transient
    @Setter
    @Getter private boolean on;


    OptionType(int id, boolean on) {
        this.id = id;
        this.on = on;

    }
}
