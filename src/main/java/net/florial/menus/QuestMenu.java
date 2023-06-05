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
import net.florial.utils.general.MoneyFormatter;
import net.florial.utils.general.VaultHandler;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class QuestMenu {


    public void questsMenu(Player p) {

        UUID u = p.getUniqueId();

        PlayerData data = Florial.getPlayerData().get(u);

        boolean isReroll = Florial.getQuest().get(u) != null;

        String menuDisplay = isReroll ? "\uE611" : "\uE612";

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七" + menuDisplay))
                .rows(1)
                .provider(new InventoryProvider() {

                    final Age age = data.getAge();
                    final String questDisplay = isReroll ? "RE-ROLL QUEST:" : "GET QUEST:";
                    final String rollPrice = Florial.getQuest().get(u) != null ? "Price: " + MoneyFormatter.put((long)Quest.rollFormula(VaultHandler.getBalance(p))) : "";

                    @Override
                    public void init(Player player, InventoryContents contents) {


                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(new ItemStack(Material.MAP), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", "  #5a372c&l︳ " +
                                        questDisplay + "\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙\n #5a372c&l︳ • COMPLETED QUESTS: #6e4837 "
                                        +  data.getGrowth() + "\n #5a372c&l︳  " + questDisplay + "\n #5a372c&l︳ •#6e4837 [CLICK ME]\n #422820&l┕━━━━━━━━━━━━━━━━━━┙\n #5a372c&l︳#6e4837&l INFORMATION\n #5a372c&l︳ • INFO:" +
                                        "\n #5a372c&l︳#6e4837 • 1 quest gives 1 DNA" +
                                        "\n #5a372c&l︳#6e4837 • Complete " + age.getRequiredQuests() + " and get " + age.getRequiredDNA() + " DNA to Age Up"
                                        + "\n #5a372c&l︳#6e4837 • " + rollPrice
                                        + "\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false)).map(i -> NBTEditor.set(i, 1010, "CustomModelData"))
                                .toList();

                        contents.set(List.of(2,3,4,5,6), IntelligentItem.of(entries.get(0), event -> {
                            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 2, 1);
                            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 2, 1);
                            Quest.give(p, false, null);
                        }));

                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }
}
