package net.florial.utils.game;

import me.libraryaddict.disguise.DisguiseAPI;
import net.florial.Florial;
import net.florial.features.age.Age;
import net.florial.models.PlayerData;
import net.florial.species.disguises.Morph;
import org.bukkit.entity.Player;

public class MorphAdjuster {

    private static final Morph morph = new Morph();

    public static void activate(Player p) {

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        if (DisguiseAPI.getDisguise(p) == null && data.getSpecies().getMorph() != null) {

            morph.activate(p, 0, false, false, data.getSpecies());

            Age age = data.getAge();

            int type = age == Age.KIT ? 6 : 5;

            DisguiseAPI.getDisguise(p).setHideArmorFromSelf(true);

            morph.activate(p, type, false, true, data.getSpecies());

        }



    }
}
