package net.florial.commands.ranks;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.florial.Florial;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class IridiumPumpkinCommand extends BaseCommand {


    @CommandAlias("iridiumpumpkins")
    @CommandPermission("iridium")
    @Default
    public void iridiumPumpkin(Player p) {


        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission set worldedit.*");
        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> {
            Bukkit.dispatchCommand(p, "pumpkins 10");
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission unset worldedit.*");
        }, 40L);


    }
}