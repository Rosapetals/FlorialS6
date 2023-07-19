package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import net.florial.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("nuzzle")
public class NuzzleCommand extends BaseCommand {

    @Default
    public static void onNuzzle(Player player, @Optional @Flags("other") Player target) {
        Bukkit.broadcastMessage("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤#ff7a8b " + player.getName() + "&f nuzzled #ff7a8b" + target.getName());
    }
}
