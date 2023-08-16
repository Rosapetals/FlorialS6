package net.florial.listeners;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.Florial;
import net.florial.features.thirst.HydrateEvent;
import net.florial.features.thirst.ThirstManager;
import net.florial.menus.CoatSelectionMenu;
import net.florial.models.PlayerData;
import net.florial.species.events.impl.SpeciesTablistEvent;
import net.florial.utils.Cooldown;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.VaultHandler;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
public class ClickablesListener implements Listener {

    private static final List<Integer> nbtData = List.of(

            201, 202, 203, 204, 205, 207, 208, 210, 45, 50, 2, 3, 400, 5, 13, 14, 7, 8, 81, 71, 12, 15, 16, 17, 18, 19, 20, 165, 21, 82, 83, 6
    );

    private static final CoatSelectionMenu coatSelector = new CoatSelectionMenu();

    @EventHandler
    public void clickableNoPlace(BlockPlaceEvent e) {

        if (e.getPlayer().getInventory().getItemInMainHand().getType() != Material.PLAYER_HEAD
        || (!(nbtData.contains(NBTEditor.getInt(e.getPlayer().getInventory().getItemInMainHand(), "CustomModelData"))))) return;

        e.setCancelled(true);

    }

    @EventHandler
    public void clickableUse(PlayerInteractEvent e) {

        if (e.getAction() != Action.LEFT_CLICK_AIR
            || e.getItem() == null
            || (!(nbtData.contains(NBTEditor.getInt(e.getItem(), "CustomModelData"))))) return;

        int value = NBTEditor.getInt(e.getItem(), "CustomModelData");

        switch(value) {
            case 201 -> tulipsShadow(e.getPlayer());
            case 202 -> healingOrb(e.getPlayer());
            case 203 -> infiniteCookie(e.getPlayer());
            case 204 -> waterJug(e.getPlayer());
            case 205 -> weatherManipulation(e.getPlayer());
            case 45, 2, 3, 5, 7, 12, 15, 16, 19, 20, 21, 82, 83 -> specialEat(e.getPlayer(), value);
            case 400 -> useMoneyVoucher(e.getPlayer());
            case 13, 14, 18 -> useDNAVoucher(e.getPlayer());
            case 81 -> useFloatie(e.getPlayer());
            case 50 -> gainFlories(e.getPlayer());
            case 71 -> coatSelectorUse(e.getPlayer());
            case 6 -> iceCubeUse(e.getPlayer());
            case 208 -> useSunScreen(e.getPlayer());
            case 207 -> mermaidTailUse(e.getPlayer());
            case 210 -> trophyRedeem(e.getPlayer(), e.getItem());
            case 165 -> e.getPlayer().getInventory().addItem(new ItemStack(Material.LIGHT));

        }
    }

    private static void trophyRedeem(Player p, ItemStack i) {

        if (!(ChatColor.stripColor(i.getLore().toString()).contains("WORTH"))) return;

        String worthLine = i.getLore().get(0);
        String placeLine = i.getLore().get(1);
        String fieldLine = i.getLore().get(2);

        int worthValue = Integer.parseInt(ChatColor.stripColor(worthLine).replace("WORTH: ", ""));

        i.setLore(Arrays.asList(placeLine, fieldLine));

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        data.setFlories(data.getFlories() + worthValue);

        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
        p.playSound(p.getLocation(), Sound.ENTITY_PILLAGER_CELEBRATE, 1, 2);



    }

