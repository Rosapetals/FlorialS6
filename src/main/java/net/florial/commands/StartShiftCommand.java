package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import net.florial.Florial;
import net.florial.models.ShiftData;
import net.florial.utils.Message;
import org.bukkit.entity.Player;

public class StartShiftCommand extends BaseCommand {

    @CommandAlias("startshift")
    @CommandPermission("florial.staff")
    public void onShiftStart(Player player) {
        if (Florial.getStaffWithShifts().containsKey(player.getUniqueId())) {
            new Message("&cYou already have a shift running").send(player);
            return;
        }
        Florial.getStaffWithShifts().put(player.getUniqueId(), new ShiftData());
        new Message("&aShift started successfully").send(player);
    }
}
