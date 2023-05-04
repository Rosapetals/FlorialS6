package net.florial.menus;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Stream;

public class EventsMenu {

    public void eventMenu(Player p) {

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE611"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

                        int dna = data.getDna();
                        int eventPoints = data.getEvent();


                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", "  #ff79a1&l︳ " +
                                                "SUBMIT\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳ • #ffa2c4Click to submit all Dandelions"
                                                + "\n#ffa2c4&l︳ •#ffa2c4 in your inventory to the event.\n#ffa2c4&l︳ • [CLICK HERE]\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙"
                                                + "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", "  #ff79a1&l︳ " +
                                                "COMPLETE MONTHLY EVENT\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳ • DANDELIONS SUBMITTED: #ffa2c4 "
                                                +  eventPoints + "\n #ffa2c4&l︳ • REQUIRED DANDELIONS:#ffa2c4 50,000\n"
                                                + " #ffa2c4&l︳ • YOUR DNA:#ffa2c4 2,000"
                                                + dna
                                                + " #ffa2c4&l︳ • REQUIRED DNA:#ffa2c4 2,000"
                                                + "#ffa2c4&l︳ •#ffa2c4 [CLICK ME]\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false)).map(i -> NBTEditor.set(i, 1010, "CustomModelData"))
                                .toList();

                        contents.set(List.of(21,22,23), IntelligentItem.of(entries.get(0), event -> {submitDandelions(p, data);}));
                        contents.set(List.of(38,39,40,41), IntelligentItem.of(entries.get(1), event -> {completeEvent(p, data);}));

                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }

    private static void completeEvent(Player p, PlayerData data) {

        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);
        p.closeInventory();

        if (data.getEvent() >= 50000 && data.getDna() >= 2000) {

            if (!(data.getEvent() >= 500000)) {

                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);
                data.setDna(data.getDna() - 2000);
                data.setEvent(500000);
                data.setFlories(data.getFlories() + 200);

            } else {
                p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You have already completed this event."));


            }

        } else {

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You don't have enough to complete this event. You need 50,000 Dandelions, but have " + data.getEvent() + " and 2,000 DNA but have " + data.getDna() + ". To get Dandelions, craft Dandelion seeds with bonemeal. To get dandelion seeds, break dandelion flowers. To get dna, complete quests via /grow or convert cash to dna in /species."));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);


        }

    }

    private static void submitDandelions(Player p, PlayerData data) {

        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);
        p.closeInventory();

        int count = 0;

        for (ItemStack item : p.getInventory().all(Material.DANDELION).values()) {
            count += item.getAmount();
            p.getInventory().remove(item);
        }

        data.setEvent(data.getEvent() + count);

        if (count > 0) {
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Succesfully submitted " + count + " dandelions!"));

        } else {
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You need at least 1 Dandelion to submit it. To craft more dandelions, craft together Dandelion Seeds and bonemeal. Get dandelion seeds by breaking dandelion flowers."));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);

        }
    }
}
