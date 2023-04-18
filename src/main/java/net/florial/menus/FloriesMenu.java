package net.florial.menus;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.menus.shop.ShopMenu;
import net.florial.species.Species;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.GetCustomSkull;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FloriesMenu {

    private static final Map<Integer, List<ItemStack>> florieItems = new HashMap<>();
    private static final Map<Integer, List<Integer>> floriePrices = new HashMap<>();


    static {

        florieItems.put(1, List.of(

                ShopMenu.arrangeItem(5000, new ItemStack(Material.PUMPKIN_SEEDS))
        ));
        floriePrices.put(1, List.of(
                5000
        ));
    }



    final net.florial.utils.general.GetCustomSkull GetCustomSkull = new GetCustomSkull();

    public void floriesMenu(Player p) {

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE608"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        List<ItemStack> species = Stream.of(CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkyNGJlNWY3NGI2NDMxNjYwZmQ1YzRjYzAzMzRkOTFlNzdlNzdmZGQ4OGQyNGVhODVlYjBmMzgzODRjN2YxYSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lFOX", "#ffa2c4&lBITE:#ffa2c4 Melee with an empty hand.", "You will bite heavy.",
                                        "#ffa2c4&lHEAT ACCLIMATION:#ffa2c4 Your Lavakit", "heritage shows. Get Fire Resistance when hurt by lava/fire.",
                                        "#ffa2c4&lSPEED II:#ffa2c4 You're faster.", "Make the most of it.",
                                        "#ffa2c4&lNIGHT VISION:#ffa2c4 You can see in dark.", "Make the most of it.",
                                        "#ffa2c4&lBURROWER:#ffa2c4 Upgrade your burrow skill to", "break blocks faster and get more materials",
                                        "#ffa2c4&lWEAK:#ffa2c4 Live with 7 hearts", "Make the most of it.",
                                        "#ffa2c4&lCARNIVORE:#ffa2c4 Vegetables aren't all that", "healthy. Except Sweet Berries!",
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
