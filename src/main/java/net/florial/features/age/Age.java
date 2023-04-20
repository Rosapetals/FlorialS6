package net.florial.features.age;

import lombok.Getter;
import net.florial.models.PlayerData;
import net.florial.species.disguises.Morph;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public enum Age {

    KIT(1, 5, 5, 0),
    ADOLESCENT(2, 50, 50, 2),
    YOUNG_ADULT(3, 75, 100, 4),
    ADULT(4, 100, 200, 8),
    ELDER(5, 200, 500, 10);

    private static final Morph morph = new Morph();

    @Getter private final int id;

    @Getter private final int requiredQuests;

    @Getter private final int requiredDNA;
    @Getter private final int increase;

    Age(int id, int requiredQuests, int requiredDNA, int increase) {
        this.id = id;
        this.requiredQuests = requiredQuests;
        this.requiredDNA = requiredDNA;
        this.increase = increase;
    }

    public static void up(Player p, PlayerData data, Age age, int requiredQuests, int requiredDNA){

        p.closeInventory();

        if (!(data.getGrowth() >= requiredQuests)) {
            p.sendMessage("You have not completed enough quests. To age up, you need "
                    + requiredQuests + " completed quests. Complete quests through /grow. You currently have "
                    + data.getGrowth() + " quests completed.");
            return;
        }

        if (!(data.getDna() >= requiredDNA)) {
            p.sendMessage("You are lacking DNA. To age up, you need "
                    + requiredDNA + " DNA! To get DNA, complete quests or convert cash to DNA in /species. You currently have"
                    + data.getDna() + " DNA.");
            return;
        }

        int id = age.getId();
        Age newAge = null;

        for (Age e : values())  {
            if (e.id != id) {
                newAge = e;
                break;
            }
        }
        
        if (newAge != null) {

            p.sendMessage("You have aged up to " + newAge + "!");

            data.setAge(newAge);
            data.setGrowth(0);
            data.setDna(data.getDna() - requiredDNA);

            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 2, 1);

            morph.activate(p, 5, false, true, data.getSpecies());

        } else {
            p.sendMessage("You can't age up anymore!");
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 2, 1);

        }

    }



}
