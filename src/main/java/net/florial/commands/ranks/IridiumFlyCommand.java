package net.florial.commands.ranks;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.florial.Florial;
import net.florial.utils.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class IridiumFlyCommand extends BaseCommand {


    @CommandAlias("iridiumfly")
    @CommandPermission("iridiumplus")
    @Default
    public void iridiumPumpkin(Player p, @Optional @Flags("other") Player target) {

        if (!(Cooldown.isOnCooldown("fly", p))) {

            target.setFlying(true);

            Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> target.setFlying(false), 6000);

            p.sendMessage("Successfully gave flight to " + target.getName() + "!");

            Cooldown.addCooldown("fly", p, 300);


        } else {
            p.sendMessage("5 minute cooldown!");
        }









    }
}
