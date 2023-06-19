package net.florial.species;

import lombok.Getter;
import net.florial.species.impl.Cat;
import net.florial.species.impl.Fox;
import net.florial.species.impl.Human;
import net.florial.species.impl.Pearlin;
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
    PEARLIN(4, new Pearlin(4)),

    // user made species
    ENDN(5, new Endn(5)),
    DRACONIC(6, new Draconic(6)),
    BASSBEAST(7, new BassBeast(7)),
    DRYAD(8, new Dryad(8)),
    MER(9, new Mer(9)),
    LYNX(10, new Lynx(10)),
    NEKORYU(11, new NekoRyu(11)),
    WINDOWSIAN(12, new Windowsian(12)),
    THALLIDIAN(13, new Thallidian(13)),
    MAGIC_DUCK(14, new MagicDuck(14)),
    SUNDRAGON(15, new SunDragon(15)),
    GALACTIC_RESEARCHER(16, new GalacticResearcher(16)),
    ENDERWING(17, new Enderwing(17));




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
