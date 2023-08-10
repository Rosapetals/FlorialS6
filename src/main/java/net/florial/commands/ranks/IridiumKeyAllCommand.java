package net.florial.commands.ranks;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class IridiumKeyAllCommand extends BaseCommand {

    private static final HashMap<UUID, Boolean> didKeyAll = new HashMap<>();


    @CommandAlias("iridiumkeyall")
    @CommandPermission("iridiumplus")
    @Default
    public void iridiumKeyAll(Player p) {

        if (didKeyAll.get(p.getUniqueId()) != null && (didKeyAll.get(p.getUniqueId()))) {

            p.sendMessage((CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You are on cooldown for 1 day.")));
            if (!(p.hasPermission("op"))) return;

        }

        ItemStack key1 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lTulip Crate Key", "", false), 1, "CustomModelData");
        key1 = NBTEditor.set(key1, 1, "Crate");

        ItemStack key2 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lExperience Crate Key", "", false), 2, "CustomModelData");
        key2 = NBTEditor.set(key2, 2, "Crate");

        ItemStack key3 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lSeasonal Crate Key", "", false), 3, "CustomModelData");
        key3 = NBTEditor.set(key3, 3, "Crate");


        for (Player player : Bukkit.getOnlinePlayers()) {player.getInventory().addItem(key1, key2, key3);}

        p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);

        Bukkit.broadcastMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤#ff5b70 " + p.getName() + "&f just did a MASSIVE KEYALL! Give thanks!"));

        didKeyAll.put(p.getUniqueId(), true);


    }
}
