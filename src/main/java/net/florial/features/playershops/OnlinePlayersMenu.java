package net.florial.features.playershops;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class OnlinePlayersMenu {


    public void open(Player p, boolean adding) {

        p.closeInventory();

        RyseInventory.builder()
                .title(CC.translate("#5a372c&lCHOOSE A PLAYER"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 2);

                        int i = 0;

                        for (Player p : Bukkit.getOnlinePlayers()) {

                            contents.set(i, IntelligentItem.of(NBTEditor.set(CustomItem.MakeItem(
                                    allocateHead(p), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", "#5a372c&l " + (adding ? "ADD" : "REMOVE") + " " + p.getName() + "\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙",
                                    false), 1010, "CustomModelData"), event -> changePlayer(player, adding, p)));
                            i++;

                        }

                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }


    private static void changePlayer(Player p, boolean adding, Player target) {

        p.closeInventory();

        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 2);

        for (Location shopLocation : PlayerShopsMenu.playerShops) {

            Sign sign = (Sign) shopLocation.getBlock().getState();

            if (sign.getLine(1).contains(p.getName())) {

                String regionName = sign.getLine(0).replaceAll(" ", "");

                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg " + (adding ? "add" : "remove") + "member " + regionName + " " + target.getName() + " -w world");

                p.sendMessage(CC.translate("#fdd8d8&lTULIP&f➤&c Okay I did it for ya, chief! " + target.getName() + " can " +  (adding ? " now place blocks here" : " no longer place or break blocks here")) + ". Hope I helped!");

                break;
            }

        }



    }

    private static ItemStack allocateHead(Player p) {

        ItemStack skullItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();

        skullMeta.setOwningPlayer(p);

        skullItem.setItemMeta(skullMeta);

        return skullItem;
    }
}
