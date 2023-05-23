package net.florial.menus.species;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.features.upgrades.Upgrade;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.GetCustomSkull;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class InstinctsMenu {

    private static final net.florial.menus.species.SpeciesMenu SpeciesMenu = new SpeciesMenu();

    private static final GetCustomSkull heads = new GetCustomSkull()
;
    public void instinctMenu(Player p) {

        if (!(Florial.getPlayerData().get(p.getUniqueId()).getAge().getId() > 3)) {
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);
            p.sendMessage("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You need to be at least at an Adult Lifestage to access Instincts. Age up through /grow");
            return;
        }

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE239"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        "#ff79a1&lBACK\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        "#ff79a1&lGAIN RANDOM INSTINCT\n#ff79a1&l︳#ff79a1 500 DNA\n#ff79a1&l︳[CLICK HERE]\n#ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFhODI5MDA4ODU2MTI5OTY1ZWE0ODIyOGZlZWQzOWJkYWEzY2E0ZmNmNDdjZDQ4MjBhYzljNjIxNzY3NWVhNCJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        format(List.of(
                                                "SNEAKY", "Allows you to become invisible", "while sneaking with an empty hand.",
                                                "", "", "", "", "", ""
                                        )), false),
                                CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFhODI5MDA4ODU2MTI5OTY1ZWE0ODIyOGZlZWQzOWJkYWEzY2E0ZmNmNDdjZDQ4MjBhYzljNjIxNzY3NWVhNCJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        format(List.of(
                                                "FIGHT OR FLIGHT", "When near death, get Strength II", "or Resistance II and Speed II.",
                                                "", "", "", "", "", ""
                                        )), false),
                                CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFhODI5MDA4ODU2MTI5OTY1ZWE0ODIyOGZlZWQzOWJkYWEzY2E0ZmNmNDdjZDQ4MjBhYzljNjIxNzY3NWVhNCJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        format(List.of(
                                                "DURABLE FEET", "You do not take fall damage.", "",
                                                "", "", "", "", "", ""
                                        )), false),
                                CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFhODI5MDA4ODU2MTI5OTY1ZWE0ODIyOGZlZWQzOWJkYWEzY2E0ZmNmNDdjZDQ4MjBhYzljNjIxNzY3NWVhNCJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        format(List.of(
                                                "STRIKER", "When striking an entity, you gain", "Strength II for a few seconds.",
                                                "", "", "", "", "", ""
                                        )), false),
                                CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFhODI5MDA4ODU2MTI5OTY1ZWE0ODIyOGZlZWQzOWJkYWEzY2E0ZmNmNDdjZDQ4MjBhYzljNjIxNzY3NWVhNCJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        format(List.of(
                                                "METABOLIZER", "All food gives instant", "nourishment.",
                                                "", "", "", "", "", ""
                                        )), false),
                                CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFhODI5MDA4ODU2MTI5OTY1ZWE0ODIyOGZlZWQzOWJkYWEzY2E0ZmNmNDdjZDQ4MjBhYzljNjIxNzY3NWVhNCJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        format(List.of(
                                                "RESISTANT", "Immune to fire and snow.", "",
                                                "", "", "", "", "", ""
                                        )), false),
                                CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFhODI5MDA4ODU2MTI5OTY1ZWE0ODIyOGZlZWQzOWJkYWEzY2E0ZmNmNDdjZDQ4MjBhYzljNjIxNzY3NWVhNCJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        format(List.of(
                                                "FLESH-EATER", "Minimal damage from witches.", "Killing them gives you food. Evil.",
                                                "", "", "", "", "", ""
                                        )), false),
                                CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFhODI5MDA4ODU2MTI5OTY1ZWE0ODIyOGZlZWQzOWJkYWEzY2E0ZmNmNDdjZDQ4MjBhYzljNjIxNzY3NWVhNCJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        format(List.of(
                                                "SOPHISTICATION", "3x Experience Gain", "",
                                                "", "", "", "", "", ""
                                        )), false),
                                CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWVjODhjNzZlM2Q5YTFlYmQ3ZjRhMmU1NWVjYmNjNDJhOGQyM2Y2OTY3ODRhYTQxMGYwOTUxMmEzYjUzYSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        format(List.of(
                                                "STRONGNOSE", "Higher chance for scent to work.", "",
                                                "", "", "", "", "", ""
                                        )), false)).map(i -> NBTEditor.set(i, 1010, "CustomModelData")).toList();


                        contents.set(List.of(18, 19), IntelligentItem.of(entries.get(0), event -> loadMenu(p)));

                        contents.set(List.of(37, 38, 39, 40, 41, 42, 43), IntelligentItem.of(entries.get(1), event -> chooseInstinct(p)));

                        //11,12,13,14,15,20,21,22,23,24,29,30,31,32,33

                        contents.set(List.of(11), IntelligentItem.of(entries.get(2), event -> p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1)));
                        contents.set(List.of(12), IntelligentItem.of(entries.get(3), event -> p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1)));
                        contents.set(List.of(13), IntelligentItem.of(entries.get(4), event -> p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1)));
                        contents.set(List.of(11), IntelligentItem.of(entries.get(5), event -> p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1)));
                        contents.set(List.of(14), IntelligentItem.of(entries.get(6), event -> p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1)));
                        contents.set(List.of(15), IntelligentItem.of(entries.get(7), event -> p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1)));
                        contents.set(List.of(20), IntelligentItem.of(entries.get(8), event -> p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1)));
                        contents.set(List.of(21), IntelligentItem.of(entries.get(9), event -> p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1)));
                        contents.set(List.of(22), IntelligentItem.of(entries.get(10), event -> p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1)));



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

    private static void chooseInstinct(Player p) {

        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);


        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        if (data.getDna() >= 500) {

            p.closeInventory();

            int startId = 4;
            int amount = 0;

            if (data.getUpgrades() == null) data.setUpgrades(new HashMap<>(Map.of(Upgrade.DOUBLEHEALTH, false)));

            for (int i = 0; i < 9; i++) {
                if (data.getUpgrades().get(Upgrade.fromID(startId)) != null) amount++;
                startId++;
            }

            if (amount > 6) {
                p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You already have all of the upgrades!"));
                return;
            }

            int id = Upgrade.randomInstinct(4, 11);

            if (data.getUpgrades().get(Upgrade.fromID(id)) != null) {
                chooseInstinct(p);
                return;
            }

            data.getUpgrades().put(Upgrade.fromID(id), true);

            data.setDna(data.getDna() - 500);

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f You successfully got the " + Upgrade.fromID(id) + " instinct! Hover over it in /instincts to see the advantages!"));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);

        } else {

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You need 500 DNA to roll for a random instinct."));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);


        }

    }

    private static String format(List<String> iterations) {
        return "  #ff79a1&l︳ " + iterations.get(0) +
                "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳ •  #ffa2c4 "
                + iterations.get(1) + "\n#ffa2c4 "
                + iterations.get(2) + "\n #ffa2c4&l︳ •  #ffa2c4 "
                + iterations.get(3) + "\n#ffa2c4 "
                + iterations.get(4) + "\n #ffa2c4&l︳ •  #ffa2c4 "
                + iterations.get(5) + "\n#ffa2c4 "
                + iterations.get(4) + "\n #ffa2c4&l︳ •  #ffa2c4 "
                + iterations.get(5) + "\n#ffa2c4 "
                + iterations.get(6) + "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n";
    }
}
