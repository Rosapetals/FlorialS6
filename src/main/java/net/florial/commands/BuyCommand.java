package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.florial.menus.BuyMenu;
import org.bukkit.entity.Player;

public class BuyCommand extends BaseCommand {

    BuyMenu buyMenu = new BuyMenu();

    @CommandAlias("buy")
    public void buyMenu(Player p) {

        buyMenu.buyMenu(p);

    }


}