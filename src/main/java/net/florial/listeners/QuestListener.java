package net.florial.listeners;

import net.florial.Florial;
import net.florial.features.quests.Quest;
import net.florial.features.quests.QuestType;
import net.florial.features.quests.events.impl.QuestProgressEvent;
import net.florial.utils.game.RegionDetector;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.*;

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

        for (int i = 0; i < event.getItemDrop().getItemStack().getAmount(); i++) {callProgressEvent(p, Florial.getQuest().get(p.getUniqueId()), QuestType.DELIVER);}

        event.getItemDrop().remove();


    }

    @EventHandler
    public void questFish(PlayerFishEvent event) {
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH
            || (!Florial.getQuest().containsKey(event.getPlayer().getUniqueId()))) return;

        if (Florial.getQuest().get(event.getPlayer().getUniqueId()).getItemType() != ((Item) Objects.requireNonNull(event.getCaught())).getItemStack().getType()) return;

        Player p = event.getPlayer();

        callProgressEvent(p, Florial.getQuest().get(p.getUniqueId()), QuestType.FISH);

    }

    @EventHandler
    public void questBurrow(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK ||
                (!Florial.getQuest().containsKey(event.getPlayer().getUniqueId())
                        || (!RegionDetector.detect(event.getPlayer().getLocation()).contains("fox")))) return;

        Player p = event.getPlayer();
        Quest quest = Florial.getQuest().get(p.getUniqueId());
        Block b = event.getClickedBlock();

        if (quest == null
                || quest.getType() != QuestType.BURROW
                || p.getFoodLevel() < 1
                || b == null
                || (!(b.getType().toString().contains("DIRT")))) return;

        Material originalMaterial = b.getType();
        b.setType(Material.SPRUCE_STAIRS);
        p.setFoodLevel(p.getFoodLevel() - 1);

        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> b.setType(originalMaterial), 100L);

        callProgressEvent(p, Florial.getQuest().get(p.getUniqueId()), QuestType.BURROW);
        p.playSound(p.getLocation(), Sound.BLOCK_ROOTED_DIRT_BREAK, 1, 2);
        p.playSound(p.getLocation(), Sound.BLOCK_PACKED_MUD_BREAK, 1, 2);

    }

    @EventHandler
    public void questWild(PlayerCommandPreprocessEvent event){
        if (!Florial.getQuest().containsKey(event.getPlayer().getUniqueId())
                        || (!(event.getMessage().contains("wild")))) return;

        Player p = event.getPlayer();

        callProgressEvent(p, Florial.getQuest().get(p.getUniqueId()), QuestType.WILD);

    }

    private static void callProgressEvent(Player p, Quest quest, QuestType type) {

        QuestProgressEvent progress = new QuestProgressEvent(
                p,
                quest,
                type);

        Bukkit.getPluginManager().callEvent(progress);

    }

}
