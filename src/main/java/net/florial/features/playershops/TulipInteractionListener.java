package net.florial.features.playershops;

import net.florial.utils.game.RegionDetector;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class TulipInteractionListener implements Listener {

    private static final PlayerShopsMenu playerShops = new PlayerShopsMenu();

    @EventHandler
    public void openPlayerShopPanel(EntityDamageByEntityEvent e) {

        if (e.getEntity().getType() != EntityType.CAT
            || (!RegionDetector.detect(e.getDamager().getLocation()).contains("shops"))) return;

        Player p = (Player) e.getDamager();

        playerShops.open(p);


    }
}
