package net.florial.menus;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.commands.DuelCommand;
import net.florial.features.duels.Duel;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class DuelMenu {


    public void activate(Player p) {

        p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 2, 1);

        RyseInventory.builder()
                .title(CC.translate("#5a372c&lCHOOSE A DUEL TYPE"))
                .rows(1)
                .provider(new InventoryProvider() {
                    

                    @Override
                    public void init(Player player, InventoryContents contents) {


                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(new ItemStack(Material.GREEN_TERRACOTTA), "&2&lCASUAL", "&aA simple duel. No risks.", true),
                                        CustomItem.MakeItem(new ItemStack(Material.RED_TERRACOTTA), "&4&lRISKY", "&cWager items and duel for them.\n&cBoth parties must wager items and then\n&c a final confirmation must be made.", true)).map(i -> NBTEditor.set(i, 1010, "CustomModelData"))
                                .toList();

                        contents.set(List.of(3), IntelligentItem.of(entries.get(0), event ->{ initiateDuel(p, false);}));
                        contents.set(List.of(5), IntelligentItem.of(entries.get(1), event ->{ initiateDuel(p, true);}));

                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }


    private static void initiateDuel(Player p, boolean isRisky) {

        p.closeInventory();
        p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 2, 1);

        UUID u = p.getUniqueId();

        Duel duel = Florial.getOngoingDuel().get(u);

        UUID targetUUID = duel.getOpponentWhoWasInvited();


        if (isRisky) {
            duel.setOpponentWhoInvitedWageredItem(new ItemStack(Material.AIR));
            duel.setOpponentWhoWasInvitedWageredItem(new ItemStack(Material.AIR));
        }

        Player target = Bukkit.getPlayer(targetUUID);

        if (!(Florial.getOngoingDuel().get(u).passNullCheck())) {
            return;
        }

        target.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤#ff3c55 " + p.getName() + " has sent you a duel request for a #ff3c55" + (isRisky ? "RISKY" : "CASUAL" + "&f duel. Do #ff3c55/duel accept&f to accept it or /duel deny to deny it.")));
        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully sent the request."));

    }
}
