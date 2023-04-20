package net.florial.listeners;

import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.species.Species;
import net.florial.species.events.impl.SpeciesDeathEvent;
import net.florial.species.events.impl.SpeciesKillEvent;
import net.florial.species.events.impl.SpeciesRespawnEvent;
import net.florial.utils.GeneralUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class SpecieListener implements Listener {

    Florial florial = Florial.getInstance();

    @EventHandler
    private void onRespawn(PlayerRespawnEvent event) {
        PlayerData data = Florial.getPlayerData().get(event.getPlayer().getUniqueId());
        if (data == null) return;


        Florial.getThirst().put(event.getPlayer().getUniqueId(), 20);
        Species.refreshTag(event.getPlayer());
        GeneralUtils.runAsync(new BukkitRunnable() {@Override public void run() {Bukkit.getScheduler().runTaskLater(florial, data::refresh, 40L);}});


    }
    
    @EventHandler
    private void onDeath(PlayerDeathEvent event) {
        PlayerData data = Florial.getPlayerData().get(event.getPlayer().getUniqueId());
        if (data == null) return;
        
        SpeciesDeathEvent death = new SpeciesDeathEvent(
            event.getPlayer(),
            data,
            data.getSpecieType()
        );
        
        Bukkit.getPluginManager().callEvent(death);
    
        if (event.getPlayer().getKiller() != null) {
            SpeciesKillEvent kill = new SpeciesKillEvent(
                event.getPlayer().getKiller(),
                event.getPlayer(),
                Florial.getPlayerData().get(event.getPlayer().getKiller().getUniqueId()),
                Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecieType()
            );
    
            Bukkit.getPluginManager().callEvent(kill);
        }
    }
    
}
