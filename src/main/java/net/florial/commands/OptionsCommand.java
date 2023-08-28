package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.florial.menus.OptionsMenu;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class OptionsCommand extends BaseCommand {

    private static final OptionsMenu optionsMenu = new OptionsMenu();


    @CommandAlias("options")
    public void onOptionMenuOpen(Player p) {

        p.playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 4);

        optionsMenu.optionsMenuOpen(p);


    }


}

