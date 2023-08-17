package net.florial.listeners;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.Florial;
import net.florial.utils.Cooldown;
import net.florial.utils.game.RegionDetector;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;


public class DrillListener implements Listener {

    private static final TownyAPI tapi = TownyAPI.getInstance();


    @EventHandler
    public void SuperDrill(PlayerInteractEvent e) {
        if (!(e.getAction() == Action.LEFT_CLICK_AIR) && !(e.getAction() == Action.LEFT_CLICK_BLOCK))
            return;

        if (e.getItem() == null)
            return;

        if (!(RegionDetector.detect(e.getPlayer().getLocation()).contains("none"))) return;


        int value = NBTEditor.getInt(e.getItem(), "CustomModelData");

        if (value != 30 && value != 31 || Cooldown.isOnCooldown("drill", e.getPlayer()))
            return;

        Player p = e.getPlayer();
        Resident resident = tapi.getResident(p.getName());

        Town town = tapi.getTown(p.getLocation());
        if (town == null) {
            return;
        } else if (!town.hasResident(resident) && (!(town.isMayor(resident)))) {
            return;
        }

        if (value == 30) {

            Block b = e.getClickedBlock();

            if (b == null)
                return;

            p.breakBlock(b);

        } else {
            if (!(p.getWorld().getBlockAt(p.getLocation()).getType() == Material.WATER))
                return;
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission set worldedit.*");
            Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> {
                Bukkit.dispatchCommand(p, "/drain 20");
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission unset worldedit.*");
            }, 40L);
            Cooldown.createCooldown("drill", p, 7);

        }
    }
}
