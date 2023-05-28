package net.florial.commands.ranks;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.VaultHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class RankGiveCommand extends BaseCommand {

    @CommandAlias("rankgive")
    @CommandPermission("op")
    public void giveRank(CommandSender sender, @Optional @Flags("other") Player arg1, String s) {


        switch(s) {
            case "vip" -> giveRewards(arg1, 200, 15000, 0,"vip");
            case "diamond" -> giveRewards(arg1, 500, 25000, 10,"diamond");
            case "iridium" -> giveRewards(arg1, 600, 50000, 20,"iridium");
            case "iridiumplus" -> giveRewards(arg1, 700, 150000, 25,"iridium++");
            case "flourite" ->  giveRewards(arg1, 800, 400000, 50,"flourite");
            case "pearlite" -> giveRewards(arg1, 10000, 1000000, 64,"pearlite");

        }
    }

    private static void giveRewards(Player p, int dna, int money, int keys, String rank) {

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        if (keys != 0) {

            ItemStack key1 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE, keys), "#ff7a8b&lTulip Crate Key", "", false), 1, "CustomModelData");
            ItemStack key2 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE, keys), "#ff7a8b&lExperience Crate Key", "", false), 2, "CustomModelData");
            ItemStack key3 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE, keys), "#ff7a8b&lSeasonal Crate Key", "", false), 3, "CustomModelData");

            p.getInventory().addItem(NBTEditor.set(key1, 1, "Crate"),
                    NBTEditor.set(key2, 2, "Crate"),
                    NBTEditor.set(key3, 3, "Crate"));

        }

        data.setDna(data.getDna() + dna);
        VaultHandler.addMoney(p, money);
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " parent add " + rank);
        if (rank.contains("pearlite")) data.setFlories(data.getFlories()+350);




    }
}
