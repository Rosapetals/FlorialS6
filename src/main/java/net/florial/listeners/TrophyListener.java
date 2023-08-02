package net.florial.listeners;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TrophyListener implements Listener {


    @EventHandler
    public void onPlayerInteract(BlockPlaceEvent event) {

        if (NBTEditor.getInt(event.getItemInHand(), "CustomModelData") != 210
        || ChatColor.stripColor(event.getItemInHand().getLore().toString()).contains("WORTH")) return;

        Player p = event.getPlayer();
        
        Block signBlock = event.getBlockPlaced().getRelative(p.getFacing());
        signBlock.setType(Material.SPRUCE_WALL_SIGN);
        Sign sign = (Sign) signBlock.getState();
        sign.setLine(0, ChatColor.GOLD + event.getItemInHand().getItemMeta().getLore().get(0));

       // BlockData signData = Material.SPRUCE_WALL_SIGN.createBlockData();

       // ((WallSign) signData).setFacing(p.getFacing().getOppositeFace());

       // signBlock.setBlockData(signData);

      //  sign.set(player.getFacing().getOppositeFace());

        sign.update();

    }

}