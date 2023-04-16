package net.florial.species.impl.usermade;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.florial.Florial;
import net.florial.species.Species;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NatureFox extends Species implements Listener {
    public NatureFox(int id) {
        super("Naturefox", id, 16, true, DisguiseType.FOX);
    }

    @Override
    public Set<Integer> sharedAbilities() {

        return new HashSet<>(List.of(
                4));
    }

    @Override
    public Set<PotionEffect> effects() {

        return new HashSet<>(List.of(
                new PotionEffect(PotionEffectType.REGENERATION, 1000000, 0, false, false, true),
                new PotionEffect(PotionEffectType.SPEED, 1000000, 0, false, false, true)));
    }

    @Override
    public Set<String> descriptions() {
        return new HashSet<>(Arrays.asList(
                "undecided", "undecided"));
    }


    @EventHandler
    public void bowPower(EntityShootBowEvent event) {

        if (!(event.getEntity() instanceof Player)
                || Florial.getPlayerData().get(event.getEntity().getUniqueId()).getSpecies() != this) return;

        ItemStack bow = event.getBow();

        assert bow != null;
        bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);

    }

    @EventHandler
    public void weakWithMelee(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player player)
                || Florial.getPlayerData().get(event.getEntity().getUniqueId()).getSpecies() != this
                || ((Player) event.getDamager()).getInventory().getItemInMainHand().getType().toString().contains("SWORD")
                || ((Player) event.getDamager()).getInventory().getItemInMainHand().getType().toString().contains("AXE")) return;


        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20, 1));


    }
}
