package net.florial.menus.shop;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.VaultHandler;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class ShopMenu {





    private static final ResourceShopMenu resourceShop = new ResourceShopMenu();
    public void shopMenu(Player p) {

        p.closeInventory();

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE603"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);

                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        "#ff79a1&lRESOURCES\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                "#ff79a1&lBOOKS\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                "#ff79a1&lDECORATIONS\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false)).map(i -> NBTEditor.set(i, 1010, "CustomModelData"))
                                .toList();


                        //10,19,20,11 (resource)
                        //29,28,37,38 (decorations)
                        //15,16,25,24 (books)
                        //22 (special)
                        //1,7 (info)
                        //31,32,39,40 (other)
                        //34,35,43,44 (other)

                        contents.set(List.of(10,19,20,11), IntelligentItem.of(entries.get(0), event -> loadMenu(p, 1)));

                        contents.set(List.of(15,16,25,24), IntelligentItem.of(entries.get(1), event -> loadMenu(p, 2)));

                        contents.set(List.of(29,28,37,38), IntelligentItem.of(entries.get(2), event -> loadMenu(p, 3)));



                    }
                })
                .build(Florial.getInstance())
                .open(p);
    }

    private static void loadMenu(Player p, int type){

        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);
        p.closeInventory();

        switch (type) {
            case 1 -> resourceShop.resourceCategory(p);
            //case 2 -> ShopMenu.shopMenu(p);
        }

    }

    public static ItemStack arrangeItem(int price, Material mat) {

        return CustomItem.MakeItem(new ItemStack(mat), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", "  #ff79a1&l︳ "
                + mat + "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳ • PRICE: #ffa2c4 "
                + price + "\n #ff79a1&l︳  [CLICK HERE]:\n#ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false);

    }

    public static void purchase(Player p, Material mat, int price) {

        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);
        p.closeInventory();

        if (VaultHandler.getBalance(p) >= price) {

            UUID u = p.getUniqueId();
            int amount = Florial.getBulkBuy().get(u) != null && Florial.getBulkBuy().get(u) ? 64 : 1;

            VaultHandler.removeMoney(p, price);
            p.getInventory().addItem(new ItemStack(mat, amount));

        } else {

            p.sendMessage("You need at least $" + price + " to buy this. You are " + (price - VaultHandler.getBalance(p)) + " short");

        }
    }

    public static void enableBulkBuy(Player p) {

        UUID u = p.getUniqueId();

        boolean outcome = Florial.getBulkBuy().get(u) == null || !Florial.getBulkBuy().get(u);


        Florial.getBulkBuy().put(u, outcome);

        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, 1);


    }
}
