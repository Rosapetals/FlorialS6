package net.florial.features.crates;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Crates implements Listener {

    private static final Map<Integer, List<ItemStack>> crateDrops = new HashMap<>();
    private static final Location[] crateLocations = {
            new Location(Bukkit.getWorld("world"), 66, 64, -319)
    };

    static {

        crateDrops.put(1, List.of(



        ));

    }


    @EventHandler
    public void crateOpen(PlayerInteractEvent e) {

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK ||
                !(Arrays.asList(crateLocations).contains((Objects.requireNonNull(e.getClickedBlock()).getLocation())))) return;

        ItemStack heldItem = e.getPlayer().getInventory().getItemInMainHand();

        if (!NBTEditor.contains(heldItem, "Crate")) return;

        int crateType = NBTEditor.getInt(heldItem, "Crate");

        List<ItemStack> openingDrops = crateDrops.get(crateType);


    }





    public static void checkItems(Player player, boolean requireAll, ItemStack[] items) {
        for (ItemStack item : items) {
            if (player.getInventory().firstEmpty() == -1) {
                if (requireAll || !player.getInventory().contains(item)) {
                    player.getWorld().dropItem(player.getLocation().add(0, 1, 0), item);
                }
            } else {
                player.getInventory().addItem(item);
            }
        }
    }



}
