package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import dev.morphia.query.filters.Filter;
import dev.morphia.query.filters.Filters;
import net.florial.Florial;
import net.florial.database.FlorialDatabase;
import net.florial.models.PlayerData;
import net.florial.species.SpecieType;
import net.florial.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RestorePlayerCommand extends BaseCommand {

    @CommandAlias("restoreplayer")
    @CommandPermission("florial.restoreplayer")
    public void onRestorePlayer(Player player, String username, String parameter, String value) {
        OfflinePlayer target = Bukkit.getOfflinePlayer(username);
        if (!target.hasPlayedBefore()) {
            new Message("&cThe specified player either does not exist, or has never logged onto the server").send(player);
            return;
        }
        final PlayerData data;
        if (target.isOnline()) {
            data = Florial.getPlayerData().get(target.getUniqueId());
        } else {
            data = FlorialDatabase.getPlayerData(target.getUniqueId()).join();
        }

        switch (parameter) {
            case "flories" -> {
                try {
                    data.setFlories(Integer.parseInt(value));
                    new Message("&aUpdated player's flories to " + data.getFlories()).send(player);
                } catch (NumberFormatException e){
                    new Message("&cIncorrect value type").send(player);
                }
            }
            case "dna" -> {
                try {
                    data.setDna(Integer.parseInt(value));
                    new Message("&aUpdated player's dna to " + data.getDna()).send(player);
                } catch (NumberFormatException e) {
                    new Message("&cIncorrect value type").send(player);
                }
            }
            case "species" -> {
                try {
                    data.setSpecieId(SpecieType.valueOf(value).getId());
                    new Message("&aUpdated player's species to " + data.getSpecieType()).send(player);

                } catch (Exception e) {
                    new Message("&cIncorrect value type").send(player);
                }
            }
            default -> new Message("&cThat is not a valid parameter to be modified").send(player);
        }

        if (target.isOnline()) {
            Florial.getPlayerData().put(target.getPlayer().getUniqueId(), data);
        } else {
            data.save(true);
        }
    }
}
