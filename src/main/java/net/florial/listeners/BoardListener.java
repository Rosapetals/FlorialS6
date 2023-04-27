package net.florial.listeners;

import net.florial.Florial;
import net.florial.utils.game.RegionDetector;
import net.florial.utils.general.CC;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class BoardListener implements Listener {


    @EventHandler
    public void onPlayerInteract(BlockPlaceEvent e) {
        if (!e.getBlock().getType().toString().contains("SIGN")
                || (!RegionDetector.detect(e.getPlayer().getLocation()).contains("signs")
                || e.getPlayer().getGameMode() != GameMode.SURVIVAL)) return;

        Player p = e.getPlayer();

        p.closeInventory();

        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Type your message in chat!"));
        Florial.getBoardLocation().put(p.getUniqueId(), e.getBlock().getLocation());


    }


    public static void writeBoard(Player p, String message, Location loc) {

        Sign sign = (Sign) loc.getBlock().getState();

        sign.setLine(0, p.getName() + " says...");

        String[] lines = message.split("(?<=\\G.{25})");

        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f " + lines + " " + lines[0]));

        for (int i = 1; i < lines.length; i++) {
            if (i > 3) break;
            sign.setLine(i, lines[i]);
        }

        sign.update();
    }

}
