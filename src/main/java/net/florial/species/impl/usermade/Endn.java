package net.florial.species.impl.usermade;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.florial.Florial;
import net.florial.species.Species;
import org.bukkit.*;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;


public class Endn extends Species implements Listener {

    public Endn(int id) {
        super("Endn", id, 22, false, DisguiseType.ENDERMAN);
    }


    @Override
    public Set<Integer> sharedAbilities() {

        return new HashSet<>(List.of(
                3));
    }

    @EventHandler
    public void silkTouchHands(BlockBreakEvent e) {

        if (Florial.getPlayerData().get(e.getPlayer().getUniqueId()).getSpecies() != this) return;

        e.setDropItems(false);

        Location loc = e.getBlock().getLocation();

        loc.getWorld().dropItem(loc, new ItemStack(e.getBlock().getType()));

    }

    @Override
    public Set<String> descriptions() {

        return new HashSet<>(Arrays.asList(
                "NONE", "none"));
    }
    @EventHandler
    public void randomTeleport(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
        || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this
        || event.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) return;

        Player player = event.getPlayer();
        Location currentLocation = player.getLocation();

        World world = currentLocation.getWorld();

        Random random = new Random();
        int xOffset = random.nextInt(21) - 10;
        int zOffset = random.nextInt(21) - 10;
        Location randomLocation = new Location(world, currentLocation.getX() + xOffset, currentLocation.getY(), currentLocation.getZ() + zOffset);

        player.teleport(randomLocation);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 3);
        world.spawnParticle(Particle.PORTAL, randomLocation, 2);

    }

    @EventHandler
    public void enderManImmunity(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player) || (!(e.getDamager() instanceof Enderman)
        || Florial.getPlayerData().get(e.getEntity().getUniqueId()).getSpecies() != this)) e.setCancelled(true);

    }
}
