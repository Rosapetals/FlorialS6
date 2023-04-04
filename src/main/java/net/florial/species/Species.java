package net.florial.species;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import net.florial.Florial;
import net.florial.features.age.Age;
import net.florial.models.PlayerData;
import net.florial.species.disguises.Morph;
import net.florial.species.events.impl.SpeciesSwitchEvent;
import net.florial.utils.GeneralUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class Species implements Listener {
    private static final Florial florial = Florial.getInstance();
    private static final Map<Material, Integer> fillingValues = Map.ofEntries(
            Map.entry(Material.CHICKEN, 20),
            Map.entry(Material.PORKCHOP, 15),
            Map.entry(Material.BEEF, 20),
            Map.entry(Material.SWEET_BERRIES, 10),
            Map.entry(Material.COD, 13),
            Map.entry(Material.SALMON, 13),
            Map.entry(Material.MUTTON, 20)
    );


    String name;
    int id;
    double maxHealth;
    boolean canSmell;
    DisguiseType morph;

    protected Species(String name, int id, double maxHealth, boolean canSmell, DisguiseType morph) {
        this.name = name;
        this.id = id;
        this.maxHealth = maxHealth;
        this.canSmell = canSmell;
        this.morph = morph;

        Bukkit.getPluginManager().registerEvents(this, Florial.getInstance());

    }

    /*
    1 = no fall dmg
    2 = flight
     */
    public Set<Integer> sharedAbilities() {
        return new HashSet<>();
    }

    public Map<Integer, PotionEffect> specific() {
        return new HashMap<>();
    }


    public Set<PotionEffect> effects() {
        return new HashSet<>();
    }

    public Set<Material> diet() {
        return new HashSet<>();
    }

    public Set<String> descriptions() {
        return new HashSet<>();
    }


    public static void become(Player p, String type) {

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        p.closeInventory();

        if (data.getSpecieId() == 0) {

            SpeciesWrapper.setSpecies(p.getUniqueId(), SpecieType.valueOf(type.toUpperCase().replace(" ", "_")));

            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);

        } else {
            p.sendMessage("You already have a species! Remove it through /resetspecies for 25 DNA.");
        }
    }


    @EventHandler
    public void whenIEat(PlayerItemConsumeEvent event) {

        Player p = event.getPlayer();

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        if (data.getSpecies() != this || data.getSpecies().diet() == null) return;

        Material mat = event.getItem().getType();

        event.setCancelled(true);

        if (this.diet().contains(mat)) {
            p.setFoodLevel(p.getFoodLevel() + fillingValues.get(mat));
            if (!(p.getSaturation() >= 20)) p.setSaturation(p.getSaturation() + (float) fillingValues.get(mat)/2);
        } else {
            p.setFoodLevel(p.getFoodLevel() + 1);
        }

    }

    @EventHandler
    public void noFallDamage(EntityDamageEvent e) {

        if (e.getCause() != EntityDamageEvent.DamageCause.FALL || (!(e.getEntity() instanceof Player p))) return;

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        if (data.getSpecies() != this || data.getSpecies().sharedAbilities() == null) return;

        if ((!(this.sharedAbilities().contains(1)))) return;

        e.setCancelled(true);

    }


}
