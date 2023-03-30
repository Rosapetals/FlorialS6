package net.florial.menus.shop;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.utils.general.CC;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ResourceShopMenu {

    private static final List<ItemStack> resourceMaterials = List.of(
            ShopMenu.arrangeItem(10, Material.DIAMOND_ORE),
            ShopMenu.arrangeItem(25, Material.COAL_ORE),
            ShopMenu.arrangeItem(50, Material.IRON_ORE),
            ShopMenu.arrangeItem(75, Material.EMERALD_ORE),
            ShopMenu.arrangeItem(100, Material.LAPIS_ORE));

    private static final List<Integer> resourcePrices = List.of(
            10, 25, 50, 74, 100);



    public void resourceCategory(Player p) {

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE601"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        int thisSlot = 0;

                        for (ItemStack item : resourceMaterials) {
                            int finalIt = thisSlot;
                            contents.set(List.of(thisSlot), IntelligentItem.of(item, event ->
                                    buyMenu(p, item.getType(), resourcePrices.get(finalIt))));
                            thisSlot++;
                        }

                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }

    private static void buyMenu(Player p, Material mat, int price) {

        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);

        p.closeInventory();

    }
}
