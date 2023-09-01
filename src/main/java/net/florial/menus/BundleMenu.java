package net.florial.menus;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.models.SeasonalCrateItem;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.GetCustomSkull;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.UUID;

public class BundleMenu {

    private static final FloriesMenu floriesMenu = new FloriesMenu();

    private static final GetCustomSkull getCustomSkull = new GetCustomSkull();

        public void bundleMenuOpen(Player p) {

        RyseInventory.builder()
                .title(CC.translate("#fb7304&lA#fb700b&lU#fb6c11&lT#fc6918&lU#fc651f&lM#fc6225&lN #fc5e2c&lB#fc5b32&lU#fc5739&lN#fd5440&lD#fd5046&lL#fd4d4d&lE"))
                .rows(1)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {


                        contents.set(4, IntelligentItem.of(CustomItem.MakeItem(getCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2UzNGM2MjlhMTM0Yjg1ZGM2OTVkNGM1MDhmZjI1NWU1ZTllMmQyZGFjNDM4NDhiNTVlYTIyNDQyM2ViOGEwIn19fQ"),
                                "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", "#fb7304&lA#fb700b&lU#fb6c11&lT#fc6918&lU#fc651f&lM#fc6225&lN #fc5e2c&lB#fc5b32&lU#fc5739&lN#fd5440&lD#fd5046&lL#fd4d4d&lE\n" +
                                " #6e4837&l︳ • " + "\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙\n #6e4837&l︳ •#fd5440&l 300 Flories"
                                + "\n #6e4837&l︳ •#fd5440&l x64 all Fall items(1000+ DNA), special heads,\n #6e4837&l︳ •#fd5440&l Mermaid's Tail, Sunscreen, 1x Bundle Basket" +
                                        "\n #6e4837&l︳ •#fd5440&l HAVE AN EMPTY INVENTORY!\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false), event -> buyBundle(p)));


                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }


    private static void buyBundle(Player p) {

        p.closeInventory();
        UUID u = p.getUniqueId();

        PlayerData data = Florial.getPlayerData().get(u);

        int flories = data.getFlories();

        if (flories >= 300) {

            data.setFlories(flories - 300);

            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 2);


            Inventory i = p.getInventory();

            for (SeasonalCrateItem value : SeasonalCrateItem.values()) {
                p.getInventory().addItem(Objects.requireNonNull(SeasonalCrateItem.fromID(value.getId(), 64)));
            }

            i.addItem(floriesMenu.florieItems.get(21));
            i.addItem(floriesMenu.florieItems.get(22));
            i.addItem(new ItemStack(Material.BUNDLE));
            i.addItem(CustomItem.MakeItem(getCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmM3ZmNjMmEwNDU0N2Y0OGI1MGJjNzJmYTI3NTMyNDc0Zjc5NTFmOTA5YTkyN2FjZTAxMDhjZTc3MjQwMjM1MCJ9fX0"), "&6Pumpkin Soup [UNOBTAINABLE]", "", false));
            i.addItem(CustomItem.MakeItem(getCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzc2ZWEwNzk2ZWFhN2UyOTNmMGUwNGZiMzAyYTRmM2Q1M2MxOWRiNDU4MzdmZWZkZjcwYjIzODJkZTY4ZGIxZCJ9fX0"), "&ePumpkin Marmalade [UNOBTAINABLE]", "", false));
            i.addItem(CustomItem.MakeItem(getCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjJiZTdkYWY2NTM3YTNhZTQ1ZDNmNWE4NWJhYmNjYjcwZTJiM2JhN2JmMjk4M2E3MGU4NTE1YzE0ZWFkOGU4In19fQ"), "&6Pumpkin [UNOBTAINABLE]", "", false));




        } else {

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You need 300 flories to buy the " +
                    "#fb7304&lA#fb700b&lU#fb6c11&lT#fc6918&lU#fc651f&lM#fc6225&lN #fc5e2c&lB#fc5b32&lU#fc5739&lN#fd5440&lD#fd5046&lL#fd4d4d&lE.&c You only have " + flories +  "flories." ));

            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 2);

        }


    }
}
