package net.florial.species.impl.usermade;

import net.florial.Florial;
import net.florial.species.Species;
import net.florial.utils.Cooldown;
import net.florial.utils.game.RegionDetector;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class Dryad extends Species implements Listener {
    public Dryad(int id) {
        super("Dryad", id, 20, false, null);
    }


    @Override
    public Set<Integer> sharedAbilities() {

        return new HashSet<>(List.of(
                4, 5));
    }

    @Override
    public Set<Material> diet() {
        return new HashSet<>(Arrays.asList(
                Material.APPLE, Material.BAKED_POTATO,
                Material.BEETROOT, Material.BEETROOT_SOUP,
                Material.BREAD, Material.CARROT,
                Material.CHORUS_FRUIT, Material.COOKIE,
                Material.DRIED_KELP, Material.ENCHANTED_GOLDEN_APPLE,
                Material.GOLDEN_APPLE, Material.GOLDEN_CARROT,
                Material.HONEY_BOTTLE, Material.MELON,
                Material.MUSHROOM_STEW, Material.POTATO,
                Material.PUMPKIN_PIE, Material.SUSPICIOUS_STEW,
                Material.SWEET_BERRIES, Material.TROPICAL_FISH));
    }

    @Override
    public Set<String> descriptions() {

        return new HashSet<>(Arrays.asList(
                "NONE", "none"));
    }

    @EventHandler
    public void spawnRandomSapling(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
                || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this
                || Cooldown.isOnCooldown("c1", event.getPlayer())
                || event.getClickedBlock().getType() != Material.GRASS_BLOCK
                || (!(RegionDetector.detect(event.getPlayer().getLocation()).contains("none")))) return;

        Block block = event.getClickedBlock();

        List<TreeType> saplings = Arrays.asList(TreeType.ACACIA, TreeType.BIRCH, TreeType.DARK_OAK, TreeType.JUNGLE, TreeType.TREE, TreeType.REDWOOD);

        assert block != null;
        block.getWorld().generateTree(block.getLocation(), saplings.get(new Random().nextInt(saplings.size())));

        Cooldown.createCooldown("c1", event.getPlayer(), 10);

    }

    @EventHandler
    public void healingAura(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
                || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this
                || (!(event.getPlayer().isSneaking())
                || Cooldown.isOnCooldown("c2", event.getPlayer())
                || Florial.getOngoingDuel().get(event.getPlayer().getUniqueId()) != null)) return;

        Player player = event.getPlayer();

        ItemStack potion = new ItemStack(Material.LINGERING_POTION);
        PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 10, 1, true, true), true);
        potion.setItemMeta(potionMeta);
        ThrownPotion thrownPotion = player.launchProjectile(ThrownPotion.class);
        thrownPotion.setItem(potion);

        Cooldown.createCooldown("c2", player, 20);
    }

    @EventHandler
    public void growPlant(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
                || (!(event.getClickedBlock() instanceof Ageable)
                || Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getSpecies() != this)) return;


        Player player = event.getPlayer();

        for (BlockFace face : BlockFace.values()) {
            if (face == BlockFace.DOWN || face == BlockFace.UP) continue;
            Block adjacentBlock = event.getClickedBlock().getRelative(face);
            adjacentBlock.applyBoneMeal(face);
        }

        player.setFoodLevel(player.getFoodLevel() - 1);
        player.playSound(player.getLocation(), Sound.ITEM_BONE_MEAL_USE, 1, 2);

    }

    @EventHandler
    public void beeImmunity(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Bee)
                || (!(event.getEntity() instanceof Player)
                || Florial.getPlayerData().get(event.getEntity().getUniqueId()).getSpecies() != this)) return;

        event.setCancelled(true);
    }

}
