package net.florial.commands.ranks;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.VaultHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class PearliteCommand extends BaseCommand {

    private static final HashMap<UUID, Boolean> didCommand = new HashMap<>();


    @CommandAlias("pearlite")
    @CommandPermission("pearlite")
    @Default
    public void pearliteCommand(Player p) {

        if (didCommand.get(p.getUniqueId()) != null && (didCommand.get(p.getUniqueId()))) {

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

        VaultHandler.addMoney(p, 15000);

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());
        data.setDna(data.getDna() + 20);
        data.setFlories(data.getFlories() + 10);

        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, 3);


        didCommand.put(p.getUniqueId(), true);


    }
}