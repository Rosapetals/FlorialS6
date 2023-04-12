package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.florial.species.SpeciesWrapper;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class SpeciesCheckCommand extends BaseCommand {

    @CommandAlias("myspecies")
    public void onInfoPanel(Player p) throws SQLException {
         p.sendMessage("your species: " + SpeciesWrapper.getSpecies(p.getUniqueId()));
    }
}