package net.florial.menus.shop;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ResourceShopMenu {

    private static final ShopMenu shop = new ShopMenu();

    private static final ItemStack[] resourceMaterials = {
            ShopMenu.arrangeItem(5000, Material.PUMPKIN_SEEDS),
            ShopMenu.arrangeItem(5000, Material.MELON_SEEDS),
            ShopMenu.arrangeItem(5000, Material.BEETROOT),
            ShopMenu.arrangeItem(5000, Material.POTATO),
            ShopMenu.arrangeItem(5000, Material.CARROT),
            ShopMenu.arrangeItem(5000, Material.SWEET_BERRIES),
            ShopMenu.arrangeItem(1000, Material.NETHER_WART),
            ShopMenu.arrangeItem(1000, Material.SPONGE),
            ShopMenu.arrangeItem(1000, Material.HONEYCOMB),
            ShopMenu.arrangeItem(1000, Material.NAME_TAG),
            ShopMenu.arrangeItem(500, Material.SLIME_BALL),
            ShopMenu.arrangeItem(500, Material.MAGMA_CREAM),
            ShopMenu.arrangeItem(500, Material.LEATHER),
            ShopMenu.arrangeItem(500, Material.BOOK),
            ShopMenu.arrangeItem(500, Material.LAPIS_LAZULI),
            ShopMenu.arrangeItem(500, Material.COAL),
            ShopMenu.arrangeItem(300, Material.GUNPOWDER),
            ShopMenu.arrangeItem(300, Material.SUGAR_CANE),
            ShopMenu.arrangeItem(300, Material.PAPER),
            ShopMenu.arrangeItem(100, Material.ROTTEN_FLESH),
            ShopMenu.arrangeItem(100, Material.GLOW_INK_SAC),
            ShopMenu.arrangeItem(50, Material.REDSTONE),
            ShopMenu.arrangeItem(50, Material.QUARTZ),
            ShopMenu.arrangeItem(50, Material.CLAY),
            ShopMenu.arrangeItem(50, Material.STRING),
            ShopMenu.arrangeItem(50, Material.FLINT),

    };

    private static final Integer[] resourcePrices = {
            5000, 5000, 5000, 5000, 5000, 5000,
            1000, 1000, 1000, 1000, 500, 500,
            500, 500, 500, 500, 300, 300, 300,
            100, 100, 50, 50, 50, 50, 50
    };



    public void resourceCategory(Player p) {

        p.closeInventory();

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE601"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        Iterator<ItemStack> materialIter = Arrays.asList(resourceMaterials).iterator();
                        Iterator<Integer> priceIter = Arrays.asList(resourcePrices).iterator();

                        contents.set(List.of(0,1,2), IntelligentItem.of(NBTEditor.set(CustomItem.MakeItem(
                                new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", "#ff79a1&lBACK\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙",
                        false), 1010, "CustomModelData"), event -> shop.shopMenu(p)));

                        contents.set(7, IntelligentItem.of(NBTEditor.set(CustomItem.MakeItem(
                                new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", "#ff79a1&lBULK BUY\n&f\n#ff79a1&l"
                                + (Florial.getBulkBuy().get(p.getUniqueId()) != null ? Florial.getBulkBuy().get(p.getUniqueId()) : "false") + "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙",
                                false), 1010, "CustomModelData"), event -> {
                                    ShopMenu.enableBulkBuy(p);
                                    resourceCategory(p);}));

                        for (int i = 9; i < 44; i++) {
                            if (materialIter.hasNext() && priceIter.hasNext()) {
                                ItemStack item = materialIter.next();
                                int price = priceIter.next();
                                contents.set(i, IntelligentItem.of(item, event -> ShopMenu.purchase(p, item.getType(), price)));
                            }
                        }

                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }

}
