package net.florial.listeners;

import net.florial.features.playershops.PlayerShops;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ShopInteractionListener implements Listener {



    @EventHandler
    public void shopInteraction(PlayerInteractEvent e) {

        if (e.getAction() != Action.LEFT_CLICK_BLOCK
        || e.getClickedBlock().getType() != Material.SPRUCE_SIGN
        || (!(PlayerShops.playerShops.contains(e.getClickedBlock().getLocation())))) return;

        Sign sign = (Sign) e.getClickedBlock().getState();
        String[] lines = sign.getLines();

        if (lines[1].contains(e.getPlayer().getName())) {

        }
    }


}
