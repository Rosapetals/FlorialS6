package net.florial.species.impl.usermade;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.florial.Florial;
import net.florial.features.skills.Skill;
import net.florial.species.Species;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Thallidian extends Species implements Listener {
    public Thallidian(int id) {
        super("Thallidian", id, 20, false, DisguiseType.SLIME);
    }


    @Override
    public Set<PotionEffect> effects() {

        return new HashSet<>(List.of(
                new PotionEffect(PotionEffectType.WATER_BREATHING, 1000000, 0, false, false, true),
                new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 1, false, false, true),
                new PotionEffect(PotionEffectType.SPEED, 1000000, 2, false, false, true),
                new PotionEffect(PotionEffectType.JUMP, 1000000, 1, false, false, true),
                new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 2, false, false, true)));
    }

    @Override
    public Set<String> descriptions() {

        return new HashSet<>(Arrays.asList(
                "GreenThumb", "grow plants faster and faster by clicking them"));
    }

    @EventHandler
    public void poisonTouch(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)
                || (!(event.getEntity() instanceof LivingEntity target)
                || Florial.getPlayerData().get(event.getDamager().getUniqueId()).getSpecies() != this)) return;

        target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 4800, 1, false, false, true));

    }

    @EventHandler
    public void extraKnockBack(EntityDamageByEntityEvent event) {

        if (!(event.getEntity() instanceof Player p)
                || (!(event.getDamager() instanceof LivingEntity entity)
                || Florial.getPlayerData().get(event.getEntity().getUniqueId()).getSpecies() != this)) return;


        Vector knockBack = p.getLocation().toVector().subtract(entity.getLocation().toVector()).normalize().multiply(12);
        entity.setVelocity(knockBack);
    }


    @EventHandler
    public void growPlant(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
                || (!(event.getClickedBlock() instanceof Ageable)
                || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this)) return;

        Player player = event.getPlayer();

        int specific = Florial.getPlayerData().get(player.getUniqueId()).getSkills().get(Skill.SPECIFIC);


        for (BlockFace face : BlockFace.values()) {
            if (face == BlockFace.DOWN || face == BlockFace.UP) continue;
            Block adjacentBlock = event.getClickedBlock().getRelative(face);
            for (int i = 0; i < specific; i++) {adjacentBlock.applyBoneMeal(face);}
        }

        player.playSound(player.getLocation(), Sound.ITEM_BONE_MEAL_USE, 1, 2);

    }

    @EventHandler
    public void replantCrop(BlockBreakEvent event) {

        if (!(event.getBlock() instanceof Ageable)
                || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this) return;

        event.setCancelled(true);

        Player p = event.getPlayer();

        p.getWorld().dropItem(p.getLocation(), new ItemStack(event.getBlock().getType()));

    }

}
