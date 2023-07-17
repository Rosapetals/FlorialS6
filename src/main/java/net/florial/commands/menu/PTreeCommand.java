package net.florial.commands.menu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.florial.menus.PlaytimeMenu;
import org.bukkit.entity.Player;

public class PTreeCommand extends BaseCommand {

    PlaytimeMenu playtimeMenu = new PlaytimeMenu();

    @CommandAlias("ptree")
    @Default
    public void onPlaytimeTreeOpen(Player player) {playtimeMenu.activate(player);}
}