package net.florial.menus.species;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.features.age.Age;
import net.florial.features.upgrades.Upgrade;
import net.florial.models.PlayerData;
import net.florial.species.disguises.Morph;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.GetCustomSkull;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Stream;

public class RebirthMenu {

    GetCustomSkull GetCustomSkull = new GetCustomSkull();
    Morph morph = new Morph();

    public void rebirthMenu(Player p) {

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE614"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

                        String mileStone1Completed = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIzZDdlNjU1ZGViZGQ1OTFkMDk5ZDc2ZmYwMDBkNzU1NWJlNGFlMTFiMWUyNmI5YWRmMjQ0YWUwMjJiMjljOCJ9fX0";
                        String mileStone2Completed = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIzZDdlNjU1ZGViZGQ1OTFkMDk5ZDc2ZmYwMDBkNzU1NWJlNGFlMTFiMWUyNmI5YWRmMjQ0YWUwMjJiMjljOCJ9fX0";
                        String mileStone3Completed = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIzZDdlNjU1ZGViZGQ1OTFkMDk5ZDc2ZmYwMDBkNzU1NWJlNGFlMTFiMWUyNmI5YWRmMjQ0YWUwMjJiMjljOCJ9fX0";
                        String mileStone4Completed = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIzZDdlNjU1ZGViZGQ1OTFkMDk5ZDc2ZmYwMDBkNzU1NWJlNGFlMTFiMWUyNmI5YWRmMjQ0YWUwMjJiMjljOCJ9fX0";
                        String mileStone5Completed = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIzZDdlNjU1ZGViZGQ1OTFkMDk5ZDc2ZmYwMDBkNzU1NWJlNGFlMTFiMWUyNmI5YWRmMjQ0YWUwMjJiMjljOCJ9fX0";


                        if (!(data.getUpgrades().isEmpty())) {
                            mileStone1Completed = data.getUpgrades().get(Upgrade.STRONGNOSE) != null ? "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhmY2EzNDVlNGZkOWM0MjJiNzNjNGMxYzUwNTZmMzc5ZGU5MjUxMGZjOTRiNGNjOTA3ZmIyMGNlNzUwZGM5MCJ9fX0" : mileStone1Completed;
                            mileStone2Completed = data.getUpgrades().get(Upgrade.SELLINCREASE) != null ? "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhmY2EzNDVlNGZkOWM0MjJiNzNjNGMxYzUwNTZmMzc5ZGU5MjUxMGZjOTRiNGNjOTA3ZmIyMGNlNzUwZGM5MCJ9fX0" : mileStone2Completed;
                            mileStone3Completed = data.getUpgrades().get(Upgrade.DOUBLEHEALTH) != null ? "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhmY2EzNDVlNGZkOWM0MjJiNzNjNGMxYzUwNTZmMzc5ZGU5MjUxMGZjOTRiNGNjOTA3ZmIyMGNlNzUwZGM5MCJ9fX0" : mileStone3Completed;
                            mileStone4Completed = data.getUpgrades().get(Upgrade.NATUREIMMUNITY) != null ? "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhmY2EzNDVlNGZkOWM0MjJiNzNjNGMxYzUwNTZmMzc5ZGU5MjUxMGZjOTRiNGNjOTA3ZmIyMGNlNzUwZGM5MCJ9fX0" : mileStone4Completed;
                            mileStone5Completed = data.getUpgrades().get(Upgrade.STRONGNOSE) != null ? "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhmY2EzNDVlNGZkOWM0MjJiNzNjNGMxYzUwNTZmMzc5ZGU5MjUxMGZjOTRiNGNjOTA3ZmIyMGNlNzUwZGM5MCJ9fX0" : mileStone5Completed;


                        }


                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(GetCustomSkull.getCustomSkull(mileStone1Completed), "#FF9920&l ┍━━━━━━━━━━━━━━━━━━┑",
                                        " #FF9920&l︳• #FFB65FSTRONGER SCENT\n"
                                                + " #FF9920&l︳• #FFB65F15 Reincarnations"
                                                + "\n #FF9920&l┕━━━━━━━━━━━━━━━━━━┙\n"
                                                + "#FF9920&l︳ • CLICK REBIRTH TO REINCARNATE"
                                                + "\n #FF9920&l︳•#FFB65F [CLICK TO UNLOCK]\n #FF9920&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(GetCustomSkull.getCustomSkull(mileStone2Completed), "#FF9920&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                " #FF9920&l︳• #FFB65F2x SELL INCREASE\n"
                                                        + " #FF9920&l︳• #FFB65F35 Reincarnations"
                                                        + "\n #FF9920&l┕━━━━━━━━━━━━━━━━━━┙\n"
                                                        + "#FF9920&l︳ • CLICK REBIRTH TO REINCARNATE"
                                                        + "\n #FF9920&l︳•#FFB65F [CLICK TO UNLOCK]\n #FF9920&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(GetCustomSkull.getCustomSkull(mileStone3Completed), "#FF9920&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                " #FF9920&l︳• #FFB65F2X ROW HEARTS\n"
                                                        + " #FF9920&l︳• #FFB65F50 Reincarnations"
                                                        + "\n #FF9920&l┕━━━━━━━━━━━━━━━━━━┙\n"
                                                        + "#FF9920&l︳ • CLICK REBIRTH TO REINCARNATE"
                                                        + "\n #FF9920&l︳•#FFB65F [CLICK TO UNLOCK]\n #FF9920&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(GetCustomSkull.getCustomSkull(mileStone4Completed), "#FF9920&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                " #FF9920&l︳• #FFB65FNATURE IMMUNITY\n"
                                                        + " #FF9920&l︳• #FFB65F75 Reincarnations"
                                                        + "\n #FF9920&l┕━━━━━━━━━━━━━━━━━━┙\n"
                                                        + "#FF9920&l︳ • CLICK REBIRTH TO REINCARNATE"
                                                        + "\n #FF9920&l︳•#FFB65F [CLICK TO UNLOCK]\n #FF9920&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(GetCustomSkull.getCustomSkull(mileStone5Completed), "#FF9920&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                " #FF9920&l︳• #FFB65FSTRONGER SCENT\n"
                                                        + " #FF9920&l︳• #FFB65F25 Reincarnations"
                                                        + "\n #FF9920&l┕━━━━━━━━━━━━━━━━━━┙\n"
                                                        + "#FF9920&l︳ • CLICK REBIRTH TO REINCARNATE"
                                                        + "\n #FF9920&l︳•#FFB65F [CLICK TO UNLOCK]\n #FF9920&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#FF9920&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                " #FF9920&l︳• #FFB65FREINCARNATION\n"
                                                        + " #FF9920&l︳• #FFB65F25 Times Reincarnated: " + data.getReincarnations()
                                                        + "\n #FF9920&l┕━━━━━━━━━━━━━━━━━━┙\n"
                                                        + "#FF9920&l︳ • Requires Elder Lifestage (/grow)\n"
                                                        + "#FF9920&l︳ • Gives 50 Flories & 1 Reincarnation"
                                                        + "\n #FF9920&l︳•#FFB65F [CLICK TO REINCARNATE]\n #FF9920&l┕━━━━━━━━━━━━━━━━━━┙", false)

                                ).map(i -> NBTEditor.set(i, 1010, "CustomModelData"))
                                .toList();

