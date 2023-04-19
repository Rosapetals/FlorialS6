package net.florial.listeners;

import net.florial.Florial;
import net.florial.features.quests.Quest;
import net.florial.features.quests.QuestType;
import net.florial.features.quests.events.impl.QuestProgressEvent;
import net.florial.utils.general.RegionDetector;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.Objects;

public class QuestListener implements Listener {

    @EventHandler
    public void questCollectChecker(CraftItemEvent event) {

        if (!(Florial.getQuest().containsKey(event.getWhoClicked().getUniqueId()))) return;

        if (Florial.getQuest().get(event.getWhoClicked().getUniqueId()).getCraftType() != Objects.requireNonNull(event.getCurrentItem()).getType()) return;

        Player p = (Player) event.getWhoClicked();

        callProgressEvent(p, Florial.getQuest().get(p.getUniqueId()), QuestType.CRAFT);

    }

    @EventHandler
    public void questMobKill(EntityDeathEvent event) {

        Player killer = event.getEntity().getKiller();
        if (killer == null || !Florial.getQuest().containsKey(killer.getUniqueId())) return;

        if (Florial.getQuest().get(killer.getUniqueId()).getMobType() != event.getEntityType()) return;


        callProgressEvent(killer, Florial.getQuest().get(killer.getUniqueId()), QuestType.KILL);

    }

    @EventHandler
    public void questEat(PlayerItemConsumeEvent event) {

        if (!Florial.getQuest().containsKey(event.getPlayer().getUniqueId())) return;

        if (Florial.getQuest().get(event.getPlayer().getUniqueId()).getItemType() != event.getItem().getType()) return;

        Player p = event.getPlayer();

        callProgressEvent(p, Florial.getQuest().get(p.getUniqueId()), QuestType.EAT);

    }

    @EventHandler
    public void questDeliverItem(PlayerDropItemEvent event) {

        if (!Florial.getQuest().containsKey(event.getPlayer().getUniqueId())) return;

        if (!RegionDetector.detect(event.getPlayer().getLocation()).contains("collect")) return;

        if (Florial.getQuest().get(event.getPlayer().getUniqueId()).getItemType() != event.getItemDrop().getItemStack().getType()) return;

        Player p = event.getPlayer();

        callProgressEvent(p, Florial.getQuest().get(p.getUniqueId()), QuestType.DELIVER);

    }

    private static void callProgressEvent(Player p, Quest quest, QuestType type) {

        QuestProgressEvent progress = new QuestProgressEvent(
                p,
                quest,
                type);

        Bukkit.getPluginManager().callEvent(progress);

    }

}
