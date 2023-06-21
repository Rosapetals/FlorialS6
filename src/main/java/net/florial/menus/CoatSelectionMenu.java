package net.florial.menus;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.species.disguises.Morph;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.GetCustomSkull;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Stream;

public class CoatSelectionMenu {

    private static GetCustomSkull skull = new GetCustomSkull();
    private static Morph morph = new Morph();


    public void coatSelectionMenu(Player p) {

        RyseInventory.builder()
                .title(CC.translate("           #5a372c&lSELECT A COAT"))
                .rows(2)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmU1NTIyYzNmMzBlMzY3NDYwNDFmODU0ZWExZmU2NjNiYzkyNjc1MTk0N2FiMWFkNTY1ODFhOGJjNjU3MjI5OSJ9fX0"), "#5a372c&lCALICO", "", false),
                                        CustomItem.MakeItem(skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2EzNzhhODllZTFkNDM1ZmUyZDk0MmQ2ODUzNTExYTgwYzBhOWZhODkyNGM1NTAzZGIzMmU2MjFkNTgzM2FlYiJ9fX0"), "#5a372c&lBLACK", "", false),
                                        CustomItem.MakeItem(skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjlmMTY5OTJmM2E5MmMyZTYyZmQzOGQ1ZDg1MWZkNjYxY2ViM2U4YTgxMDIwMDUzNmVhNGIxMmViMDVmMzQxZSJ9fX0"), "#5a372c&lSIAMESE", "", false),
                                        CustomItem.MakeItem(skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2IzNTViYTM4OTAxZWU4NmEyM2Q2MWU5Y2UxMDE2NThmMjQxN2Q2YjliNGMzODEyYTkwNGFkZTU4MWNiNDQxNSJ9fX0"), "#5a372c&lTABBY", "", false),
                                        CustomItem.MakeItem(skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjRhOGE3YmY1ZTc1MGY4MDhlNGZiZDQwODk0ZGZlNzQ2YTA3MjQ1ZGMxNmExMTM3NDNhZTM1ZmViNWIwZjc3MSJ9fX0"), "#5a372c&lWHITE", "", false),
                                        CustomItem.MakeItem(skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmNiYzBiZjNiY2IyYzUxZWQ0NDUzYjIwZTc0MWE4MGRjNWYwNjU1NTBiMWUzNTFjMGVhZmFkYzUwMjI1N2FmNSJ9fX0"), "#5a372c&lORANGE", "", false),
                                        CustomItem.MakeItem(skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjdmNTk2YWZiODY5ZjgwNmY2MTA4NWE0OTliN2U3NTE0ODkxM2QzZTYwNjAxZjE2NjI1OGRmYmNiODJhM2JiZiJ9fX0"), "#5a372c&lJELLIE", "", false),
                                        CustomItem.MakeItem(skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQ2MmI0YTQ1YzVmYTIzM2E0NjM5YWY2ZmI5MTBhZmMxNzk1ZWIwMDExZTQ2MjY2YTA4NjVkMTczZWJkZTlhYiJ9fX0"), "#5a372c&lPERSIAN", "", false),
                                        CustomItem.MakeItem(skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWY1MzkyOTNlYmVhYmE0YzM1ZjA4OTM1N2U1NDU3ZjgzOGZhNGE1ZGI0ZGE4M2FmYmZjMjk1YWQyZDZkNmVhNiJ9fX0"), "#5a372c&lJET BLACK", "", false),
                                        CustomItem.MakeItem(skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZjZDJlMGMyYjVkZjk0ODJlZjQ1ZjVlMWQzY2YwNzc3OGZlYmRkODQ1NWQzZjAyZDMyNjAyOWFkNzlmNDFjZiJ9fX0"), "#5a372c&lBRITISH SHORTHAIR", "", false),
                                        CustomItem.MakeItem(skull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzE5Nzk0NjE2NTAyN2Q2ZThjMjEzNTM0NDNlM2IxMDNlZWY0YmVlMWExYzIzODk4NGZlMGFmYjA2Njg1OWQ0NSJ9fX0"), "#5a372c&lRAGDOLL", "", false)
                                        ).map(i -> NBTEditor.set(i, 1010, "CustomModelData"))
                                .toList();

                        contents.set(0, IntelligentItem.of(entries.get(0), event -> selectCoat(7, p)));
                        contents.set(1, IntelligentItem.of(entries.get(1), event -> selectCoat(8, p)));
                        contents.set(2, IntelligentItem.of(entries.get(2), event -> selectCoat(10, p)));
                        contents.set(3, IntelligentItem.of(entries.get(3), event -> selectCoat(11, p)));
                        contents.set(4, IntelligentItem.of(entries.get(4), event -> selectCoat(12, p)));
                        contents.set(5, IntelligentItem.of(entries.get(5), event -> selectCoat(13, p)));
                        contents.set(6, IntelligentItem.of(entries.get(6), event -> selectCoat(14, p)));
                        contents.set(7, IntelligentItem.of(entries.get(7), event -> selectCoat(15, p)));
                        contents.set(8, IntelligentItem.of(entries.get(8), event -> selectCoat(16, p)));
                        contents.set(12, IntelligentItem.of(entries.get(9), event -> selectCoat(17, p)));
                        contents.set(14, IntelligentItem.of(entries.get(10), event -> selectCoat(9, p)));



                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }


    private static void selectCoat(int id, Player p) {

        morph.activate(p, id, false, true, Florial.getPlayerData().get(p.getUniqueId()).getSpecies());
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 2, 1);
        p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 2, 1);
        p.closeInventory();

    }
}
