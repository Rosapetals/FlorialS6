package net.florial.menus;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.features.upgrades.Upgrade;
import net.florial.models.PlayerData;
import net.florial.utils.Cooldown;
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

    public static final List<ItemStack> sellItems = List.of(

            arrangeItem(3, new ItemStack(Material.ROTTEN_FLESH)),
            arrangeItem(5, new ItemStack(Material.MELON)),
            arrangeItem(5, new ItemStack(Material.FLINT)),
            arrangeItem(35, new ItemStack(Material.BROWN_MUSHROOM)),
            arrangeItem(35, new ItemStack(Material.RED_MUSHROOM)),
            arrangeItem(20, new ItemStack(Material.BLAZE_ROD)),
            arrangeItem(5, new ItemStack(Material.BONE)),
            arrangeItem(2, new ItemStack(Material.CLAY_BALL)),
            arrangeItem(11, new ItemStack(Material.CHARCOAL)),
            arrangeItem(25, new ItemStack(Material.COPPER_INGOT)),
            arrangeItem(5, new ItemStack(Material.COBBLED_DEEPSLATE)),
            arrangeItem(10, new ItemStack(Material.STONE)),
            arrangeItem(5, new ItemStack(Material.STRING)),
            arrangeItem(5, new ItemStack(Material.SALMON)),
            arrangeItem(5, new ItemStack(Material.COD)),
            arrangeItem(25, new ItemStack(Material.TROPICAL_FISH)),
            arrangeItem(10, new ItemStack(Material.PUFFERFISH)),
            arrangeItem(15, new ItemStack(Material.CACTUS))


    );

    public static final List<Integer> sellPrices = List.of(
            3, 5, 5, 35, 35, 20, 5, 2, 11, 25, 5, 10, 5,
            5, 5, 25, 10, 15


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

        if (Cooldown.isOnCooldown("menu", p)) return;
        int amount = 0;

        if (!(p.getInventory().contains(item.getType()))) {

            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 2);
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You don't have any of that item to sell!"));
            return;

        }

        Cooldown.addCooldown("menu", p, 1);

        for (ItemStack i : p.getInventory().getContents()) {
            if (i != null && i.getType() == item.getType()) {
                amount += i.getAmount();
            }
        }

        p.getInventory().remove(item.getType());

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        if (data.getUpgrades() != null && data.getUpgrades().get(Upgrade.SELLINCREASE) != null) amount = amount*2;

        VaultHandler.addMoney(p, amount * price);

        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 2);


    }

    public static ItemStack arrangeItem(int price, ItemStack i) {

        return CustomItem.MakeItem(i, "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", "  #5a372c&l︳ "
                + i.getType() + "\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙\n #6e4837&l︳ • SELL PER: #6e4837 "
                + price + "\n #5a372c&l︳  [CLICK HERE]:\n#5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false);

    }
}
