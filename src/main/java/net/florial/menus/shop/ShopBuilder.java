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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ShopBuilder {


    private static final Map<Integer, List<ItemStack>> materialLists = new HashMap<>();
    private static final Map<Integer, List<Integer>> priceLists = new HashMap<>();

    static {

        materialLists.put(1, List.of(

                ShopMenu.arrangeItem(5000, new ItemStack(Material.PUMPKIN_SEEDS)),
                ShopMenu.arrangeItem(5000, new ItemStack(Material.MELON_SEEDS)),
                ShopMenu.arrangeItem(5000, new ItemStack(Material.BEETROOT)),
                ShopMenu.arrangeItem(5000, new ItemStack(Material.POTATO)),
                ShopMenu.arrangeItem(5000, new ItemStack(Material.CARROT)),
                ShopMenu.arrangeItem(5000, new ItemStack(Material.SWEET_BERRIES)),
                ShopMenu.arrangeItem(1000, new ItemStack(Material.NETHER_WART)),
                ShopMenu.arrangeItem(1000, new ItemStack(Material.SPONGE)),
                ShopMenu.arrangeItem(1000, new ItemStack(Material.HONEYCOMB)),
                ShopMenu.arrangeItem(1000, new ItemStack(Material.NAME_TAG)),
                ShopMenu.arrangeItem(500, new ItemStack(Material.SLIME_BALL)),
                ShopMenu.arrangeItem(500, new ItemStack(Material.MAGMA_CREAM)),
                ShopMenu.arrangeItem(500, new ItemStack(Material.LEATHER)),
                ShopMenu.arrangeItem(500, new ItemStack(Material.BOOK)),
                ShopMenu.arrangeItem(500, new ItemStack(Material.LAPIS_LAZULI)),
                ShopMenu.arrangeItem(500, new ItemStack(Material.COAL)),
                ShopMenu.arrangeItem(300, new ItemStack(Material.GUNPOWDER)),
                ShopMenu.arrangeItem(300, new ItemStack(Material.SUGAR_CANE)),
                ShopMenu.arrangeItem(300, new ItemStack(Material.PAPER)),
                ShopMenu.arrangeItem(100, new ItemStack(Material.ROTTEN_FLESH)),
                ShopMenu.arrangeItem(100, new ItemStack(Material.GLOW_INK_SAC)),
                ShopMenu.arrangeItem(50, new ItemStack(Material.REDSTONE)),
                ShopMenu.arrangeItem(50, new ItemStack(Material.QUARTZ)),
                ShopMenu.arrangeItem(50, new ItemStack(Material.CLAY)),
                ShopMenu.arrangeItem(50, new ItemStack(Material.STRING)),
                ShopMenu.arrangeItem(50, new ItemStack(Material.FLINT))
        ));
        priceLists.put(1, List.of(
                5000, 5000, 5000, 5000, 5000, 5000,
                1000, 1000, 1000, 1000, 500, 500,
                500, 500, 500, 500, 300, 300, 300,
                100, 100, 50, 50, 50, 50, 50
        ));

        materialLists.put(2, List.of(
                ShopMenu.arrangeItem(100000, book(Enchantment.MENDING)),
                ShopMenu.arrangeItem(75000, book(Enchantment.PROTECTION_ENVIRONMENTAL)),
                ShopMenu.arrangeItem(50000, book(Enchantment.LOOT_BONUS_BLOCKS)),
                ShopMenu.arrangeItem(50000, book(Enchantment.DAMAGE_ALL)),
                ShopMenu.arrangeItem(50000, book(Enchantment.LOOT_BONUS_MOBS)),
                ShopMenu.arrangeItem(50000, book(Enchantment.ARROW_INFINITE)),
                ShopMenu.arrangeItem(50000, book(Enchantment.RIPTIDE)),
                ShopMenu.arrangeItem(50000, book(Enchantment.THORNS)),
                ShopMenu.arrangeItem(50000, book(Enchantment.MULTISHOT)),
                ShopMenu.arrangeItem(50000, book(Enchantment.PIERCING)),
                ShopMenu.arrangeItem(50000, book(Enchantment.ARROW_KNOCKBACK)),
                ShopMenu.arrangeItem(50000, book(Enchantment.ARROW_DAMAGE)),
                ShopMenu.arrangeItem(50000, book(Enchantment.WATER_WORKER)),
                ShopMenu.arrangeItem(50000, book(Enchantment.QUICK_CHARGE)),
                ShopMenu.arrangeItem(50000, book(Enchantment.SWIFT_SNEAK)),
                ShopMenu.arrangeItem(50000, book(Enchantment.LOYALTY)),
                ShopMenu.arrangeItem(50000, book(Enchantment.PROTECTION_FALL)),
                ShopMenu.arrangeItem(30000, book(Enchantment.KNOCKBACK)),
                ShopMenu.arrangeItem(30000, book(Enchantment.SWEEPING_EDGE)),
                ShopMenu.arrangeItem(30000, book(Enchantment.BINDING_CURSE)),
                ShopMenu.arrangeItem(30000, book(Enchantment.VANISHING_CURSE)),
                ShopMenu.arrangeItem(30000, book(Enchantment.OXYGEN)),
                ShopMenu.arrangeItem(25000, book(Enchantment.SILK_TOUCH)),
                ShopMenu.arrangeItem(100000, book(Enchantment.FIRE_ASPECT)),
                ShopMenu.arrangeItem(25000, book(Enchantment.DEPTH_STRIDER)),
                ShopMenu.arrangeItem(25000, book(Enchantment.SOUL_SPEED)),
                ShopMenu.arrangeItem(15000, book(Enchantment.DURABILITY)),
                ShopMenu.arrangeItem(15000, book(Enchantment.DIG_SPEED)),
                ShopMenu.arrangeItem(15000, book(Enchantment.LURE)),
                ShopMenu.arrangeItem(15000, book(Enchantment.FROST_WALKER)),
                ShopMenu.arrangeItem(25000, new ItemStack(Material.ENCHANTING_TABLE))

        ));

        priceLists.put(2, List.of(
                100000, 750000, 50000, 50000, 50000, 50000, 50000,
                50000, 50000, 50000, 50000, 50000, 50000, 50000,
                50000, 50000, 50000, 30000, 30000, 30000, 30000,
                30000, 25000, 100000, 25000, 25000, 15000, 15000,
                15000, 15000, 25000

        ));


        materialLists.put(3, List.of(
                ShopMenu.arrangeItem(100, new ItemStack(Material.WHITE_TULIP)),
                ShopMenu.arrangeItem(100, new ItemStack(Material.PINK_TULIP)),
                ShopMenu.arrangeItem(100, new ItemStack(Material.RED_TULIP)),
                ShopMenu.arrangeItem(100, new ItemStack(Material.ORANGE_TULIP)),
                ShopMenu.arrangeItem(100, new ItemStack(Material.POPPY)),
                ShopMenu.arrangeItem(1000, new ItemStack(Material.SEA_LANTERN)),
                ShopMenu.arrangeItem(1000, new ItemStack(Material.BROWN_MUSHROOM)),
                ShopMenu.arrangeItem(1000, new ItemStack(Material.RED_MUSHROOM)),
                ShopMenu.arrangeItem(500, new ItemStack(Material.GLOW_LICHEN)),
                ShopMenu.arrangeItem(500, new ItemStack(Material.WARPED_FUNGUS)),
                ShopMenu.arrangeItem(500, new ItemStack(Material.CRIMSON_FUNGUS)),
                ShopMenu.arrangeItem(350, new ItemStack(Material.BELL)),
                ShopMenu.arrangeItem(200, new ItemStack(Material.LANTERN)),
                ShopMenu.arrangeItem(200, new ItemStack(Material.GLASS)),
                ShopMenu.arrangeItem(200, new ItemStack(Material.KELP)),
                ShopMenu.arrangeItem(200, new ItemStack(Material.SEA_PICKLE)),
                ShopMenu.arrangeItem(200, new ItemStack(Material.SEAGRASS)),
                ShopMenu.arrangeItem(100, new ItemStack(Material.COBBLESTONE)),
                ShopMenu.arrangeItem(100, new ItemStack(Material.STONE)),
                ShopMenu.arrangeItem(100, new ItemStack(Material.SMALL_DRIPLEAF)),
                ShopMenu.arrangeItem(100, new ItemStack(Material.BIG_DRIPLEAF)),
                ShopMenu.arrangeItem(100, new ItemStack(Material.MUD)),
                ShopMenu.arrangeItem(50, new ItemStack(Material.YELLOW_DYE)),
                ShopMenu.arrangeItem(50, new ItemStack(Material.BLACK_DYE)),
                ShopMenu.arrangeItem(50, new ItemStack(Material.WHITE_DYE)),
                ShopMenu.arrangeItem(50, new ItemStack(Material.BLUE_DYE)),
                ShopMenu.arrangeItem(50, new ItemStack(Material.RED_DYE)),
                ShopMenu.arrangeItem(50, new ItemStack(Material.GREEN_DYE)),
                ShopMenu.arrangeItem(50, new ItemStack(Material.CANDLE)),
                ShopMenu.arrangeItem(50, new ItemStack(Material.DIRT)),
                ShopMenu.arrangeItem(20, new ItemStack(Material.OAK_LOG)),
                ShopMenu.arrangeItem(20, new ItemStack(Material.SPRUCE_LOG)),
                ShopMenu.arrangeItem(20, new ItemStack(Material.ACACIA_LOG)),
                ShopMenu.arrangeItem(20, new ItemStack(Material.DARK_OAK_LOG)),
                ShopMenu.arrangeItem(20, new ItemStack(Material.BIRCH_LOG)),
                ShopMenu.arrangeItem(20, new ItemStack(Material.JUNGLE_LOG)),
                ShopMenu.arrangeItem(20, new ItemStack(Material.WARPED_ROOTS)),
                ShopMenu.arrangeItem(20, new ItemStack(Material.CRIMSON_ROOTS))
                ));

        priceLists.put(3, List.of(
                100, 100, 100, 100, 100, 1000,
                1000, 1000, 500, 500, 500, 350,
                200, 200, 200, 200, 200, 100,
                100, 100, 100, 100, 50, 50, 50,
                50, 50, 50, 50, 50, 20, 20, 20,
                20, 20, 20, 20, 20
        ));

        materialLists.put(4, List.of(ShopMenu.arrangeItem(15000, new ItemStack(Material.DISC_FRAGMENT_5))));
        priceLists.put(4, List.of(15000));
    }

    private static final ShopMenu shop = new ShopMenu();


    public void category(Player p, int type) {

        p.closeInventory();

        List<ItemStack> resourceMaterials = materialLists.getOrDefault(type, materialLists.get(1));
        List<Integer> resourcePrices = priceLists.getOrDefault(type, priceLists.get(1));

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE601"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        Iterator<ItemStack> materialIter = (resourceMaterials).iterator();
                        Iterator<Integer> priceIter = (resourcePrices).iterator();

                        contents.set(List.of(0,1,2), IntelligentItem.of(NBTEditor.set(CustomItem.MakeItem(
                                new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", "#ff79a1&lBACK\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙",
                        false), 1010, "CustomModelData"), event -> shop.shopMenu(p)));

                        contents.set(7, IntelligentItem.of(NBTEditor.set(CustomItem.MakeItem(
                                new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", "#ff79a1&lBULK BUY\n&f\n#ff79a1&l"
                                + (Florial.getBulkBuy().get(p.getUniqueId()) != null ? Florial.getBulkBuy().get(p.getUniqueId()) : "false") + "\n&f\n#ff79a1 Enable to buy" +
                                       " x64 of an item" +  "#ff79a1&l┕━━━━━━━━━━━━━━━━━━┙",
                                false), 1010, "CustomModelData"), event -> {
                                    ShopMenu.enableBulkBuy(p);
                                    category(p, type);}));

                        for (int i = 9; i < 44; i++) {
                            if (materialIter.hasNext() && priceIter.hasNext()) {
                                ItemStack item = materialIter.next();
                                int price = priceIter.next();
                                contents.set(i, IntelligentItem.of(item, event -> ShopMenu.purchase(p, item, price)));
                            }
                        }

                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }

    private static ItemStack book(Enchantment e) {

        ItemStack i = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta iMeta = i.getItemMeta();

        iMeta.addEnchant(e,1,false);
        EnchantmentStorageMeta ec = (EnchantmentStorageMeta)iMeta;
        ec.addStoredEnchant(e, 1, true);
        i.setItemMeta(ec);

        return i;
    }

}
