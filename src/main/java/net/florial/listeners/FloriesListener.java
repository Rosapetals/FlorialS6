package net.florial.listeners;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.Florial;
import net.florial.features.upgrades.Upgrade;
import net.florial.menus.SellMenu;
import net.florial.utils.Cooldown;
import net.florial.utils.general.CC;
import net.florial.utils.general.VaultHandler;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FloriesListener implements Listener {

    @EventHandler
    public void valHallaBlessing(EntityDamageEvent e) {

        if (e.getCause() != EntityDamageEvent.DamageCause.SUFFOCATION
                && e.getCause() != EntityDamageEvent.DamageCause.FALL) return;

        if (e.getEntity() instanceof Player
                && (Florial.getPlayerData().get(e.getEntity().getUniqueId()).getUpgrades() != null)
                && (Florial.getPlayerData().get(e.getEntity().getUniqueId())).getUpgrades().get(Upgrade.NATUREIMMUNITY) != null) e.setCancelled(true);

    }

    @EventHandler
    public void consumeTotem(PlayerItemConsumeEvent e) {

        Player p = e.getPlayer();
        ItemStack item = e.getItem();

        if (item.getType() != Material.TOTEM_OF_UNDYING
                || NBTEditor.getInt(item, "CustomModelData") != 206
                || Cooldown.isOnCooldown("c3", p)) return;

        Cooldown.addCooldown("c3", p, 600);
    }

    @EventHandler
    public void consumeTotem(PlayerInteractEvent e) {

        if (e.getAction() != Action.LEFT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_AIR) return;

        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();

        if (item.getType() != Material.PAPER
                || NBTEditor.getInt(item, "CustomModelData") != 10) return;

        if (e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.CHEST) {

            processSell(((Chest) e.getClickedBlock().getState()).getBlockInventory(), p);


        } else {

            processSell(p.getInventory(), p);


        }

    }

    private static void processSell(Inventory inventory, Player p) {

        int sellPrice = 0;

        for (ItemStack i : inventory.getContents()) {

            if (i == null || i.getType() == Material.AIR) continue;

            int value = 0;

            for (int t = 0; t < SellMenu.sellItems.size(); t++) {
                ItemStack sellItem = SellMenu.sellItems.get(t);
                if (sellItem.getType() == i.getType()) value = SellMenu.sellPrices.get(t);

            }

            if (value == 0) continue;

            sellPrice = sellPrice + value * i.getAmount();
            inventory.remove(i);

        }

        VaultHandler.addMoney(p, sellPrice);
        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Sold all for #ff1d3a$" + sellPrice));
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 2, (float) 2);



    }
}
