package net.florial.listeners;

import net.florial.utils.game.RegionDetector;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColonyResourceListener implements Listener {

    private static final List<String> regionLocations = List.of(

            "catcamp",
            "foxcamp",
            "humancamp",
            "burrowcamp"

    );

    private static final Map<Integer, List<Material>> regionMaterials = new HashMap<>();

    static {

        regionMaterials.put(0, List.of(

                Material.SNOW_BLOCK,
                Material.SPRUCE_LOG,
                Material.MUSHROOM_STEM

        ));

        regionMaterials.put(1, List.of(

                Material.COARSE_DIRT,
                Material.RED_MUSHROOM_BLOCK,
                Material.PODZOL

        ));

        regionMaterials.put(2, List.of(

                Material.DARK_OAK_LOG,
                Material.COBBLESTONE,
                Material.VINE

        ));

        regionMaterials.put(3, List.of(

                Material.SAND,
                Material.GRAVEL,
                Material.BLACKSTONE

        ));
    }

    @EventHandler
    public void grindMaterial(BlockBreakEvent e) {

        if (!RegionDetector.detect(e.getPlayer().getLocation()).contains("camp")) return;

        Player p = e.getPlayer();

        if (p.getGameMode() == GameMode.CREATIVE) return;

        int index = 0;

        for (int i = 0; i < regionLocations.size(); i++) {if (RegionDetector.detect(p.getLocation()).contains(regionLocations.get(i))) index = i;}

        if (regionMaterials.get(index).contains(e.getBlock().getType())) {

            p.getInventory().addItem(new ItemStack(e.getBlock().getType()));
            p.playSound(p.getLocation(), Sound.BLOCK_ROOTED_DIRT_STEP, 2, (float) 1);
            e.setCancelled(true);

        } else if (p.getGameMode() == GameMode.SURVIVAL) {
            e.setCancelled(true);
        }




    }

}
