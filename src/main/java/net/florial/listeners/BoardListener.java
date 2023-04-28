package net.florial.listeners;

import net.florial.Florial;
import net.florial.utils.game.RegionDetector;
import net.florial.utils.general.CC;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class BoardListener implements Listener {


    @EventHandler
    public void onCommunityPost(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK
        || (!RegionDetector.detect(e.getPlayer().getLocation()).contains("signs")
        || Objects.requireNonNull(e.getClickedBlock()).getType() != Material.SPRUCE_PLANKS)) return;

        Player p = e.getPlayer();

        Block block = e.getClickedBlock().getRelative(e.getBlockFace());
        BlockData signData = Material.OAK_WALL_SIGN.createBlockData();

        if (signData instanceof org.bukkit.block.data.type.WallSign wallSign) wallSign.setFacing(BlockFace.EAST);

        block.setBlockData(signData);
        Sign sign = (Sign) block.getState();
        sign.setEditable(true);
        sign.update();

        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Type your message in chat!"));
        Florial.getBoardLocation().put(p.getUniqueId(), block.getLocation());


    }


    public static void writeBoard(Player p, String message, Location loc) {

        Sign sign = (Sign) loc.getBlock().getState();

        sign.setLine(0, p.getName() + " says...");

        String[] lines = message.split("(?<=\\G.{25})");

        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);
        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully inputted!"));

        if (lines.length == 1) {
            sign.setLine(1, lines[0]);
        } else {
            for (int i = 1; i < lines.length; i++) {
                if (i > 3) break;
                sign.setLine(i, lines[i]);
            }
        }

        sign.update();
    }

}
