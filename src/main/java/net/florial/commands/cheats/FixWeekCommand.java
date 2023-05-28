package net.florial.commands.cheats;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import net.florial.Florial;
import net.florial.models.PlayerData;
import org.bukkit.entity.Player;

public class FixWeekCommand extends BaseCommand {

    @CommandAlias("fixweek")
    @CommandPermission("op")
    public void onWeekFix(Player p, @Optional @Flags("other") Player target){

        PlayerData data = Florial.getPlayerData().get(target.getUniqueId());

        data.getLoggedInDays().replaceAll((key, value) -> false);

        p.sendMessage("Successful. Weeks now: " + data.getLoggedInDays());

    }
}