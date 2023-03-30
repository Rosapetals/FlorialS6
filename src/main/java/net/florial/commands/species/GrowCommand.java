package net.florial.commands.species;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.florial.menus.species.GrowthMenu;
import org.bukkit.entity.Player;

public class GrowCommand extends BaseCommand {

    private static final GrowthMenu Growth = new GrowthMenu();

    @CommandAlias("grow")
    public void onInfoPanel(Player p) {Growth.growthMenu(p);}
}