    private static void useSunScreen(Player p) {

        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 3000, 0, false, false, true));

        p.playSound(p.getLocation(), Sound.BLOCK_SPORE_BLOSSOM_STEP, 1, 1);
        p.playSound(p.getLocation(), Sound.ITEM_BUCKET_FILL_POWDER_SNOW, 1, 1);

    }


    private static void iceCubeUse(Player p) {

        PlayerInventory inventory = p.getInventory();

        ItemStack key1 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lTulip Crate Key", "", false), 1, "CustomModelData");
        key1 = NBTEditor.set(key1, 1, "Crate");

        ItemStack key2 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lExperience Crate Key", "", false), 2, "CustomModelData");
        key2 = NBTEditor.set(key2, 2, "Crate");

        ItemStack key3 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lSeasonal Crate Key", "", false), 3, "CustomModelData");
        key3 = NBTEditor.set(key3, 3, "Crate");

        inventory.addItem(key1, key2, key3);

        removeItem(inventory.getItemInMainHand(), p);

        p.playSound(p.getLocation(), Sound.BLOCK_GLASS_FALL, 1, 2);
        p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 3);


    }


    private static void mermaidTailUse(Player p) {


        p.setVelocity(p.getLocation().getDirection().multiply(3));

        p.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 1200, 0, false, false, true));

        p.playSound(p.getLocation(), Sound.ENTITY_TROPICAL_FISH_HURT, 1, (float) 1);



    }

    private static void coatSelectorUse(Player p) {

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        if (data.getSpecieId() == 1) {

            coatSelector.coatSelectionMenu(p);
            p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 1, (float) 1);

            SpeciesTablistEvent e = new SpeciesTablistEvent(
                    p
            );
            Bukkit.getPluginManager().callEvent(e);
        }

    }


    private static void tulipsShadow(Player p) {

        if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            if (!(Cooldown.isOnCooldown("c3", p))) p.removePotionEffect(PotionEffectType.INVISIBILITY);

        } else {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 1, false, false, true));
            Cooldown.createCooldown("c3", p, 2);

        }
    }

    private static void healingOrb(Player p) {

        if (!(Cooldown.isOnCooldown("c4", p))) p.setHealth(p.getMaxHealth());
        Cooldown.createCooldown("c4", p, 240);
    }

    private static void waterJug(Player p) {

        HydrateEvent e = new HydrateEvent(
                p,
                p.getInventory().getItemInMainHand(),
                ThirstManager.getThirst(p),
                20

        );

        Bukkit.getPluginManager().callEvent(e);
    }

    private static void weatherManipulation(Player p){

        if (Cooldown.isOnCooldown("c4", p)) return;

        p.getWorld().setStorm(!p.getWorld().hasStorm());

        Cooldown.createCooldown("c4", p, 300);



    }

    private static void infiniteCookie(Player p) {

        p.setSaturation(20);
        p.setFoodLevel(20);
    }

    private static void specialEat(Player p, int value) {

        ItemStack item = p.getInventory().getItemInMainHand();

        if (value == 2 || value == 3) {
            if (item.getType() != Material.PAPER) return;
        }

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        removeItem(item, p);

        data.setDna(data.getDna() + 2);

        p.playSound(p.getLocation(), Sound.ITEM_HONEY_BOTTLE_DRINK, 1, (float) 8);

    }

    private static void gainFlories(Player p) {

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        data.setFlories(data.getFlories() + p.getInventory().getItemInMainHand().getAmount());

        p.getInventory().setItemInMainHand(null);

    }

    private static void removeItem(ItemStack heldItem, Player p) {

        if (heldItem.getAmount() > 1) {
            heldItem.setAmount(heldItem.getAmount() - 1);
        } else {
            p.getInventory().setItemInMainHand(null);
        }

    }

    private static void useMoneyVoucher(Player p) {

        ItemStack heldItem = p.getInventory().getItemInMainHand();

        removeItem(heldItem, p);

        String lore = heldItem.getItemMeta().getLore().get(0);
        lore = ChatColor.stripColor(lore);

        VaultHandler.addMoney(p, Integer.valueOf(lore));

    }

    private static void useDNAVoucher(Player p) {

        ItemStack heldItem = p.getInventory().getItemInMainHand();
        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        removeItem(heldItem, p);

        String lore = heldItem.getItemMeta().getLore().get(0);
        lore = ChatColor.stripColor(lore);

        data.setDna(data.getDna() + Integer.valueOf(lore));

    }

    private static void useFloatie(Player p) {

        if (p.getWorld().getBlockAt(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ())).getType() != Material.WATER) return;


        Block block = p.getLocation().getBlock().getRelative(BlockFace.DOWN); // get the block under the player
        Material originalMaterial = block.getType();
        block.setType(Material.SOUL_SAND);

        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> block.setType(originalMaterial), 60L);


    }

}
