package net.florial.commands.database;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.florial.database.FlorialDatabase;
import org.bukkit.entity.Player;

public class RemoveFieldCommand extends BaseCommand {

    private static final FlorialDatabase database = new FlorialDatabase();

    @CommandAlias("fieldremove")
    public void onInfoPanel(Player p, String s) {database.removeField(s);}
}
