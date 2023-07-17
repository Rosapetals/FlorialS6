package net.florial.commands.species;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ColonyCommand extends BaseCommand {


    @CommandAlias("colony")
    public void colonyCommand(Player p) {

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        if (!(data.getAge().getId() > 3)) {

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You need to at least be an ADULT but you are only a " + data.getAge() + ". Grow up through /grow."));

            return;
        }

        if (data.getSpecieId() != 4) {
            Bukkit.dispatchCommand(p, "warp " + data.getSpecies().getName());
        } else {

            if (DisguiseAPI.getDisguise(p) == null) {
                Bukkit.dispatchCommand(p, "warp human");
                return;
            }

            if (DisguiseAPI.getDisguise(p).getType() == DisguiseType.CAT) {
                Bukkit.dispatchCommand(p, "warp cat");
            } else {
                Bukkit.dispatchCommand(p, "warp fox");
            }

        }

        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 2);

    }
}
