package net.florial.menus;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.models.OptionType;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class OptionsMenu {



    public void optionsMenuOpen(Player p) {

        RyseInventory.builder()
                .title(CC.translate("#ff79a1&lSERVER OPTIONS"))
                .rows(1)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        UUID u = p.getUniqueId();

                        PlayerData data = Florial.getPlayerData().get(u);

                        HashMap<OptionType, Boolean> options = data.getOptions();


                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(new ItemStack(Material.PINK_CONCRETE), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", "  #5a372c&l︳ " +
                                                "NIGHT VISION\n #6e4837&l︳ • " + (options.get(OptionType.NIGHT_VISION) ? "ON" : "OFF") + "\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.PINK_CONCRETE), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", " #5a372c&l︳ " +
                                                "ALL EFFECTS\n #6e4837&l︳ • " + (options.get(OptionType.ALL_EFFECTS) ? "ON" : "OFF") + "\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false)).map(i -> NBTEditor.set(i, 1010, "CustomModelData"))
                                .toList();


                            contents.set(3, IntelligentItem.of(entries.get(0), event -> optionSet(p, OptionType.NIGHT_VISION, data)));
                            contents.set(5, IntelligentItem.of(entries.get(1), event -> optionSet(p, OptionType.ALL_EFFECTS, data)));


                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }


    private static void optionSet(Player p, OptionType optionType, PlayerData data) {

        p.closeInventory();
        UUID u = p.getUniqueId();


        p.clearActivePotionEffects();

        boolean setTo = !data.getOptions().get(optionType);

        data.getOptions().put(optionType, setTo);

        Florial.getPlayerData().get(u).refresh();


        p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 2);
    }
}
