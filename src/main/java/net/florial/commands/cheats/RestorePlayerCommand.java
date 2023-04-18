package net.florial.commands.cheats;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import org.bukkit.entity.Player;

public class RestorePlayerCommand extends BaseCommand {

    @CommandAlias("restoreplayer")
    @CommandPermission("florial.restoreplayer")
    public void onRestorePlayer(Player player, String username, String parameter, Object value) {

    }
}
