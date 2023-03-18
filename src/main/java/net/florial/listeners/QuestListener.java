package net.florial.listeners;

import net.florial.Florial;
import net.florial.features.quests.QuestType;
import net.florial.features.quests.events.impl.QuestProgressEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class QuestListener implements Listener {

    @EventHandler
    public void questCollectChecker(BlockBreakEvent event) {

        if (!(Florial.getQuest().containsKey(event.getPlayer().getUniqueId()))) return;

        if (Florial.getQuest().get(event.getPlayer().getUniqueId()).getBlockType() != event.getBlock().getType()) return;

        QuestProgressEvent progress = new QuestProgressEvent(
                event.getPlayer(),
                Florial.getQuest().get(event.getPlayer().getUniqueId()),
                QuestType.KILL);

        Bukkit.getPluginManager().callEvent(progress);

    }

    @EventHandler
    public void questMobKill(EntityDeathEvent event) {

        Player killer = event.getEntity().getKiller();
        if (killer == null || !Florial.getQuest().containsKey(killer.getUniqueId())) return;

        if (Florial.getQuest().get(killer.getUniqueId()).getMobType() == null) return;

        QuestProgressEvent progress = new QuestProgressEvent(
                killer,
                Florial.getQuest().get(killer.getUniqueId()),
                QuestType.KILL);

        Bukkit.getPluginManager().callEvent(progress);

    }

}
