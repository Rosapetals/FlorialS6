package net.florial.commands.ranks;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.florial.Florial;
import net.florial.utils.general.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DiamondFixWaterCommand extends BaseCommand {


    @CommandAlias("diamondfixwater")
    @CommandPermission("diamond")
    @Default
    public void diamondFixWater(Player p) {


        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission set worldedit.*");
        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> {
            Bukkit.dispatchCommand(p, "fixwater 10");
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission unset worldedit.*");
        }, 40L);


        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Succesful!"));









    }
}
