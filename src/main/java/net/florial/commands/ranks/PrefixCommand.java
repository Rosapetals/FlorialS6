package net.florial.commands.ranks;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Optional;
import net.florial.utils.general.CC;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PrefixCommand extends BaseCommand {

    @CommandAlias("prefix")
    @CommandPermission("iridiumplus")
    @Default
    public static void changePrefix(Player p, @Optional String s) {

        if (s == null) return;

        if (s.isBlank()) {

            p.sendMessage((CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c Correct Usage: /prefix &ccool")));
            p.playSound(p, Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);

        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " meta setprefix 100 \"" + s + " \"");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tab player " + p.getName() + " tagprefix \"" + s + " \"");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tab player " + p.getName() + " tabprefix \"" + s + " \"");

            p.sendMessage((CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully set your prefix to " + s + "!")));
            p.playSound(p, Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);
        }

    }
}
