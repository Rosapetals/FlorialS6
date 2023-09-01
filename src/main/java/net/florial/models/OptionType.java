package net.florial.models;

import dev.morphia.annotations.Transient;
import lombok.Getter;
import lombok.Setter;
import net.florial.species.disguises.Morph;

public enum OptionType {

    NIGHT_VISION(1, true),
    ALL_EFFECTS(2, true);


    private static final Morph morph = new Morph();

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
