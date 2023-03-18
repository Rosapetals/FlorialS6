package net.florial.features.age;

import dev.morphia.annotations.Transient;
import lombok.Getter;

public enum Age {

    KIT(1, 0),
    ADOLESCENT(2, 2),
    YOUNG_ADULT(3, 4),
    ADULT(4, 8),
    ELDER(5, 10);

    @Getter private final int id;
    @Getter private final int increase;

    Age(int id, int increase) {
        this.id = id;
        this.increase = increase;
    }


}
