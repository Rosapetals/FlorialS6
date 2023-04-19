package net.florial.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import net.florial.Florial;
import net.florial.models.ShiftData;
import net.florial.utils.general.CC;
import org.bukkit.entity.Player;

public class StartShiftCommand extends BaseCommand {

    @CommandAlias("startshift")
    @CommandPermission("florial.staff")
    public void onShiftStart(Player player) {
        if (Florial.getStaffWithShifts().containsKey(player.getUniqueId())) {
            player.sendMessage(CC.translate("&cYou already have a shift running"));
            return;
        }
        Florial.getStaffWithShifts().put(player.getUniqueId(), new ShiftData());
        player.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff5b70&l➤&f Shift started successfully"));
    }
}
