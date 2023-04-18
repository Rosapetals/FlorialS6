package net.florial.menus.species;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.species.Species;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.GetCustomSkull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Stream;

public class UserSpeciesMenu {

    // wip

    final net.florial.utils.general.GetCustomSkull GetCustomSkull = new GetCustomSkull();

    public void userSpeciesMenu(Player p) {

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE608"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        List<ItemStack> species = Stream.of(CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkyNGJlNWY3NGI2NDMxNjYwZmQ1YzRjYzAzMzRkOTFlNzdlNzdmZGQ4OGQyNGVhODVlYjBmMzgzODRjN2YxYSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lBASSBEAST", "#ffa2c4&lLIGHTNING:#ffa2c4 Melee with a sword to strike", "your foes with lightning.",
                                        "#ffa2c4&lROAR:#ffa2c4 Left-Click with an empty hand", "to roar loudly.",
                                        "#ffa2c4&lFLIGHT:#ffa2c4 Shift+Left-Click to fly", "Be sure to look up!",
                                        "#ffa2c4&lLarge::#ffa2c4 +6 hearts.", "Make the most of it.",
                                        "#ffa2c4&lALLERGIES:#ffa2c4 Allergic to bees and starchy", "foods like potatoes and carrots.",
                                        "#ffa2c4&lDAMP INTOLERANCE:#ffa2c4 Takes more damage when", "it is raining.",
                                        "#ffa2c4&lPOWERFUL: Strength II. Speed 1.", "& Resistance I.",
                                        "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UzZTAzYTk2NzE4NDAyMjNhOTZhOTIwOTE0ZDFiODczMTQwNDRjYzZkMzJhYWI3YzI3ZTFmNjQwZWNjMjFkNSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lDRACONIC", "#ffa2c4&lFLIGHT:#ffa2c4 Shift+Left-Click to fly!", "Be sure to look up!",
                                        "#ffa2c4&lFEARFUL ROAR:#ffa2c4 Hit with an empty hand to", "scratch nearby entities in a radius.",
                                        "#ffa2c4&lSTRONG:#ffa2c4 Strength II &&", "Absorption I.",
                                        "#ffa2c4&lLARGE:#ffa2c4 +4 hearts.", "Make the most of it.",
                                        "#ffa2c4&lCARNIVORE:#ffa2c4 Vegetables aren't all that", "healthy.",
                                        "", "", "", "", "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UzZTAzYTk2NzE4NDAyMjNhOTZhOTIwOTE0ZDFiODczMTQwNDRjYzZkMzJhYWI3YzI3ZTFmNjQwZWNjMjFkNSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lDRYAD", "#ffa2c4&lMOTHER NATURE:#ffa2c4 Right-Click a block to spawn a", "random sapling.",
                                        "#ffa2c4&lAURA:#ffa2c4 Shift+Right-Click a block to summon", "a healing aura nearby.",
                                        "#ffa2c4&lNURTURER:#ffa2c4 Right-Click a crop to", "grow it some.",
                                        "#ffa2c4&lVEGETARIAN:#ffa2c4 Meat is just...", "not it.",
                                        "#ffa2c4&lWEAK PLANT:#ffa2c4 You're weaker at night and", "more vulnerable to fire.",
                                        "#ffa2c4&lBEE FRIEND:#ffa2c4 Immune to bee", "stings.",
                                        "", "", "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UzZTAzYTk2NzE4NDAyMjNhOTZhOTIwOTE0ZDFiODczMTQwNDRjYzZkMzJhYWI3YzI3ZTFmNjQwZWNjMjFkNSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lCAT", "#ffa2c4&lNINE LIVES:#ffa2c4 Bounce back when close to death.", "Has a cooldown.",
                                        "#ffa2c4&lSCRATCH:#ffa2c4 Hit with an empty hand to", "scratch nearby entities in a radius.",
                                        "#ffa2c4&lPURR:#ffa2c4 Shift+Right-Click anywhere to give", "yourself & those around you Regen II.",
                                        "#ffa2c4&lNIGHT VISION:#ffa2c4 You can see in dark.", "Make the most of it.",
                                        "#ffa2c4&lQUICKFEET:#ffa2c4 Land on your feet.", "No fall damage! + Speed I.",
                                        "#ffa2c4&lSMALL:#ffa2c4 Live with 6 hearts", "Make the most of it.",
                                        "#ffa2c4&lCARNIVORE:#ffa2c4 Vegetables aren't all that", "healthy.",
                                        "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UzZTAzYTk2NzE4NDAyMjNhOTZhOTIwOTE0ZDFiODczMTQwNDRjYzZkMzJhYWI3YzI3ZTFmNjQwZWNjMjFkNSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lCAT", "#ffa2c4&lNINE LIVES:#ffa2c4 Bounce back when close to death.", "Has a cooldown.",
                                        "#ffa2c4&lSCRATCH:#ffa2c4 Hit with an empty hand to", "scratch nearby entities in a radius.",
                                        "#ffa2c4&lPURR:#ffa2c4 Shift+Right-Click anywhere to give", "yourself & those around you Regen II.",
                                        "#ffa2c4&lNIGHT VISION:#ffa2c4 You can see in dark.", "Make the most of it.",
                                        "#ffa2c4&lQUICKFEET:#ffa2c4 Land on your feet.", "No fall damage! + Speed I.",
                                        "#ffa2c4&lSMALL:#ffa2c4 Live with 6 hearts", "Make the most of it.",
                                        "#ffa2c4&lCARNIVORE:#ffa2c4 Vegetables aren't all that", "healthy.",
                                        "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UzZTAzYTk2NzE4NDAyMjNhOTZhOTIwOTE0ZDFiODczMTQwNDRjYzZkMzJhYWI3YzI3ZTFmNjQwZWNjMjFkNSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lCAT", "#ffa2c4&lNINE LIVES:#ffa2c4 Bounce back when close to death.", "Has a cooldown.",
                                        "#ffa2c4&lSCRATCH:#ffa2c4 Hit with an empty hand to", "scratch nearby entities in a radius.",
                                        "#ffa2c4&lPURR:#ffa2c4 Shift+Right-Click anywhere to give", "yourself & those around you Regen II.",
                                        "#ffa2c4&lNIGHT VISION:#ffa2c4 You can see in dark.", "Make the most of it.",
                                        "#ffa2c4&lQUICKFEET:#ffa2c4 Land on your feet.", "No fall damage! + Speed I.",
                                        "#ffa2c4&lSMALL:#ffa2c4 Live with 6 hearts", "Make the most of it.",
                                        "#ffa2c4&lCARNIVORE:#ffa2c4 Vegetables aren't all that", "healthy.",
                                        "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UzZTAzYTk2NzE4NDAyMjNhOTZhOTIwOTE0ZDFiODczMTQwNDRjYzZkMzJhYWI3YzI3ZTFmNjQwZWNjMjFkNSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lCAT", "#ffa2c4&lNINE LIVES:#ffa2c4 Bounce back when close to death.", "Has a cooldown.",
                                        "#ffa2c4&lSCRATCH:#ffa2c4 Hit with an empty hand to", "scratch nearby entities in a radius.",
                                        "#ffa2c4&lPURR:#ffa2c4 Shift+Right-Click anywhere to give", "yourself & those around you Regen II.",
                                        "#ffa2c4&lNIGHT VISION:#ffa2c4 You can see in dark.", "Make the most of it.",
                                        "#ffa2c4&lQUICKFEET:#ffa2c4 Land on your feet.", "No fall damage! + Speed I.",
                                        "#ffa2c4&lSMALL:#ffa2c4 Live with 6 hearts", "Make the most of it.",
                                        "#ffa2c4&lCARNIVORE:#ffa2c4 Vegetables aren't all that", "healthy.",
                                        "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UzZTAzYTk2NzE4NDAyMjNhOTZhOTIwOTE0ZDFiODczMTQwNDRjYzZkMzJhYWI3YzI3ZTFmNjQwZWNjMjFkNSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lCAT", "#ffa2c4&lNINE LIVES:#ffa2c4 Bounce back when close to death.", "Has a cooldown.",
                                        "#ffa2c4&lSCRATCH:#ffa2c4 Hit with an empty hand to", "scratch nearby entities in a radius.",
                                        "#ffa2c4&lPURR:#ffa2c4 Shift+Right-Click anywhere to give", "yourself & those around you Regen II.",
                                        "#ffa2c4&lNIGHT VISION:#ffa2c4 You can see in dark.", "Make the most of it.",
                                        "#ffa2c4&lQUICKFEET:#ffa2c4 Land on your feet.", "No fall damage! + Speed I.",
                                        "#ffa2c4&lSMALL:#ffa2c4 Live with 6 hearts", "Make the most of it.",
                                        "#ffa2c4&lCARNIVORE:#ffa2c4 Vegetables aren't all that", "healthy.",
                                        "", "", "", "")), false)).toList();


                        contents.set(List.of(23), IntelligentItem.of(species.get(1), event -> {
                            Species.become(p, "CAT");}));
                        contents.set(List.of(21), IntelligentItem.of(species.get(0), event -> Species.become(p, "FOX")));


                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }


    private static String format(List<String> iterations){
        return "  #ff79a1&l︳ " + iterations.get(0) +
                "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳ •  #ffa2c4 "
                + iterations.get(1) + "\n#ffa2c4 "
                + iterations.get(2) + "\n #ffa2c4&l︳ •  #ffa2c4 "
                + iterations.get(3) + "\n#ffa2c4 "
                + iterations.get(4) + "\n #ffa2c4&l︳ •  #ffa2c4 "
                + iterations.get(5) + "\n#ffa2c4 "
                + iterations.get(6) + "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n"
                + "#ffa2c4&l︳ • #ffa2c4 "
                + iterations.get(7) + "\n#ffa2c4 "
                + iterations.get(8) + "\n #ffa2c4&l︳ • #ffa2c4 "
                + iterations.get(9) + "\n#ffa2c4 "
                + iterations.get(10) + "\n #ffa2c4&l︳ • #ffa2c4 "
                + iterations.get(11) + "\n#ffa2c4 "
                + iterations.get(12) + "\n #ffa2c4&l︳ • #ffa2c4 "
                + iterations.get(13) + "\n#ffa2c4 "
                + iterations.get(14) + "\n #ffa2c4&l︳ • #ffa2c4 "
                + iterations.get(15) + "\n#ffa2c4 "
                + iterations.get(16) + "\n #ffa2c4&l︳ • #ffa2c4 "
                + iterations.get(17) + "\n#ffa2c4 "
                + iterations.get(18) + "\n #ffa2c4&l︳ • [CLICK HERE]" +
                "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙";}
}
