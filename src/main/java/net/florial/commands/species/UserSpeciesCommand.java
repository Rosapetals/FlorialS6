package net.florial.commands.species;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.florial.menus.species.UserSpeciesMenu;
import org.bukkit.entity.Player;

public class UserSpeciesCommand extends BaseCommand {

    UserSpeciesMenu userSpeciesMenu = new UserSpeciesMenu();

    @CommandAlias("userspecies")
    @Default
    public void onUserSpeciesMenuOpen(Player player) {userSpeciesMenu.userSpeciesMenu(player);}
}
