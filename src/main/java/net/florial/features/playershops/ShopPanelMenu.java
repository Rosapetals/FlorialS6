package net.florial.features.playershops;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class ShopPanelMenu {

    private static final OnlinePlayersMenu onlinePlayersMenu = new OnlinePlayersMenu();


    public void open(Player p) {

        p.closeInventory();

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE631"))
                .rows(1)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 2);


                        contents.set(List.of(0,1,2), IntelligentItem.of(NBTEditor.set(CustomItem.MakeItem(
                                new ItemStack(Material.MAP), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", "#5a372c&lADD A PLAYER TO SHOP\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙",
                                false), 1010, "CustomModelData"), event -> showOnlinePlayers(p, true)));
                        contents.set(List.of(4,5), IntelligentItem.of(NBTEditor.set(CustomItem.MakeItem(
                                new ItemStack(Material.MAP), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", "#5a372c&lREMOVE A PLAYER TO SHOP\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙",
                                false), 1010, "CustomModelData"), event -> showOnlinePlayers(p, false)));
                        contents.set(List.of(6,7,8), IntelligentItem.of(NBTEditor.set(CustomItem.MakeItem(
                                new ItemStack(Material.MAP), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", "#5a372c&lADD TAGLINE\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙",
                                false), 1010, "CustomModelData"), event -> addTag(p)));

                    }
                })
                .build(Florial.getInstance())
                .open(p);
    }



    private static void showOnlinePlayers(Player p, boolean adding) {
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);
        p.closeInventory();
        onlinePlayersMenu.open(p, adding);
    }

    private static void addTag(Player p) {

        UUID u = p.getUniqueId();
        p.closeInventory();
        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Type your message in chat for a tagline!"));
        Florial.getBoardLocation().put(u, Florial.getBoardLocation().get(u));
    }
}
