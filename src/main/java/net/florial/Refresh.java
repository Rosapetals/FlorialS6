package net.florial;

import net.florial.features.skills.Skill;
import net.florial.features.upgrades.Upgrade;
import net.florial.models.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class Refresh {

    /*
    This is a necessary, unique class
    Over the course of a user's lifetime, he may get
    several various upgrades, or may have skills that give buffs.
    Because of how minecraft works, these buffs fade after a restart or on death
    therefore it's our responsibility to load it everytime he dies or rejoins.
    additionally he may get max health enhancements somehow, someway. it is
    therefore our responsibility to load that too in the off-case it is somehow lost
     */

    private static final Florial florial = Florial.getInstance();

    public static void load(Player p, PlayerData data) {

        // now additions shall be.. have we anything to add to the max health?
        int additions = 0;
        AtomicReference<Double> maxHealth = new AtomicReference<>(data.getSpecies().getMaxHealth() + (data.getAge().getId() == 2 ? 1 :
                data.getAge().getId() > 2 ? 2 : 0));
        HashMap<Skill, Integer> skills = data.getSkills();
        // shall we get upgrades? if it is null leave it blank! We needn't assign upgrades to players who will never get them, so let that not be null overtime
        HashMap<Upgrade, Boolean> upgrades = data.getUpgrades() != null ? data.getUpgrades() : new HashMap<>();
        int resistance = skills.get(Skill.RESISTANCE);
        int survival = skills.get(Skill.SURVIVAL);
        int specific = skills.get(Skill.SPECIFIC);

        // let's see if the user has upgraded resistance?
        if (resistance > 1) mainThread(p, PotionEffectType.DAMAGE_RESISTANCE, null,resistance-2);

        if (skills.get(Skill.STRENGTH) > 1)   mainThread(p, PotionEffectType.INCREASE_DAMAGE, null,skills.get(Skill.STRENGTH)-2);

        //we use this statement to check if survival is 20 or just 5. because at those levels max health INCREASES!

        if (survival > 4) additions = survival >= 20 ? 6 : 4;

        if (survival > 14)  mainThread(p, PotionEffectType.REGENERATION, null, 0);

        //let's loop through their specie's unique skill set and apply all necessary effects
        for (Map.Entry<Integer, PotionEffect> entry : data.getSpecies().specific().entrySet()) {
            boolean applicable = specific >= entry.getKey() && entry.getValue() != null;
            if (applicable) {
                mainThread(p, null, entry.getValue(), 0);
            } else {
                break;
            }
        }

        // let's set their maxhealth now
        p.setMaxHealth(maxHealth.get() + additions);

        // OK! let's begin the Great Upgrade Check, and then just stop this whole code if our player hasn't got an upgrade yet!
        if (upgrades.isEmpty()) return;
        Map<Upgrade, Runnable> upgradeHandlers = new HashMap<>() {{
            put(Upgrade.DOUBLEHEALTH, () -> maxHealth.set(Math.max(maxHealth.get(), 40)));
            put(Upgrade.HASTE, () ->  mainThread(p, PotionEffectType.FAST_DIGGING, null,1));
        }};

        for (Map.Entry<Upgrade, Runnable> entry : upgradeHandlers.entrySet()) {
            Upgrade upgrade = entry.getKey();
            Runnable handler = entry.getValue();
            if (upgrades.get(upgrade)) handler.run();
        }
        //this runs twice for the aforementioned reasons.. let's see if we can get this down to running once in some way!
        p.setMaxHealth(maxHealth.get() + additions);
    }

    private static void mainThread(Player p, PotionEffectType effect, PotionEffect potion, int amount) {

        Bukkit.getScheduler().runTaskLater(florial, () -> {
            p.addPotionEffect(Objects.requireNonNullElseGet(potion, () -> new PotionEffect(effect, 1000000, amount, false, false, true)));
        }, 5L);

    }
}
