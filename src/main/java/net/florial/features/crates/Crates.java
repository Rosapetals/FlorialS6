package net.florial.features.crates;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.Florial;
import net.florial.utils.game.FireWorkSpawner;
import net.florial.utils.general.CustomItem;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.List;


public class Crates implements Listener {

    private static final Map<Integer, List<ItemStack>> crateDrops = new HashMap<>();
    private static final Location[] crateLocations = {
            new Location(Bukkit.getWorld("world"), 6935, 73, 7168),
            new Location(Bukkit.getWorld("world"), 6938, 73, 7167),
            new Location(Bukkit.getWorld("world"), 6941, 73, 7170),
            new Location(Bukkit.getWorld("world"), 6940, 73, 7173)

    };

    static {

        ItemStack key1 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lTulip Crate Key", "", false), 1, "CustomModelData");
        key1 = NBTEditor.set(key1, 1, "Crate");

        ItemStack key2 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lExperience Crate Key", "", false), 2, "CustomModelData");
        key2 = NBTEditor.set(key2, 2, "Crate");

        ItemStack key3 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lSeasonal Crate Key", "", false), 3, "CustomModelData");
        key3 = NBTEditor.set(key3, 3, "Crate");

        ItemStack key4 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lVote Crate Key", "", false), 4, "CustomModelData");
        key4 = NBTEditor.set(key4, 4, "Crate");

        crateDrops.put(1, List.of(

                key2,
                key3,
                key4,

                CustomItem.MakeItem(new ItemStack(Material.SUNFLOWER, 10), "#ff7a8b&lFlories [Left-Click]", "", false),

                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER), "#ff7a8b&lMoney Voucher", "#ff7a8b&l50000", false), 10, "CustomModelData"),
                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER), "#ff7a8b&lMoney Voucher", "#ff7a8b&l30000", false), 11, "CustomModelData"),
                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER), "#ff7a8b&lMoney Voucher", "#ff7a8b&l10000", false), 12, "CustomModelData"),

                new ItemStack(Material.DIAMOND),
                new ItemStack(Material.QUARTZ, 10),

                new ItemStack(Material.CAKE)



                ));

        crateDrops.put(2, List.of(
                new ItemStack(Material.EXPERIENCE_BOTTLE, 120),
                new ItemStack(Material.EXPERIENCE_BOTTLE, 300),
                new ItemStack(Material.EXPERIENCE_BOTTLE, 600),
                new ItemStack(Material.EXPERIENCE_BOTTLE, 64),

                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.MUSIC_DISC_CAT), "#ff7a8b&lDNA Voucher", "#ff7a8b&l50", false), 13, "CustomModelData"),
                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.MUSIC_DISC_CAT), "#ff7a8b&lDNA Voucher", "#ff7a8b&l20", false), 14, "CustomModelData"),

                new ItemStack(Material.GOLDEN_APPLE, 64),
                new ItemStack(Material.GOLDEN_APPLE, 50),
                new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 10)

                ));

        crateDrops.put(3, List.of(

                key1,

                new ItemStack(Material.EGG, 30),
                new ItemStack(Material.EGG, 64),

                new ItemStack(Material.GOLDEN_APPLE, 64),

                new ItemStack(Material.PINK_SHULKER_BOX, 5),


                key2,

                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER), "#ff7a8b&lChocolate Egg", "", false), 47,"CustomModelData"),
                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER), "#ff7a8b&lEaster Egg", "", false), 48,"CustomModelData"),
                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.BUNDLE), "#ff7a8b&lEaster Basket", "", false), 46,"CustomModelData")



                ));

        crateDrops.put(4, List.of(

                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.SUNFLOWER), "#ff7a8b&lFlories [Left-Click]", "", false), 50,"CustomModelData"),

                new ItemStack(Material.GOLDEN_APPLE, 10),
                new ItemStack(Material.EXPERIENCE_BOTTLE, 600),
                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.MUSIC_DISC_CAT), "#ff7a8b&lDNA Voucher", "#ff7a8b&l10", false), 14, "CustomModelData"),

                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER), "#ff7a8b&lMoney Voucher", "#ff7a8b&l10000", false), 11, "CustomModelData"),
                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER), "#ff7a8b&lMoney Voucher", "#ff7a8b&l25000", false), 11, "CustomModelData"),

                key1

                ));

    }


    @EventHandler
    public void crateOpen(PlayerInteractEvent e) {

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK ||
                !(Arrays.asList(crateLocations).contains((Objects.requireNonNull(e.getClickedBlock()).getLocation())))) return;

        ItemStack heldItem = e.getPlayer().getInventory().getItemInMainHand();

        if (!NBTEditor.contains(heldItem, "Crate")) return;

        List<ItemStack> openingDrops = crateDrops.get(NBTEditor.getInt(heldItem, "Crate"));

        Player p = e.getPlayer();

        if (heldItem.getAmount() > 1) {
            heldItem.setAmount(heldItem.getAmount() - 1);
        } else {
            p.getInventory().remove(heldItem);
        }

        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);


        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> {

            checkItems(p, true, List.of(openingDrops.get(0)));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, 2);
            FireWorkSpawner.spawn(3, Color.FUCHSIA, Color.WHITE, p);


        }, 40);


    }



    public static void checkItems(Player player, boolean requireAll, List<ItemStack> items) {

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
