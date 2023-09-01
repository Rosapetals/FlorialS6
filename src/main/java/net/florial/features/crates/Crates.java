package net.florial.features.crates;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.Florial;
import net.florial.models.SeasonalCrateItem;
import net.florial.utils.game.FireWorkSpawner;
import net.florial.utils.general.CustomItem;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.*;
import java.util.stream.Collectors;


public class Crates implements Listener {

    private static final Map<Integer, List<ItemStack>> crateDrops = new HashMap<>();
    private static final List<Location> crateLocations = List.of(
            new Location(Bukkit.getWorld("world"), 6935, 73, 7168),
            new Location(Bukkit.getWorld("world"), 6938, 73, 7167),
            new Location(Bukkit.getWorld("world"), 6941, 73, 7170),
            new Location(Bukkit.getWorld("world"), 6940, 73, 7173));


    static {

        ItemStack key1 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lTulip Crate Key", "", false), 1, "CustomModelData");
        key1 = NBTEditor.set(key1, 1, "Crate");

        ItemStack key2 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lExperience Crate Key", "", false), 2, "CustomModelData");
        key2 = NBTEditor.set(key2, 2, "Crate");

        ItemStack key3 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#fb4e0e&lA#fb5418&lu#fc5a21&lt#fc602b&lu#fc6634&lm#fd6c3e&ln #fd7248&lC#fd7851&lr#fd7e5b&la#fe8465&lt#fe8a6e&le #fe9078&lK#ff9681&le#ff9c8b&ly", "", false), 3, "CustomModelData");
        key3 = NBTEditor.set(key3, 3, "Crate");

        ItemStack key4 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lVote Crate Key", "", false), 4, "CustomModelData");
        key4 = NBTEditor.set(key4, 4, "Crate");

        crateDrops.put(1, List.of(

                key2,
                key3,
                key4,

                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER), "#ff7a8b&lMoney Voucher", "#ff7a8b&l800", false), 4, "CustomModelData"),
                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER), "#ff7a8b&lMoney Voucher", "#ff7a8b&l500", false), 4, "CustomModelData"),

                new ItemStack(Material.DIAMOND),
                new ItemStack(Material.QUARTZ, 10),

                new ItemStack(Material.CAKE)



                ));

        crateDrops.put(2, List.of(
                new ItemStack(Material.EXPERIENCE_BOTTLE, 120),
                new ItemStack(Material.EXPERIENCE_BOTTLE, 300),
                new ItemStack(Material.EXPERIENCE_BOTTLE, 600),
                new ItemStack(Material.EXPERIENCE_BOTTLE, 64),

                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.MUSIC_DISC_CAT), "#ff7a8b&lDNA Voucher", "#ff7a8b&l5", false), 13, "CustomModelData"),
                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.MUSIC_DISC_CAT), "#ff7a8b&lDNA Voucher", "#ff7a8b&l5", false), 14, "CustomModelData"),

                new ItemStack(Material.GOLDEN_APPLE, 64),
                new ItemStack(Material.GOLDEN_APPLE, 50),
                new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 10)

                ));

        crateDrops.put(3, List.of(

                key1,

                Objects.requireNonNull(SeasonalCrateItem.fromID(1, 1)),
                Objects.requireNonNull(SeasonalCrateItem.fromID(2, 1)),
                Objects.requireNonNull(SeasonalCrateItem.fromID(3, 1)),
                Objects.requireNonNull(SeasonalCrateItem.fromID(4, 1)),
                Objects.requireNonNull(SeasonalCrateItem.fromID(5, 1)),
                Objects.requireNonNull(SeasonalCrateItem.fromID(6, 1)),
                Objects.requireNonNull(SeasonalCrateItem.fromID(7, 1)),
                Objects.requireNonNull(SeasonalCrateItem.fromID(9, 1)),
                Objects.requireNonNull(SeasonalCrateItem.fromID(10, 1)),
                Objects.requireNonNull(SeasonalCrateItem.fromID(11, 1)),
                Objects.requireNonNull(SeasonalCrateItem.fromID(12, 1)),
                Objects.requireNonNull(SeasonalCrateItem.fromID(13, 1)),
                Objects.requireNonNull(SeasonalCrateItem.fromID(14, 1)),




                new ItemStack(Material.PUMPKIN, 30),
                new ItemStack(Material.SWEET_BERRIES, 64),

                new ItemStack(Material.GOLDEN_APPLE, 64),

                new ItemStack(Material.PINK_SHULKER_BOX, 5),
                new ItemStack(Material.PUMPKIN_PIE, 5),
                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER), "#ff7a8b&lMoney Voucher", "#ff7a8b&l800", false), 4, "CustomModelData"),



                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER, 2), "#ff7a8b&lFlories [Left-Click]", "", false), 401, "CustomModelData"),


                key2,

                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER, 1), "#ff7a8b&lFlories [Left-Click]", "", false), 401,"CustomModelData")


        ));


        crateDrops.put(4, List.of(

                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER, 1), "#ff7a8b&lFlories [Left-Click]", "", false), 401,"CustomModelData"),

                new ItemStack(Material.GOLDEN_APPLE, 10),
                new ItemStack(Material.EXPERIENCE_BOTTLE, 600),
                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.MUSIC_DISC_CAT), "#ff7a8b&lDNA Voucher", "#ff7a8b&l2", false), 14, "CustomModelData"),

                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER), "#ff7a8b&lMoney Voucher", "#ff7a8b&l3000", false), 4, "CustomModelData"),
                NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER), "#ff7a8b&lMoney Voucher", "#ff7a8b&l7000", false), 4, "CustomModelData"),

                key1

                ));

    }


    @EventHandler
    public void crateOpen(PlayerInteractEvent e) {

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK ||
                !(crateLocations.contains((Objects.requireNonNull(e.getClickedBlock()).getLocation())))) return;

        ItemStack heldItem = e.getPlayer().getInventory().getItemInMainHand();

        if (!NBTEditor.contains(heldItem, "Crate")) return;

        int value = NBTEditor.getInt(heldItem, "Crate");

        if (crateLocations.get(0).equals(e.getClickedBlock().getLocation()) && value != 1) return;
        if (crateLocations.get(1).equals(e.getClickedBlock().getLocation()) && value != 2) return;
        if (crateLocations.get(2).equals(e.getClickedBlock().getLocation()) && value != 3) return;
        if (crateLocations.get(3).equals(e.getClickedBlock().getLocation()) && value != 4) return;


        List<ItemStack> openingDrops = crateDrops.get(NBTEditor.getInt(heldItem, "Crate"));

        Player p = e.getPlayer();

        if (heldItem.getAmount() > 1) {
            heldItem.setAmount(heldItem.getAmount() - 1);
        } else {
            p.getInventory().setItemInMainHand(null);
        }

        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);

        Random rand = new Random();

        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> {

            for (int i = 0; i < 3; i++) {
                checkItems(p, true, List.of(openingDrops.get(rand.nextInt(openingDrops.size()))));
            }

            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, 2);
            FireWorkSpawner.spawn(3, Color.ORANGE, Color.RED, p);


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
