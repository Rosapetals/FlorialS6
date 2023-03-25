package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.florial.menus.ResourceShopMenu;
import org.bukkit.entity.Player;

public class ShopCommand extends BaseCommand {

    ResourceShopMenu resourceShopMenu = new ResourceShopMenu();

    @CommandAlias("shop")
    @Default
    public void onOpenShop(Player player) {
        resourceShopMenu.resourceCategory(player);}
}
