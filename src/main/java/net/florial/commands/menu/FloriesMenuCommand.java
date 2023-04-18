package net.florial.commands.menu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.florial.menus.FloriesMenu;
import org.bukkit.entity.Player;

public class FloriesMenuCommand extends BaseCommand {

    FloriesMenu floriesMenu = new FloriesMenu();

    @CommandAlias("flories")
    @Default
    public void openFlorIes(Player player) {floriesMenu.floriesMenu(player);}
}

