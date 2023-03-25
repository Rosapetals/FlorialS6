package net.florial.menus;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.features.age.Age;
import net.florial.features.quests.Quest;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Stream;

public class GrowthMenu {

    private static final SpeciesMenu SpeciesMenu = new SpeciesMenu();

    public void growthMenu(Player p) {

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE600"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

                        Age age = data.getAge();
                        int requiredDNA = age.getRequiredDNA();
                        int requiredQuests = age.getRequiredQuests();

                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", "  #ff79a1&l︳ " +
                                "QUEST SYSTEM\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳ • YOUR AGE: #ffa2c4 "
                                + age + "\n #ff79a1&l︳  INCREASE BY:\n #ffa2c4&l︳ •#ffa2c4 "
                                + requiredDNA + " DNA\n #ffa2c4&l︳ • " + requiredQuests + " Quests\n#ffa2c4&l︳ • [CLICK HERE]\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳#ff79a1&l INFORMATION\n #ffa2c4&l︳ • HOVER OVER:\n #ffa2c4&l︳#ffa2c4 the other menu options "
                                + "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", "  #ff79a1&l︳ " +
                                                "GET-A-QUEST\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳ • COMPLETED QUESTS: #ffa2c4 "
                                                +  data.getGrowth() + "\n #ff79a1&l︳  GET QUEST:\n #ffa2c4&l︳ •#ffa2c4 [CLICK ME]\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳#ff79a1&l INFORMATION\n #ffa2c4&l︳ • INFO:" +
                                                "\n #ffa2c4&l︳#ffa2c4 • Progress is tracked at top of screen" +
                                                "\n #ffa2c4&l︳#ffa2c4 • 1 quest gives 1 DNA" +
                                                "\n #ffa2c4&l︳#ffa2c4 • Complete " + requiredQuests + " and get " + requiredDNA + " DNA to Age Up"
                                                + "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", "  #ff79a1&l︳ " +
                                                "GO BACK\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                                "KIT", "BE BORN", "1", "2", "3", "4")), false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", "  #ff79a1&l︳ " +
                                                "GO BACK\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false)).map(i -> NBTEditor.set(i, 1010, "CustomModelData"))
                                .toList();
                        //3,4,5,6
                        contents.set(List.of(0, 1), IntelligentItem.of(entries.get(2), event -> loadMenu(p)));
                        contents.set(List.of(3,4,5,6), IntelligentItem.of(entries.get(0), event -> {
                            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 2, 1);
                            Age.up(p, data, age, requiredQuests, requiredDNA);
                        }));
                        contents.set(List.of(12,13,14), IntelligentItem.of(entries.get(1), event -> {
                            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 2, 1);
                            Quest.give(p);
                        }));
                        contents.set(List.of(9,10,18,19,28), IntelligentItem.of(entries.get(3), event -> {p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 2, 1);}));
                        contents.set(List.of(16,17,25,26), IntelligentItem.of(entries.get(3), event -> {p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 2, 1);}));
                        contents.set(List.of(36,37,45,46), IntelligentItem.of(entries.get(3), event -> {p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 2, 1);}));
                        contents.set(List.of(43,44,52,53), IntelligentItem.of(entries.get(3), event -> {p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 2, 1);}));
                        contents.set(List.of(21,22,23,30,31,32,33,39,40,41), IntelligentItem.of(entries.get(3), event -> {p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 2, 1);}));
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

    private static String format(List<String> iterations){

        return  "  #ff79a1&l︳ "
                + iterations.get(0) + "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳ • HOW TO GET:\n #ff79a1&l︳ "
                + iterations.get(1) + "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳#ff79a1&l INFORMATION\n #ffa2c4&l︳ • INFO:" +
                "\n #ffa2c4&l︳#ffa2c4 • "
                + iterations.get(2) +
                "\n #ffa2c4&l︳#ffa2c4 • "
                + iterations.get(3) +
                "\n #ffa2c4&l︳#ffa2c4 • "
                + iterations.get(4) +
                "\n #ffa2c4&l︳#ffa2c4 • "
                + iterations.get(5) + "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙";
    }

}

