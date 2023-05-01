package net.florial.commands.species;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
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

        Bukkit.dispatchCommand(p, "warp " + data.getSpecies().getName());
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 2);

    }
}
