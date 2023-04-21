package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.florial.menus.SellMenu;
import org.bukkit.entity.Player;

public class SellCommand extends BaseCommand {

    private static final SellMenu sellMenu = new SellMenu();

    @CommandAlias("sell")
    @Default
    public static void onSit(Player p) {sellMenu.sellMenu(p);}
}
