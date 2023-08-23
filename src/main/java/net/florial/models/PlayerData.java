package net.florial.models;

import com.mongodb.lang.Nullable;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import io.github.bananapuncher714.nbteditor.NBTEditor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.florial.Florial;
import net.florial.Refresh;
import net.florial.database.FlorialDatabase;
import net.florial.features.age.Age;
import net.florial.features.dailyrewards.Weekday;
import net.florial.features.playtimerewards.Tier;
import net.florial.features.skills.Skill;
import net.florial.features.upgrades.Upgrade;
import net.florial.species.SpecieType;
import net.florial.species.Species;
import net.florial.utils.GeneralUtils;
import net.florial.utils.general.CustomItem;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity("playerdata")
public class PlayerData {

    @Id
    private ObjectId _id = new ObjectId();

    @Getter
    private String UUID;
    @Getter
    private String discordId = "";
    @Getter @Setter
    private int flories = 0;
    private int dna = 25;
    private int specieId = 0;

    private int event = 0;
    private int growth = 0;
    private int reincarnations = 0;
    @Nullable
    String pronouns = "";
    @Nullable
    String prefix = "";
    Age age = Age.KIT;
    HashMap<Skill, Integer> skills = new HashMap<>(Map.of(Skill.SCENT,1, Skill.RESISTANCE,1, Skill.STRENGTH,1, Skill.SURVIVAL,1, Skill.SPECIFIC,1));
    HashMap<Weekday, Boolean> loggedInDays = new HashMap<>(Map.of(Weekday.SUNDAY, false, Weekday.MONDAY, false, Weekday.TUESDAY, false, Weekday.WEDNESDAY, false, Weekday.THURSDAY, false, Weekday.FRIDAY, false, Weekday.SATURDAY, false));

    LocalDate lastLoggedIn = LocalDate.now();
    HashMap<Upgrade, Boolean> upgrades = new HashMap<>();
    @Getter @Setter String gradient1 = "";

    @Getter @Setter String gradient2 = "";

    HashMap<Tier, Boolean> playtimeTiers = new HashMap<>();



    public PlayerData(String uuid, String discordId, int flories, int dna, int specieId, @org.jetbrains.annotations.Nullable String pronouns, HashMap<Skill,Integer> skills, HashMap<Upgrade,Boolean> upgrades, int event, int growth, int reincarnations, @org.jetbrains.annotations.Nullable String prefix, Age age, String gradient1, String gradient2, HashMap<Tier,Boolean> playtimeTiers) {

        this.UUID = uuid;
        this.discordId = discordId;
        this.flories = flories;
        this.dna = dna;
        this.specieId = specieId;
        this.pronouns = pronouns;
        this.skills = skills;
        this.upgrades = upgrades;
        this.event = event;
        this.growth = growth;
        this.reincarnations = reincarnations;
        this.prefix = prefix;
        this.age = age;
        this.gradient1 = gradient1;
        this.gradient2 = gradient2;
        this.playtimeTiers = playtimeTiers;
    }

    public PlayerData(String uuid) {
        this.UUID = uuid;
    }
    public PlayerData() {}
    public SpecieType getSpecieType() {
        return SpecieType.fromID(specieId);
    }

    public Species getSpecies() {
        return getSpecieType().getSpecie();
    }

    @BsonIgnore
    public Player getPlayer() {
        return Bukkit.getPlayer(java.util.UUID.fromString(this.UUID));
    }

    @BsonIgnore
    public void save(boolean async) {
        if (async) FlorialDatabase.createNewPlayerDataAsync(this);
        else FlorialDatabase.createNewPlayerData(this);
    }

    @BsonIgnore
    public void refresh() {

        Player p = getPlayer();
        java.util.UUID u = p.getUniqueId();
        if (getSpecieType().getSpecie() == null) return;

        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> {


            for (PotionEffect effect : getSpecies().effects()) {

                if (Florial.optionsEffects().get(u) != null) break;

                if (Florial.getOptionsNV().get(u) != null && effect.getType() == PotionEffectType.NIGHT_VISION) continue;

                getPlayer().addPotionEffect(effect);
            }
        }, 70L);


        if (getSpecies().isCanSmell()) {
            GeneralUtils.runAsync(new BukkitRunnable() {
                @Override
                public void run() {
                    PlayerInventory inv = p.getInventory();
                    if (inv.getStorageContents()[8] != null && inv.getStorageContents()[8].getType() != Material.PAPER) {
                        p.getWorld().dropItem(p.getLocation(), inv.getStorageContents()[8]);
                    }
                    inv.setItem(8, NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.PAPER), "#6A3A2F&lSCENT [CLICK]", "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑\n #ffa2c4&l︳#ffa2c4 Right-Click to smell Entity\n #ffa2c4&l︳#ffa2c4 Left-Click to Track Food\n  #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false), 1, "CustomModelData"));
                }
            });


        }

        Refresh.load(getPlayer(), this);

    }


    public void overwrite() {
        if (Bukkit.getPlayer(UUID) == null) return;
        Florial.getPlayerData().put(Bukkit.getPlayer(UUID).getUniqueId(), this);
    }


}
