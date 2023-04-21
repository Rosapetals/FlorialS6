package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import me.libraryaddict.disguise.DisguiseAPI;
import net.florial.Florial;
import net.florial.species.disguises.Morph;
import net.florial.utils.general.CC;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sittable;

public class SitCommand extends BaseCommand {

    private static final Morph morph = new Morph();

    @CommandAlias("sit")
    @Default
    public static void onSit(Player p) {

        if (!(DisguiseAPI.getDisguise(p) instanceof Sittable)) return;

        morph.activate(p, 1,  (!((Sittable) DisguiseAPI.getDisguise(p)).isSitting()), true, Florial.getPlayerData().get(p.getUniqueId()).getSpecies());

        p.sendMessage((CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Do #ff3c55/sit&f again to sit or stand up!")));

    }
}