                        contents.set(List.of(13), IntelligentItem.of(entries.get(0), event -> {
                            unlockMilestone(p, data, 15, Upgrade.STRONGNOSE);
                        }));
                        contents.set(List.of(20), IntelligentItem.of(entries.get(1), event -> {
                            unlockMilestone(p, data, 35, Upgrade.SELLINCREASE);
                        }));
                        contents.set(List.of(24), IntelligentItem.of(entries.get(2), event -> {
                            unlockMilestone(p, data, 50, Upgrade.DOUBLEHEALTH);
                        }));
                        contents.set(List.of(36), IntelligentItem.of(entries.get(3), event -> {
                            unlockMilestone(p, data, 75, Upgrade.NATUREIMMUNITY);
                        }));
                        contents.set(List.of(44), IntelligentItem.of(entries.get(4), event -> {
                            unlockMilestone(p, data, 100, Upgrade.STRONGNOSE);
                        }));

                        //13,20,24,36,44
                          contents.set(List.of(29,30,31,32,33,38,39,40,41,42), IntelligentItem.of(entries.get(5), event -> {activateReincarnation(p, data);}));

                    }
                })
                .build(Florial.getInstance())
                .open(p);


    }

    private void unlockMilestone(Player p, PlayerData data, int required, Upgrade milestone) {

        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);
        p.closeInventory();

        if (data.getReincarnations() >= required) {

            if (data.getUpgrades().containsKey(milestone)) {
                p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c Already unlocked this Milestone!"));
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
                return;
            }

            data.getUpgrades().put(milestone, true);


            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 1);
            p.playSound(p.getLocation(), Sound.ENTITY_PILLAGER_CELEBRATE, 1, 1);

            rebirthMenu(p);


        } else {

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You need to Reincarnate at least " + required + " times to unlock this Milestone. Click the 'REBIRTH' button in /reincarnation as an Elder Lifestage from /grow to Reincarnate."));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
        }
    }

    private void activateReincarnation(Player p, PlayerData data) {

        p.closeInventory();

        if (data.getAge() == Age.ELDER) {

            p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, 1);
            p.playSound(p.getLocation(), Sound.ENTITY_PILLAGER_CELEBRATE, 1, 1);

            data.setAge(data.getSpecieId() == 3 ? Age.ADOLESCENT : Age.KIT);
            data.setReincarnations(data.getReincarnations() + 1);
            morph.activate(p, 6, false, true, data.getSpecies());

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&6&l&o REINCARNATED!"));


        } else {
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c To REBIRTH/REINCARNATE you must be the ELDER Lifestage achievable in /grow!"));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
        }

    }
}

