package net.florial.menus;

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

public class ResourceShopMenu {

    private static final List<ItemStack> resourceMaterials = List.of(
            arrangeItem(10, Material.DIAMOND_ORE),
            arrangeItem(25, Material.COAL_ORE),
            arrangeItem(50, Material.IRON_ORE),
            arrangeItem(75, Material.EMERALD_ORE),
            arrangeItem(100, Material.LAPIS_ORE));

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

    private static ItemStack arrangeItem(int price, Material mat) {

        return CustomItem.MakeItem(new ItemStack(mat), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", "  #ff79a1&l︳ "
                + mat + "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳ • PRICE: #ffa2c4 "
                + price + "\n #ff79a1&l︳  [CLICK HERE]:\n#ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false);

    }
}
