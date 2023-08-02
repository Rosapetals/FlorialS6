package net.florial.listeners;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class TrophyListener implements Listener {


    @EventHandler
    public void onTrophyPlace(BlockPlaceEvent event) {

        if (NBTEditor.getInt(event.getPlayer().getInventory().getItemInMainHand(), "CustomModelData") != 210
        || ChatColor.stripColor(event.getItemInHand().getLore().toString()).contains("WORTH")) return;

        Block signBlock = event.getBlockPlaced().getRelative(BlockFace.NORTH);
        signBlock.setType(Material.SPRUCE_WALL_SIGN);
        Sign sign = (Sign) signBlock.getState();
        sign.setLine(0, ChatColor.GOLD + event.getItemInHand().getItemMeta().getLore().get(0));
        sign.setLine(1, ChatColor.GOLD + event.getItemInHand().getItemMeta().getLore().get(1));

        event.getPlayer().sendMessage((CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Placed trophy! Simply break the sign to pick it up!")));

        sign.update();

    }


    @EventHandler
    public void onTrophyBreak(BlockBreakEvent event) {

        if (event.getBlock().getType() != Material.SPRUCE_WALL_SIGN
        || (event.getBlock().getRelative(((WallSign) event.getBlock().getBlockData()).getFacing().getOppositeFace()).getType() != Material.DRAGON_EGG)) return;

        Player p = event.getPlayer();

        Sign sign = (Sign) event.getBlock().getState();
        Block b = event.getBlock();

        event.setCancelled(true);
        b.setType(Material.AIR);

        b.getRelative(((WallSign) event.getBlock().getBlockData()).getFacing().getOppositeFace()).setType(Material.AIR);

        String placeLine = sign.getLine(0);
        String fieldLine = sign.getLine(1);

        ItemStack trophy = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.DRAGON_EGG), "&6&lTROPHY -", "&6" + placeLine + "\n&6" + fieldLine, false), 210, "CustomModelData");



        Location loc = b.getLocation();

        loc.getWorld().dropItem(loc, trophy);

        p.playSound(p.getLocation(), Sound.BLOCK_STONE_BREAK, 1, 2);

    }

    @EventHandler
    public void dragonEggInteractCanceller(PlayerInteractEvent event) {

        if (event.getAction() != Action.LEFT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null
        || event.getClickedBlock().getType() != Material.DRAGON_EGG) return;

        event.setCancelled(true);

    }
}