package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.florial.menus.shop.ShopMenu;
import org.bukkit.entity.Player;

public class ShopCommand extends BaseCommand {

    ShopMenu shopMenu = new ShopMenu();

    @CommandAlias("shop")
    @Default
    public void onOpenShop(Player player) {
        shopMenu.shopMenu(player);}
}
