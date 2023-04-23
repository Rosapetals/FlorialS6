package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.florial.utils.general.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("viewself")
public class ViewSelfCommand extends BaseCommand {


    @Default
    public static void viewSelf(Player p) {

            Bukkit.dispatchCommand(p, "viewselfd");

        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Viewself toggled."));

    }
}