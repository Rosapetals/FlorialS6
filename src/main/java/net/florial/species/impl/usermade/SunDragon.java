package net.florial.species.impl.usermade;

import net.florial.Florial;
import net.florial.species.Species;
import net.florial.utils.Cooldown;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SunDragon extends Species implements Listener {

    public SunDragon(int id) {
        super("Sun Dragon", id, 20, true, null);
    }


    @Override
    public Set<Integer> sharedAbilities() {

        return new HashSet<>(List.of(
                2));
    }

    @Override
    public Set<String> descriptions() {

        return new HashSet<>(Arrays.asList(
                "NONE", "none"));
    }

    @EventHandler
    public void throwFireBall(PlayerInteractEvent e) {

        if (e.getAction() != Action.LEFT_CLICK_AIR
                || Florial.getPlayerData().get(e.getPlayer().getUniqueId()).getSpecies() != this
        || Cooldown.isOnCooldown("c1", e.getPlayer())
        || e.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR
        || Florial.getOngoingDuel().get(e.getPlayer().getUniqueId()) != null) return;

        Player p = e.getPlayer();

        Fireball f = p.launchProjectile(Fireball.class);
        f.setYield(0);
        f.setVelocity(p.getLocation().getDirection().multiply(2));

        Cooldown.addCooldown("c1", p, 15);

    }

}
