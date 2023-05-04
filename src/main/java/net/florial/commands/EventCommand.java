package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.florial.menus.EventsMenu;
import org.bukkit.entity.Player;

public class EventCommand extends BaseCommand {


    private static final EventsMenu eventMenu = new EventsMenu();

    @CommandAlias("event")
    public void onEventMenuOpen(Player p) {eventMenu.eventMenu(p);}
}
