package net.florial.species.impl;

import net.florial.Florial;
import net.florial.features.skills.Skill;
import net.florial.species.Species;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Human extends Species implements Listener {
    
    public Human(int id) {
        super("Human", id, 20, false, null);
    }

    @Override
    public Set<String> descriptions() {

        return new HashSet<>(Arrays.asList(
                "Medical", "left-click w paper to heal yourself better and better"));
    }


    @EventHandler
    public void weakStomach(PlayerItemConsumeEvent event) {

        if (Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this) return;


        List<Material> badMeats = Arrays.asList(Material.COD, Material.SALMON, Material.TROPICAL_FISH, Material.PUFFERFISH,
                Material.MUTTON, Material.BEEF, Material.CHICKEN, Material.PORKCHOP);

        if (badMeats.contains(event.getItem().getType())) event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 2400, 0, false, false, true));

    }

    @EventHandler
    public void medicalAid(PlayerInteractEvent event) {

        if (event.getAction() != Action.LEFT_CLICK_AIR
        || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this
        || event.getPlayer().getInventory().getItemInMainHand().getType() != Material.PAPER
        || Florial.getOngoingDuel().get(event.getPlayer().getUniqueId()) != null) return;

        Player p = event.getPlayer();

        int specific = Florial.getPlayerData().get(p.getUniqueId()).getSkills().get(Skill.SPECIFIC);

        p.getInventory().removeItem(new ItemStack(Material.PAPER));

        p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, (float) 0.5);

        double newHealth = (p.getHealth() + specific+1) > 20 ? 20 : p.getHealth() + 2;

        p.setHealth(newHealth);
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, specific*200, (specific+1)-2, false, false, true));



    }






}
