package net.florial.listeners.mobs;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.Florial;
import net.florial.species.Species;
import net.florial.utils.GeneralUtils;
import net.florial.utils.general.CustomItem;
import net.florial.utils.math.GetChance;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class AnimalListener implements Listener {

    private static final Set<EntityType> ALLOWED_ENTITY_TYPES = new HashSet<>(Arrays.asList(
            EntityType.SHEEP, EntityType.COW, EntityType.CHICKEN, EntityType.PIG, EntityType.RABBIT, EntityType.HOGLIN
    ));

    private static final Set<Material> ALLOWED_MATERIALS = new HashSet<>(Arrays.asList(
            Material.BEEF, Material.CHICKEN, Material.PORKCHOP, Material.MUTTON, Material.RABBIT
    ));

    @EventHandler
    public void passiveSpawn(CreatureSpawnEvent e) {

        if (e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL) return;

        EntityType ent = e.getEntityType();
        if (!List.of(EntityType.SHEEP, EntityType.COW, EntityType.CHICKEN, EntityType.PIG, EntityType.RABBIT).contains(ent)) {
            return;

        }

        if (!(GetChance.chanceOf(15))) {
            e.setCancelled(true);
        } else {
            addPotionEffects(e.getEntity());
        }
    }

    private static void addPotionEffects(LivingEntity e) {
        PotionEffect resist = new PotionEffect(PotionEffectType.SPEED, 1000000, 2, false, false, true);
        PotionEffect speed = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1000000, 3, false, false, true);

        for (PotionEffect effect : List.of(resist, speed)) {e.addPotionEffect(effect);}
    }

    @EventHandler
    public void dropDead(EntityDeathEvent e) {

        EntityType ent = e.getEntityType();

        if (!ALLOWED_ENTITY_TYPES.contains(ent)) return;

        LivingEntity en = e.getEntity();

        Predicate<ItemStack> edible = x -> x.getType().isEdible();
        ItemStack firstEdible = e.getDrops().stream().filter(edible).findFirst().orElse(new ItemStack(Material.RABBIT));
        e.getDrops().removeIf(edible);

        if (ent == EntityType.HOGLIN) firstEdible = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PORKCHOP), "&fRaw Boar", "", false), 1, "CustomModelData");

        ItemFrame itemFrame = en.getWorld().spawn(en.getLocation(), ItemFrame.class);
        itemFrame.setVisible(false);
        itemFrame.setFacingDirection(BlockFace.UP);
        itemFrame.setItem(firstEdible);

        GeneralUtils.runAsync(new BukkitRunnable() {@Override public void run() {Bukkit.getScheduler().runTaskLater(Florial.getInstance(), itemFrame::remove, 1200L);
        }});

    }


    @EventHandler
    public void pickUpMeat(EntityDamageByEntityEvent e) {

        if (!(e.getEntity() instanceof ItemFrame) || (!(e.getDamager() instanceof Player p))) return;

        if (!ALLOWED_MATERIALS.contains(((ItemFrame) e.getEntity()).getItem().getType())) return;

        p.getInventory().addItem(((ItemFrame) e.getEntity()).getItem());
        p.playSound(p.getLocation(), Sound.BLOCK_SLIME_BLOCK_PLACE, 1, (float) 1.3);
        e.getEntity().remove();
    }

    @EventHandler
    public void animalBones(PlayerItemConsumeEvent event) {

        Player p = event.getPlayer();

        if (!(Species.boneFoods.contains(event.getItem().getType()))) return;

        p.getInventory().addItem(new ItemStack(Material.BONE, 1));

        event.setCancelled(true);

        ItemStack item = event.getItem();

        if (item.getAmount() > 1) {
            item.setAmount(item.getAmount() - 1);
        } else {
            p.getInventory().removeItem(item);
        }
    }



}
