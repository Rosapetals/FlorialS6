package net.florial.models;

import dev.morphia.ReplaceOptions;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.florial.database.FlorialDatabase;
import net.florial.utils.GeneralUtils;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;

@Data
@Entity("discorduser")
@NoArgsConstructor
public class DiscordUser {

    @Id
    private ObjectId id = new ObjectId();

    private String uuid;
    private int exp;
    private int level;
    private int coins;
    private String mcUUID;

    public DiscordUser(String uuid, int exp, int level, int coins, String mcUUID) {
        this.uuid = uuid;
        this.exp = exp;
        this.level = level;
        this.coins = coins;
        this.mcUUID = mcUUID;
    }

    public DiscordUser(String id) {
        this.uuid = id;
        this.exp = 0;
        this.level = 1;
        this.coins = 0;
        this.mcUUID = "";
    }
    @BsonIgnore
    public void save(boolean async) {
        if (async) {
            GeneralUtils.runAsync(new BukkitRunnable() {
                @Override
                public void run() {
                    FlorialDatabase.getDatastore().replace(getInstance(), new ReplaceOptions().upsert(true));
                }
            });
        }
        else FlorialDatabase.getDatastore().replace(getInstance(), new ReplaceOptions().upsert(true));
    }

    @BsonIgnore
    public void createNew() {
        DiscordUser user = this;
        GeneralUtils.runAsync(new BukkitRunnable() {
            @Override
            public void run() {
                FlorialDatabase.getDatastore().save(user);
            }
        });
    }

    public static int getFieldValue(DiscordUser discordData, String fieldName) {
        try {
            Field field = discordData.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (int) field.get(discordData);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public DiscordUser getInstance() {
        return this;
    }
}