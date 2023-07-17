package net.florial.species.impl.usermade;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.florial.Florial;
import net.florial.species.Species;
import net.florial.utils.Cooldown;
import net.florial.utils.game.GetTarget;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MagicDuck extends Species implements Listener {
    public MagicDuck(int id) {
        super("Magic Duck", id, 20, true, DisguiseType.CHICKEN);
    }


    @Override
    public Set<PotionEffect> effects() {

        return new HashSet<>(List.of(
                new PotionEffect(PotionEffectType.SLOW_FALLING, 1000000, 1, false, false, true),
                new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 1000000, 1, false, false, true)));
    }

    @Override
    public Set<String> descriptions() {

        return new HashSet<>(Arrays.asList(
                "NONE", "none"));
    }

    @Override
    public Set<Material> diet() {
        return new HashSet<>(Arrays.asList(
                Material.COD, Material.SALMON,
                Material.TROPICAL_FISH, Material.PUFFERFISH,
                Material.POTION));
    }

    @EventHandler
    public void farPoison(PlayerInteractEvent e) {
        if (e.getAction() != Action.LEFT_CLICK_AIR
        || Florial.getPlayerData().get(e.getPlayer().getUniqueId()).getSpecies() != this
        || Cooldown.isOnCooldown("c1", e.getPlayer())
        || Florial.getOngoingDuel().get(e.getPlayer().getUniqueId()) != null) return;

        Player p = e.getPlayer();

        LivingEntity ent = GetTarget.of(p);

        if (ent == p || (ent == null)) return;

        ent.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0, false, false, true));
        ent.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 0, false, false, true));

        Cooldown.addCooldown("c1", p, 30);
    }


    @EventHandler
    public void eatBook(PlayerInteractEvent e) {
        if (e.getAction() != Action.LEFT_CLICK_AIR
            || Florial.getPlayerData().get(e.getPlayer().getUniqueId()).getSpecies() != this ||
                (!(e.getPlayer().getInventory().getItemInMainHand().getType().toString().contains("BOOK")))) return;

        Player p = e.getPlayer();
        p.setFoodLevel((p.getFoodLevel() + 6 > 19) ? 20 : p.getFoodLevel()+6);
        p.playSound(p, Sound.ENTITY_GENERIC_EAT, 1, 1);

    }

    @EventHandler
    public void strengthOnKill(EntityDeathEvent e) {

        if (e.getEntity().getKiller() == null || Florial.getPlayerData().get(e.getEntity().getUniqueId()) == null) return;

        if (Florial.getPlayerData().get(e.getEntity().getUniqueId()).getSpecies() != this
            || Cooldown.isOnCooldown("c2", (Player) e.getEntity())) return;

        Player p = (Player) e.getEntity();

        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2400, 1, false, false, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2400, 1, false, false, true));
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2400, 1, false, false, true));
        Cooldown.addCooldown("c2", p, 300);

    }

    @EventHandler
    public void extraHunger(FoodLevelChangeEvent e) {
        Player p = (Player) e.getEntity();
        if (Florial.getPlayerData().get(p.getUniqueId()).getSpecies() == this
                && p.getWorld().getBlockAt(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ())).getType() == Material.WATER) e.setFoodLevel(e.getFoodLevel() - 1);

    }

}
