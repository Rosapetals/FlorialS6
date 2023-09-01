package net.florial.menus;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.features.playtimerewards.Tier;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.GetCustomSkull;
import net.florial.utils.general.VaultHandler;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class PlaytimeMenu {

    private static final GetCustomSkull GetCustomSkull = new GetCustomSkull();

    public void activate(Player p) {


        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE632"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        int hours = player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 72000;

                        PlayerData data = Florial.getPlayerData().get(player.getUniqueId());

                        HashMap<Tier, Boolean> tiers = data.getPlaytimeTiers() != null ? data.getPlaytimeTiers() : new HashMap<>();


                        List<Integer> spots = List.of(13, 20, 24, 36, 44, 40);

                        int index = 0;
                        for (int value : spots) {
                            index++;
                            Tier tier = Tier.fromID(index);
                            assert tier != null;
                            contents.set(value, IntelligentItem.of(CustomItem.MakeItem(GetCustomSkull.getCustomSkull(
                                            tiers.containsKey(tier) ?
                                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhmY2EzNDVlNGZkOWM0MjJiNzNjNGMxYzUwNTZmMzc5ZGU5MjUxMGZjOTRiNGNjOTA3ZmIyMGNlNzUwZGM5MCJ9fX0"
                                                    :
                                                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIzZDdlNjU1ZGViZGQ1OTFkMDk5ZDc2ZmYwMDBkNzU1NWJlNGFlMTFiMWUyNmI5YWRmMjQ0YWUwMjJiMjljOCJ9fX0"), "#FF9920&l ┍━━━━━━━━━━━━━━━━━━┑",
                                    format(tier.getRequiredHours(), hours, index), false), event -> unlockPlayTimeReward(player, tiers, tier, hours, data)));

                        }


                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }

    private static void unlockPlayTimeReward(Player p, HashMap<Tier, Boolean> tiers, Tier tier, int hours, PlayerData data) {

        p.closeInventory();

        if (tiers.containsKey(tier)) {

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You have already unlocked this tier."));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, (float) 1.3);

        } else {

            int requiredHours = tier.getRequiredHours();

            if (hours >= requiredHours) {

                int seasonalKeys = tier.getSeasonalKeys();
                int dna = tier.getDna();
                int money = tier.getMoney();
                int flories = tier.getFlories();

                p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f You have unlocked this tier and earned " + flories + " flories, $" + money + ", " + dna + " DNA, and " + seasonalKeys + " Seasonal Keys."));
                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, (float) 1.3);

                ItemStack key3 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#fb4e0e&lA#fb5418&lu#fc5a21&lt#fc602b&lu#fc6634&lm#fd6c3e&ln #fd7248&lC#fd7851&lr#fd7e5b&la#fe8465&lt#fe8a6e&le #fe9078&lK#ff9681&le#ff9c8b&ly", "", false), 3, "CustomModelData");
                key3 = NBTEditor.set(key3, 3, "Crate");

                for (int i = 0; i < seasonalKeys; i++) {p.getInventory().addItem(key3);}
                VaultHandler.addMoney(p, money);
                data.setDna(data.getDna() + dna);
                data.setFlories(data.getFlories() + flories);

                data.getPlaytimeTiers().put(tier, true);

            } else {
                p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You need " + requiredHours + " hours to unlock this. You only have " +  hours + " hours."));
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, (float) 1.3);
            }
        }
    }

    private static String format(int requiredHours, int yourHours, int tier) {

        return  " #FF9920&l︳• #FFB65FPLAYTIME TIER " + tier + "\n"
                + " #FF9920&l︳• Unlocks at: #FFB65F" + requiredHours + " hours"
                + "\n #FF9920&l︳• Your Hours: #FFB65F" + yourHours
                + "\n #FF9920&l┕━━━━━━━━━━━━━━━━━━┙\n"
                + "\n #FF9920&l︳•#FFB65F [CLICK TO UNLOCK]\n #FF9920&l┕━━━━━━━━━━━━━━━━━━┙";

    }
}
