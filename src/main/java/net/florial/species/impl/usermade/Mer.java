package net.florial.species.impl.usermade;

import net.florial.Florial;
import net.florial.species.Species;
import net.florial.utils.Cooldown;
import net.florial.utils.game.LineOfSight;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.PufferFish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class Mer extends Species implements Listener {
    public Mer(int id) {
        super("Mer", id, 20, false, null);
    }

    @Override
    public Set<PotionEffect> effects() {

        return new HashSet<>(List.of(
                new PotionEffect(PotionEffectType.WATER_BREATHING, 1000000, 0, false, false, true),
                new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 1000000, 0, false, false, true),
                new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 0, false, false, true)));
    }

    @Override
    public Set<Integer> sharedAbilities() {

        return new HashSet<>(List.of(
                5));
    }

    @Override
    public Set<Material> diet() {
        return new HashSet<>(Arrays.asList(
                Material.BEEF, Material.PORKCHOP,
                Material.CHICKEN, Material.MUTTON,
                Material.TROPICAL_FISH, Material.COD,
                Material.SALMON, Material.DRIED_KELP));
    }


    @EventHandler
    public void waterFish(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
                || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this
                || Cooldown.isOnCooldown("c1", event.getPlayer())
                || LineOfSight.get(event.getPlayer(), Material.WATER, 5)) return;

        Player player = event.getPlayer();

        List<Material> fishTypes = Arrays.asList(Material.COD, Material.SALMON, Material.TROPICAL_FISH, Material.PUFFERFISH);

        player.getInventory().addItem(new ItemStack(fishTypes.get(new Random().nextInt(fishTypes.size()))));
        player.playSound(player.getLocation(), Sound.ENTITY_COD_FLOP, 1.0f, 1.0f);

        Cooldown.addCooldown("c1", player, 10);

    }

    @EventHandler
    public void hasteUnderwater(BlockBreakEvent event) {

        if (Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this) return;

        Player p = event.getPlayer();

        if (p.getWorld().getBlockAt(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ())).getType() != Material.WATER) return;

        event.setCancelled(true);
        event.getBlock().breakNaturally();

    }

    @EventHandler
    public void damageBoostInWater(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player p)) return;

        if (Florial.getPlayerData().get(event.getDamager().getUniqueId()).getSpecies() != this) return;

        if (p.getWorld().getBlockAt(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ())).getType() != Material.WATER) return;

        event.setDamage(event.getDamage() + 6);

    }

    @EventHandler
    public void landVulnerability(EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player p)
        || Florial.getOngoingDuel().get(p.getUniqueId()) != null) return;

        if (Florial.getPlayerData().get(event.getEntity().getUniqueId()).getSpecies() != this) return;

        if (p.getWorld().getBlockAt(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 2, p.getLocation().getZ())).getType() == Material.WATER) return;

        event.setDamage(event.getDamage() + 4);

    }

    @EventHandler
    public void pufferFishImmunity(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof PufferFish)
                || (!(event.getEntity() instanceof Player)
                || Florial.getPlayerData().get(event.getEntity().getUniqueId()).getSpecies() != this)) return;

        event.setCancelled(true);
    }

}
