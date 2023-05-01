package net.florial.listeners;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.utils.general.CustomItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {


    @EventHandler
    public void pickDandelions(BlockBreakEvent e) {

        if (e.getBlock().getType() != Material.DANDELION) return;

        e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER), "&eDandelion Seeds", "&e&oCraft with bonemeal.", false), 6,"CustomModelData"));
    }
}
