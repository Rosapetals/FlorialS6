package net.florial.utils.game;

import net.florial.Florial;
import net.florial.features.age.Age;
import net.florial.models.PlayerData;
import org.bukkit.entity.Player;

public class AgeSuffixAdjuster {


    public static String cache(Player p) {

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        String ageIcon = "&f";
        assert data.getPronouns() != null;
        String pronouns = data.getPronouns().isBlank() ? "" : "#ff3c55(" + data.getPronouns() + ")";



        Age age = data.getAge();

        switch (age) {
            case KIT -> ageIcon = "\uE616";
            case ADOLESCENT -> ageIcon = "\uE617";
            case YOUNG_ADULT -> ageIcon = "\uE618";
            case ADULT -> ageIcon = "\uE619";
            case ELDER -> ageIcon = "\uE620";

        }

        return "&f" + ageIcon + pronouns;


    }
}
