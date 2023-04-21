package net.florial.features.quests;

import lombok.Getter;
import lombok.Setter;
import net.florial.Florial;
import net.florial.utils.general.VaultHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Quest {

    private static final QuestGenerator quest = new QuestGenerator();

    @Getter private final String name;
    @Getter private final QuestType type;
    @Getter private final int target;
    @Getter
    private Material craftType;
    @Getter private EntityType mobType;
    @Getter private Material itemType;
   @Getter @Setter
   private Integer progress;

    public Quest(String name, QuestType type, int target, Material craftType, EntityType mobType, Material itemType, Integer progress) {
        this.name = name;
        this.type = type;
        this.target = target;
        this.craftType = craftType;
        this.mobType = mobType;
        this.itemType = itemType;
        this.progress = progress;

    }

    @Override
    public String toString() {
        return name + " (" + progress + "/" + target + ")";
    }

    public static void give(Player p) {

        UUID u = p.getUniqueId();
        Quest questData = quest.generateQuest();

        p.closeInventory();

        Florial.getQuest().put(u, questData);

        if (Florial.getQuestBar().containsKey(u)) {

            double balance = VaultHandler.getBalance(p);

            double cost = (balance * 0.10);
            cost = cost < 50 ? 500 : cost;

            if (balance >= cost) {
                Florial.getQuestBar().get(u).removePlayer(p);
                VaultHandler.removeMoney(p, cost);
            } else {
                p.sendMessage("Based upon your current balance, you need $" + cost + " to roll for a new quest. You are short by $" + (cost-balance));
                return;
            }

        }

        BossBar boss = Bukkit.createBossBar(
                "Quest Progress: " + Florial.getQuest().get(p.getUniqueId()).toString(),
                BarColor.RED,
                BarStyle.SOLID);

        boss.setProgress(0.0);
        boss.addPlayer(p);

        Florial.getQuestBar().put(p.getUniqueId(), boss);

        p.sendMessage("View the top of your screen to view quest progress. Don't like your quest? Click the GET QUEST button again to get a new one for some money!");
    }


}
