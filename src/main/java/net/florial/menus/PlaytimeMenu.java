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
import java.util.stream.Stream;

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



                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(GetCustomSkull.getCustomSkull(
                                tiers.containsKey(Tier.TIER1) ?
                                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhmY2EzNDVlNGZkOWM0MjJiNzNjNGMxYzUwNTZmMzc5ZGU5MjUxMGZjOTRiNGNjOTA3ZmIyMGNlNzUwZGM5MCJ9fX0"
                                        :
                                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIzZDdlNjU1ZGViZGQ1OTFkMDk5ZDc2ZmYwMDBkNzU1NWJlNGFlMTFiMWUyNmI5YWRmMjQ0YWUwMjJiMjljOCJ9fX0"), "#FF9920&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        format(Tier.TIER1.getRequiredHours(), hours, 1), false),
                                        CustomItem.MakeItem(GetCustomSkull.getCustomSkull(
                                                        tiers.containsKey(Tier.TIER2) ?
                                                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhmY2EzNDVlNGZkOWM0MjJiNzNjNGMxYzUwNTZmMzc5ZGU5MjUxMGZjOTRiNGNjOTA3ZmIyMGNlNzUwZGM5MCJ9fX0"
                                                                :
                                                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIzZDdlNjU1ZGViZGQ1OTFkMDk5ZDc2ZmYwMDBkNzU1NWJlNGFlMTFiMWUyNmI5YWRmMjQ0YWUwMjJiMjljOCJ9fX0"), "#FF9920&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                format(Tier.TIER2.getRequiredHours(), hours, 2), false),
                                        CustomItem.MakeItem(GetCustomSkull.getCustomSkull(
                                                        tiers.containsKey(Tier.TIER3) ?
                                                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhmY2EzNDVlNGZkOWM0MjJiNzNjNGMxYzUwNTZmMzc5ZGU5MjUxMGZjOTRiNGNjOTA3ZmIyMGNlNzUwZGM5MCJ9fX0"
                                                                :
                                                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIzZDdlNjU1ZGViZGQ1OTFkMDk5ZDc2ZmYwMDBkNzU1NWJlNGFlMTFiMWUyNmI5YWRmMjQ0YWUwMjJiMjljOCJ9fX0"), "#FF9920&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                format(Tier.TIER3.getRequiredHours(), hours, 3), false),
                                        CustomItem.MakeItem(GetCustomSkull.getCustomSkull(
                                                        tiers.containsKey(Tier.TIER4) ?
                                                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhmY2EzNDVlNGZkOWM0MjJiNzNjNGMxYzUwNTZmMzc5ZGU5MjUxMGZjOTRiNGNjOTA3ZmIyMGNlNzUwZGM5MCJ9fX0"
                                                                :
                                                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIzZDdlNjU1ZGViZGQ1OTFkMDk5ZDc2ZmYwMDBkNzU1NWJlNGFlMTFiMWUyNmI5YWRmMjQ0YWUwMjJiMjljOCJ9fX0"), "#FF9920&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                format(Tier.TIER4.getRequiredHours(), hours, 4), false),
                                        CustomItem.MakeItem(GetCustomSkull.getCustomSkull(
                                                        tiers.containsKey(Tier.TIER5) ?
                                                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhmY2EzNDVlNGZkOWM0MjJiNzNjNGMxYzUwNTZmMzc5ZGU5MjUxMGZjOTRiNGNjOTA3ZmIyMGNlNzUwZGM5MCJ9fX0"
                                                                :
                                                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIzZDdlNjU1ZGViZGQ1OTFkMDk5ZDc2ZmYwMDBkNzU1NWJlNGFlMTFiMWUyNmI5YWRmMjQ0YWUwMjJiMjljOCJ9fX0"), "#FF9920&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                format(Tier.TIER5.getRequiredHours(), hours, 5), false),
                                        CustomItem.MakeItem(GetCustomSkull.getCustomSkull(
                                                        tiers.containsKey(Tier.TIER6) ?
                                                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhmY2EzNDVlNGZkOWM0MjJiNzNjNGMxYzUwNTZmMzc5ZGU5MjUxMGZjOTRiNGNjOTA3ZmIyMGNlNzUwZGM5MCJ9fX0"
                                                                :
                                                                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIzZDdlNjU1ZGViZGQ1OTFkMDk5ZDc2ZmYwMDBkNzU1NWJlNGFlMTFiMWUyNmI5YWRmMjQ0YWUwMjJiMjljOCJ9fX0"), "#FF9920&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                format(Tier.TIER6.getRequiredHours(), hours, 6), false)



                                ).map(i -> NBTEditor.set(i, 1010, "CustomModelData"))
                                .toList();

                        contents.set(List.of(13), IntelligentItem.of(entries.get(0), event -> unlockPlayTimeReward(player, tiers, Tier.TIER1, hours, data)));
                        contents.set(List.of(20), IntelligentItem.of(entries.get(1), event -> unlockPlayTimeReward(player, tiers, Tier.TIER2, hours, data)));
                        contents.set(List.of(24), IntelligentItem.of(entries.get(2), event -> unlockPlayTimeReward(player, tiers, Tier.TIER3, hours, data)));
                        contents.set(List.of(36), IntelligentItem.of(entries.get(3), event -> unlockPlayTimeReward(player, tiers, Tier.TIER4, hours, data)));
                        contents.set(List.of(44), IntelligentItem.of(entries.get(4), event -> unlockPlayTimeReward(player, tiers, Tier.TIER5, hours, data)));
                        contents.set(List.of(39), IntelligentItem.of(entries.get(5), event -> unlockPlayTimeReward(player, tiers, Tier.TIER6, hours, data)));


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

                p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You have unlocked this tier and earned " + flories + " flories, $" + money + ", " + dna + " DNA, and " + seasonalKeys + " Seasonal Keys."));
                p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, (float) 1.3);

                ItemStack key3 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lSeasonal Crate Key", "", false), 3, "CustomModelData");

                for (int i = 0; i < seasonalKeys; i++) {p.getInventory().addItem(key3);}
                VaultHandler.addMoney(p, money);
                data.setDna(data.getDna() + dna);
                data.setFlories(data.getFlories() + flories);


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
