package net.florial.menus;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.features.dailyrewards.Reward;
import net.florial.features.dailyrewards.Weekday;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class DailyRewardMenu {


    public void dailyRewardMenu(Player p) {

        RyseInventory.builder()
                .title(CC.translate("#ff79a1&lDAILY REWARDS"))
                .rows(1)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

                        HashMap<Weekday, Boolean> playersWeek = data.getLoggedInDays();

                        int slot = 1;
                        for (Weekday weekday : Weekday.values()) {

                            ItemStack i = CustomItem.MakeItem(new ItemStack((playersWeek.get(weekday) ? Material.PINK_CONCRETE : Material.LIGHT_GRAY_CONCRETE)),
                                    "#ff79a1&l" + weekday.name(), "", true);

                            contents.set(slot, IntelligentItem.of(i, event -> {
                                Reward.give(weekday, p);
                                p.playSound(p.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1, (float) 2);

                            }));

                            slot++;
                        }


                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }
}
