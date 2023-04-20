package net.florial.species.impl.usermade;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.florial.Florial;
import net.florial.species.Species;
import net.florial.utils.math.GetChance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Windowsian extends Species implements Listener {
    public Windowsian(int id) {
        super("Windowsian", id, 20, false, null);
    }


    @Override
    public Set<PotionEffect> effects() {

        return new HashSet<>(List.of(
                new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false, true),
                new PotionEffect(PotionEffectType.FAST_DIGGING, 1000000, 0, false, false, true),
                new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 1, false, false, true),
                new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 9, false, false, true)));
    }

    @Override
    public Set<String> descriptions() {

        return new HashSet<>(Arrays.asList(
                "NONE", "none"));
    }


    @EventHandler
    public void powerUsageBreak(BlockBreakEvent event) {

        if (Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this) return;

        powerUsage(event.getPlayer());

    }

    @EventHandler
    public void powerUsageAttack(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player p)
            || Florial.getPlayerData().get(event.getDamager().getUniqueId()).getSpecies() != this) return;

        powerUsage(p);

    }

    private static void powerUsage(Player p) {if (GetChance.chanceOf(50) && (p.getFoodLevel() > 0)) p.setFoodLevel(p.getFoodLevel() - 1);}
}
