package net.florial.commands.menu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.florial.menus.BundleMenu;
import org.bukkit.entity.Player;

public class BundleMenuCommand extends BaseCommand {

    BundleMenu bundleMenu = new BundleMenu();

    @CommandAlias("bundle")
    @Default
    public void openBundleMenu(Player player) {bundleMenu.bundleMenuOpen(player);}
}
