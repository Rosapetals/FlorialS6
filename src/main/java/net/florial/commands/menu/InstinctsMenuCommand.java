package net.florial.commands.menu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.florial.menus.species.InstinctsMenu;
import org.bukkit.entity.Player;

public class InstinctsMenuCommand extends BaseCommand {

    InstinctsMenu instinctsMenu = new InstinctsMenu();

    @CommandAlias("instincts")
    @Default
    public void onOpenShop(Player player) {instinctsMenu.instinctMenu(player);}
}
