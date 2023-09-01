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
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE613"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

                        int eventPoints = data.getEvent();


                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(new ItemStack(Material.MAP), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", " #5a372c&l︳" +
                                                " SUBMIT\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙\n #6e4837&l︳ • #6e4837 to submit leaves"
                                                + "\n #6e4837&l︳ •#6e4837 in your inventory to the event.\n #6e4837&l︳ • [CLICK HERE]\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", " #5a372c&l︳ " +
                                                "Monthly Event: Leaf Round-Up\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙\n #6e4837&l︳ • LEAVES SUBMITTED: #6e4837 "
                                                +  eventPoints + "\n #6e4837&l︳ • REQUIRED:#6e4837 8,000\n"
                                                + " #6e4837&l︳ • REWARD:#6e4837 200 Flories at 8k Leaves\n"
                                                + " #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false)).map(i -> NBTEditor.set(i, 1010, "CustomModelData"))
                                .toList();

                        contents.set(List.of(18,19,20), IntelligentItem.of(entries.get(0), event -> {submit(p, data);}));
                        contents.set(List.of(3,4,13,14,5), IntelligentItem.of(entries.get(1), event -> {completeEvent(p, data);}));

                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }

    private static void completeEvent(Player p, PlayerData data) {

        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);
        p.closeInventory();

        if (data.getEvent() >= 8000 && (!(data.getEvent() >= 10000))) {
            p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);
            data.setFlories(data.getFlories() + 200);
            data.setEvent(10000);
        }

    }

    private static void submit(Player p, PlayerData data) {

        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);
        p.closeInventory();

        if (!(data.getEvent() >= 8000)) {

            int count = 0;

            for (ItemStack item : p.getInventory().getContents()) {
                if (item != null && item.getType().toString().contains("LEAVES")) {
                    count += item.getAmount();
                    p.getInventory().remove(item);
                }
            }

            data.setEvent(data.getEvent() + count);

            if (count > 0) {
                p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully submitted " + count + " leaves!"));
                completeEvent(p, data);

            } else {
                p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You need at least 1 Leaf Block to submit it."));
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);

            }

        } else {
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c Already completed the event!"));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
        }

    }
}
