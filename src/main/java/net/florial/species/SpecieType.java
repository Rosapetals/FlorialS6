package net.florial.species;

import lombok.Getter;
import net.florial.species.impl.Cat;
import net.florial.species.impl.Fox;
import net.florial.species.impl.Human;
import net.florial.species.impl.usermade.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SpecieType {

    //base species

    NONE(0, null),
    CAT(1, new Cat(1)),
    FOX(2, new Fox(2)),
    HUMAN(3, new Human(3)),
    PEARLIN(4, null),

    // user made species
    ENDN(5, new Endn(5)),
    DRACONIC(6, new Draconic(6)),
    NATUREFOX(7, new NatureFox(7)),
    BASSBEAST(8, new BassBeast(8)),
    DRYAD(9, new Dryad(9)),
    MER(10, new Mer(10)),
    LYNX(11, new Lynx(11)),
    NEKORYU(12, new NekoRyu(12)),
    WINDOWSIAN(13, new Windowsian(13)),
    THALLIDIAN(14, new Thallidian(14));


    //end




    @Getter private final int id;
    @Getter private final Species specie;

    SpecieType(int id, Species specie) {
        this.id = id;
        this.specie = specie;
    }

    public static SpecieType fromID(int id) {
        for (SpecieType e : values())
            if (e.id == id) return e;

        return null;
    }

    public static List<Species> getAllSpecies() {
        return Arrays.stream(values()).map(SpecieType::getSpecie).collect(Collectors.toList());
    }
}
