package net.florial.features.dailyrewards;

import dev.morphia.annotations.Transient;
import lombok.Getter;
import lombok.Setter;

public enum Weekday {

    SUNDAY(1, false),
    MONDAY(2, false),
    TUESDAY(3, false),
    WEDNESDAY(4, false),
    THURSDAY(5, false),
    FRIDAY(6, false),
    SATURDAY(7, false);

    @Getter
    private final int id;

    @Transient
    @Setter
    @Getter private boolean achieved;

    Weekday(int id, boolean achieved) {
        this.id = id;
        this.achieved = achieved;
    }


}


