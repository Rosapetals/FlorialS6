package net.florial.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TrophyCommand extends BaseCommand {

    @CommandAlias("maketrophy")
    @CommandPermission("eventlead")
    public void onTrophyMake(Player p, @Optional @Flags("other") Player target, int reward, String event) {

        p.sendMessage((CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f The trophy was given to #ff3c55" + target.getName())));

        target.getInventory().addItem(NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.DRAGON_EGG), "&6&lTROPHY -", "&6WORTH: " + reward + "\n&6FIRST PLACE\n&6" + event, false), 210, "CustomModelData"));


    }

}
