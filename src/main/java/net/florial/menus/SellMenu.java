package net.florial.menus;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.features.upgrades.Upgrade;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.VaultHandler;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.List;

public class SellMenu {

    private static final List<ItemStack> sellItems = List.of(

            arrangeItem(3, new ItemStack(Material.ROTTEN_FLESH)),
            arrangeItem(5, new ItemStack(Material.MELON)),
            arrangeItem(5, new ItemStack(Material.IRON_INGOT)),
            arrangeItem(35, new ItemStack(Material.BROWN_MUSHROOM)),
            arrangeItem(35, new ItemStack(Material.RED_MUSHROOM)),
            arrangeItem(50, new ItemStack(Material.BLAZE_ROD)),
            arrangeItem(5, new ItemStack(Material.BONE)),
            arrangeItem(10, new ItemStack(Material.GOLD_INGOT)),
            arrangeItem(10, new ItemStack(Material.CHARCOAL))





            );

    private static final List<Integer> sellPrices = List.of(
            3, 5, 5, 35, 35, 50, 5, 10, 10


    );


    public void sellMenu(Player p) {

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE610"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        Iterator<ItemStack> materialIter = (sellItems).iterator();
                        Iterator<Integer> priceIter = (sellPrices).iterator();

                        for (int i = 18; i < 35; i++) {
                            if (materialIter.hasNext() && priceIter.hasNext()) {
                                ItemStack item = materialIter.next();
                                int price = priceIter.next();
                                contents.set(i, IntelligentItem.of(item, event -> sell(p, item, price)));
                            }
                        }



                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }

    private static void sell(Player p, ItemStack item, int price) {

        p.closeInventory();

        int amount = 0;

        if (!(p.getInventory().contains(item.getType()))) {

            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 2);
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You don't have any of that item to sell!"));
            return;

        }

        for (ItemStack i : p.getInventory().getContents()) {
            if (i != null && i.getType() == item.getType()) {
                amount += i.getAmount();
            }
        }

        p.getInventory().remove(item.getType());

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        if (data.getUpgrades() != null && data.getUpgrades().get(Upgrade.SELLINCREASE)) amount = amount*2;

        VaultHandler.addMoney(p, amount * price);
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 2);


    }

    public static ItemStack arrangeItem(int price, ItemStack i) {

        return CustomItem.MakeItem(i, "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", "  #ff79a1&l︳ "
                + i.getType() + "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳ • SELL PER: #ffa2c4 "
                + price + "\n #ff79a1&l︳  [CLICK HERE]:\n#ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false);

    }
}
