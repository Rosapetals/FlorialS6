package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.florial.utils.general.CC;
import org.bukkit.entity.Player;

public class PackOffCommand extends BaseCommand {

    @CommandAlias("packoff")
    public void onPackOff(Player p) {

        p.setResourcePack("https://github.com/Florial-Development/Resource-Pack/raw/main/S6%20-%20no%20pack16.zip");

        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Your pack should have been changed."));

    }


}