package net.florial.commands.menu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.florial.menus.species.InstinctsMenu;
import net.florial.menus.species.RebirthMenu;
import org.bukkit.entity.Player;

public class RebirthMenuCommand extends BaseCommand {

    RebirthMenu rebirthMenu = new RebirthMenu();

    @CommandAlias("reincarnation")
    @Default
    public void onRebirthOpen(Player player) {rebirthMenu.rebirthMenu(player);}
}
