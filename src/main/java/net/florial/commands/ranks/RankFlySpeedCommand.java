package net.florial.commands.ranks;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import net.florial.Florial;
import net.florial.utils.general.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RankFlySpeedCommand extends BaseCommand {


    @CommandAlias("rankflyspeed")
    @CommandPermission("iridium")
    @Default
    public void iridiumForestGen(Player p, Integer rate) {


        if (rate > 5) return;

        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission set essentials.*");
        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> {
            Bukkit.dispatchCommand(p, "flyspeed" + rate);
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission unset essentials.*");
        }, 40L);

        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set essentials.*"), 120L);


        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Set your fly speed to " + rate));



    }
}
