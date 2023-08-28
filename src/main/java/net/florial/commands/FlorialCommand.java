package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import net.florial.Florial;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class FlorialCommand extends BaseCommand {

    @Default
    public void florial(Player player) {
        player.sendMessage(Component.text("&d[FLORIAL]\n&fIf you are seeing this, then you do not need to worry about this command, if you think you do need to worry, I'm scared for you"));
    }

    @Subcommand("reload")
    @CommandPermission("op")
    public void reloadConfig(Player player) {
        Florial.getInstance().reloadConfig();
        player.sendMessage(Component.text("&aReloaded config file, some changes such as tokens will require a total restart to change"));
    }
}
