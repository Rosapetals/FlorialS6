package net.florial.listeners;

import net.florial.features.thirst.HydrateEvent;
import net.florial.features.thirst.ThirstManager;
import net.florial.utils.game.LineOfSight;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class ThirstListener  implements Listener {


    @EventHandler
    private void OnDrink(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        ItemStack i = p.getInventory().getItemInMainHand();

        if (!LineOfSight.get(p, Material.WATER, 5) || i.getType() != Material.AIR) return;

        HydrateEvent e = new HydrateEvent(
                p,
                i,
                ThirstManager.getThirst(p),
                2

        );

        Bukkit.getPluginManager().callEvent(e);
    }
}
