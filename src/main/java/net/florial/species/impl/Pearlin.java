package net.florial.species.impl;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.florial.Florial;
import net.florial.features.skills.Skill;
import net.florial.species.Species;
import net.florial.utils.Cooldown;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pearlin extends Species implements Listener {
    public Pearlin(int id) {
        super("Pearlin", id, 32, true, DisguiseType.FOX);
    }


    @Override
    public Set<String> descriptions() {

        return new HashSet<>(Arrays.asList(
                "Heal", "Right-Clicking entities heals them more"));
    }

    @Override
    public Set<PotionEffect> effects() {

        return new HashSet<>(List.of(
                new PotionEffect(PotionEffectType.SPEED, 1000000, 0, false, false, true),
                new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 1, false, false, true)));
    }

    @Override
    public Set<Integer> sharedAbilities() {

        return new HashSet<>(List.of(
                1, 3));
    }

    @EventHandler
    public void healEntity(PlayerInteractEntityEvent e) {

        if (!(e.getRightClicked() instanceof LivingEntity)
                || Florial.getPlayerData().get(e.getPlayer().getUniqueId()).getSpecies() != this
                || (Cooldown.isOnCooldown("c1", e.getPlayer())
                || Florial.getOngoingDuel().get(e.getPlayer().getUniqueId()) != null)) return;

        Player p = e.getPlayer();

        int specific = Florial.getPlayerData().get(p.getUniqueId()).getSkills().get(Skill.SPECIFIC);

        double newHealth = (p.getHealth() + specific > 32) ? 32 : p.getHealth() + specific+1;

        p.setHealth(newHealth);
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, specific*200, (specific+1)-2, false, false, true));

        Cooldown.addCooldown("c1", p, 10);
        p.getWorld().spawnParticle(Particle.HEART, p.getLocation(), 10, 0.5, 0.5, 0.5, 0.1);

    }
}
