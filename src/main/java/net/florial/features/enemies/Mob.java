package net.florial.features.enemies;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import net.florial.Florial;
import net.florial.features.enemies.events.MobDeathEvent;
import net.florial.features.enemies.events.MobSpawnEvent;
import net.florial.utils.game.MobSpawn;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class Mob implements Listener {

    EntityType entity;

    EntityType iReplace;

    int health;

    int dmg;

    int toughness;

    List<ItemStack> drops;


    protected Mob(EntityType entity, EntityType iReplace, int health, int dmg, int toughness, List<ItemStack> drops) {
        this.entity = entity;
        this.health = health;
        this.dmg = dmg;
        this.toughness = toughness;
        this.iReplace = iReplace;
        this.drops = drops;

        Bukkit.getPluginManager().registerEvents(this, Florial.getInstance());

    }

    @EventHandler
    public void spawnMyself(MobSpawnEvent event) {

        if (event.getFormer().getType() != this.iReplace) return;

        Collection<Entity> amINear = event.getW().getNearbyEntitiesByType(this.entity.getEntityClass(), event.getLoc(), 20);

        if (amINear.size() > 0) return;

        LivingEntity me = (LivingEntity) MobSpawn.spawnMob(this.entity, event.getW(), event.getLoc());
        Objects.requireNonNull(me.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(this.dmg);
        Objects.requireNonNull(me.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(this.toughness);
        Objects.requireNonNull(me.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(this.health);

        me.setRemoveWhenFarAway(true);

        if (this.entity == EntityType.HOGLIN) ((Hoglin) me).setImmuneToZombification(true);


    }

    @EventHandler
    public void whenIDie(MobDeathEvent event) {

        if (event.getEntity() != this.entity) return;

        for (ItemStack i : this.drops) {event.getDrops().add(i);}
    }



}
