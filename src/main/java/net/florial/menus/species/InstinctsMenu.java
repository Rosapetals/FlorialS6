package net.florial.menus.species;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.features.age.Age;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Stream;

public class InstinctsMenu {

    private static final net.florial.menus.species.SpeciesMenu SpeciesMenu = new SpeciesMenu();

    public void instinctMenu(Player p) {

        if (!(Florial.getPlayerData().get(p.getUniqueId()).getAge().getId() > 3)) {
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);
            p.sendMessage("You need to be at least at an Adult Lifestage to access Instincts. Age up through /grow");
            return;
        }

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE239"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                "#ff79a1&lBACK\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false)).map(i -> NBTEditor.set(i, 1010, "CustomModelData")).toList();


                        contents.set(List.of(18, 19), IntelligentItem.of(entries.get(0), event -> loadMenu(p)));

                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }

    private static void loadMenu(Player p) {

        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);

        p.closeInventory();

        SpeciesMenu.speciesMenu(p);
    }
}
