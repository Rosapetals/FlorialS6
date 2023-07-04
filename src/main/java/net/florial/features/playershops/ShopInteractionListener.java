package net.florial.features.playershops;

import net.florial.Florial;
import net.florial.utils.game.RegionDetector;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;
import java.util.UUID;

public class ShopInteractionListener implements Listener {


    private static final ShopPanelMenu shopPanelMenu = new ShopPanelMenu();

    @EventHandler
    public void shopInteraction(PlayerInteractEvent e) {

        if (!(RegionDetector.detect(e.getPlayer().getLocation()).contains("shops"))) return;

        if (e.getAction() != Action.LEFT_CLICK_BLOCK
                || Objects.requireNonNull(e.getClickedBlock()).getType() != Material.SPRUCE_WALL_SIGN) return;


        Sign sign = (Sign) e.getClickedBlock().getState();
        String[] lines = sign.getLines();

        if (lines[1].contains(e.getPlayer().getName())) {

            UUID u = e.getPlayer().getUniqueId();

            shopPanelMenu.open(e.getPlayer());
            Florial.getBoardLocation().put(u, sign.getLocation());

        }
    }


}
