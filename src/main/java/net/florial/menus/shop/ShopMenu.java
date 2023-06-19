package net.florial.menus.shop;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.utils.Cooldown;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.GetCustomSkull;
import net.florial.utils.general.VaultHandler;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class ShopMenu {

    private static final ShopBuilder shopBuilder = new ShopBuilder();
    private static final GetCustomSkull GetCustomSkull = new GetCustomSkull();

    public void shopMenu(Player p) {

        p.closeInventory();

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE603"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);

                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(new ItemStack(Material.MAP), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        "#5a372c&lRESOURCES\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                "#5a372c&lBOOKS\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                "#5a372c&lDECORATIONS\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                "#5a372c&lMISCELLANEOUS\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzdmMWZhNjFjNDQ5ZDFhNDFhM2IyZDAyMDUyMDQ1NWRhMWU1MDU2MDRjMTJhOGNlZTdjNDY4NmNhMWNhOWI5NSJ9fX0"), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                "#5a372c&lPRIDE\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                "#5a372c&lWEALTH ITEMS\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false)).map(i -> NBTEditor.set(i, 1010, "CustomModelData"))
                                .toList();


                        //10,19,20,11 (resource)
                        //29,28,37,38 (decorations)
                        //15,16,25,24 (books)
                        //22 (special)
                        //1,7 (info)
                        //31,32,39,40 misc
                        //34,35,43,44 (other)

                        contents.set(List.of(10,19,20,11), IntelligentItem.of(entries.get(0), event -> loadMenu(p, 1)));

                        contents.set(List.of(15,16,25,24), IntelligentItem.of(entries.get(1), event -> loadMenu(p, 2)));

                        contents.set(List.of(29,28,37,38), IntelligentItem.of(entries.get(2), event -> loadMenu(p, 3)));

                        contents.set(List.of(31,32,39,40), IntelligentItem.of(entries.get(3), event -> loadMenu(p, 4)));

                        contents.set(List.of(22), IntelligentItem.of(entries.get(4), event -> loadMenu(p, 5)));

                        contents.set(List.of(34,35,43,44), IntelligentItem.of(entries.get(5), event -> loadMenu(p, 6)));






                    }
                })
                .build(Florial.getInstance())
                .open(p);
    }

    private static void loadMenu(Player p, int type){

        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);
        p.closeInventory();

        switch (type) {
            case 1 -> shopBuilder.category(p, 1);
            case 2 -> shopBuilder.category(p, 2);
            case 3 -> shopBuilder.category(p, 3);
            case 4 -> shopBuilder.category(p, 4);
            case 5 -> shopBuilder.category(p, 5);
            case 6 -> shopBuilder.category(p, 6);

        }

    }

    public static ItemStack arrangeItem(int price, ItemStack i) {

        return CustomItem.MakeItem(i, "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", "  #5a372c&l︳ "
                + i.getType() + "\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙\n #6e4837&l︳ • PRICE: #6e4837 "
                + price + "\n #5a372c&l︳  [CLICK HERE]:\n#5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false);

    }

    public static void purchase(Player p, ItemStack item, int price) {

        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);

        if ((!(Cooldown.isOnCooldown("menu", p)))) {

            UUID u = p.getUniqueId();

            int amount = Florial.getBulkBuy().get(u) != null && Florial.getBulkBuy().get(u) ? 64 : 1;
            price = p.hasPermission("pearlite") && ShopBuilder.materialLists.get(3).contains(item) ? 0 : price;

            if (VaultHandler.getBalance(p) >= price*amount) {

                VaultHandler.removeMoney(p, price * amount);

                for (int i = 0; i < amount; i++) {p.getInventory().addItem(item);}

            } else {
                p.closeInventory();
                p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You need at least $" + price*amount + " to buy this. You are " + ((price*amount) - VaultHandler.getBalance(p)) + " short"));
            }

            Cooldown.addCooldown("menu", p, 2);

        }
    }

    public static void enableBulkBuy(Player p) {

        UUID u = p.getUniqueId();

        boolean outcome = Florial.getBulkBuy().get(u) == null || !Florial.getBulkBuy().get(u);


        Florial.getBulkBuy().put(u, outcome);

        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, 1);


    }
}
