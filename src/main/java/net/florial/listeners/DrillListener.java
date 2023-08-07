package net.florial.listeners;

import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.Florial;
import net.florial.utils.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;

import java.util.Objects;


public class DrillListener implements Listener {


    @EventHandler
    public void SuperDrill(PlayerInteractEvent e) throws NotRegisteredException {
        if (!(e.getAction() == Action.LEFT_CLICK_AIR) && !(e.getAction() == Action.LEFT_CLICK_BLOCK))
            return;

        if (e.getItem() == null)
            return;

        int value = NBTEditor.getInt(e.getItem(), "CustomModelData");

        if (value != 30 && value != 31 || Cooldown.isOnCooldown("drill", e.getPlayer()))
            return;

        Player p = e.getPlayer();
        Resident resident = TownyAPI.getInstance().getResident(p.getName());

        if (value == 30) {
            if (e.getClickedBlock() == null)
                return;

            Town town = Objects.requireNonNull(TownyAPI.getInstance().getTownBlock(e.getClickedBlock().getLocation())).getTown();
            if (town != null && !(town.hasResident(resident) || town.isMayor(resident))) {
                return;
            }

            p.breakBlock(e.getClickedBlock());

        } else {
            if (!(p.getWorld().getBlockAt(p.getLocation()).getType() == Material.WATER))
                return;

            Town town = Objects.requireNonNull(TownyAPI.getInstance().getTownBlock(p.getLocation())).getTown();
            if (town != null && !(town.hasResident(resident) || town.isMayor(resident))) {
                return;
            }

            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission set worldedit.*");
            Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> {
                Bukkit.dispatchCommand(p, "/drain 20");
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " permission unset worldedit.*");
            }, 40L);

            Cooldown.createCooldown("drill", p, 7);
        }
    }
}
