package net.florial.commands.cheats;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import net.florial.database.FlorialDatabase;
import org.bukkit.command.CommandSender;

public class RestartEventCommand extends BaseCommand {

    @CommandAlias("restartevent")
    @CommandPermission("op")
    public void restartEventCommand(CommandSender p) {

        FlorialDatabase.restartEvent();
    }
}