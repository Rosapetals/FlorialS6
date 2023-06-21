package net.florial.commands.ranks;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KeyAllCommand extends BaseCommand {



    @CommandAlias("keyall")
    @CommandPermission("op")
    @Default
    public static void iridiumKeyAll(CommandSender sender, @Optional @Flags("other") Player target, int amount) {

        ItemStack key1 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lTulip Crate Key", "", false), 1, "CustomModelData");
        key1 = NBTEditor.set(key1, 1, "Crate");

        ItemStack key2 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lExperience Crate Key", "", false), 2, "CustomModelData");
        key2 = NBTEditor.set(key2, 2, "Crate");

        ItemStack key3 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lSeasonal Crate Key", "", false), 3, "CustomModelData");
        key3 = NBTEditor.set(key3, 3, "Crate");

        for (Player player : Bukkit.getOnlinePlayers()) {
            target.playSound(target.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);
            for (int i = 0; i < amount; i++) {
                player.getInventory().addItem(key1, key2, key3);
            }

        }

        Bukkit.broadcastMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤#ff5b70 " + target.getName() + "&f just did a MASSIVE KEYALL for " + amount + " keys! Give thanks!"));


    }
}
