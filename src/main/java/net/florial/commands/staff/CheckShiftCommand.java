package net.florial.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import net.florial.Florial;
import net.florial.models.ShiftData;
import net.florial.utils.Message;
import net.florial.utils.general.CC;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class CheckShiftCommand extends BaseCommand {

    @CommandAlias("checkshift")
    @CommandPermission("florial.staff")
    public void onShiftEnd(Player player) {

        if (!Florial.getStaffWithShifts().containsKey(player.getUniqueId())) {
            player.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff5b70&l➤ &cYou do not have a shift running"));
            return;
        }

        ShiftData data = Florial.getStaffWithShifts().get(player.getUniqueId());

        int minutes = Math.toIntExact(TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - data.getShiftStart()));


        player.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff5b70&l➤&f Current shift time (minutes): " + minutes));
    }
}
