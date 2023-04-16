package net.florial.models;

import dev.morphia.ReplaceOptions;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.florial.database.FlorialDatabase;
import net.florial.utils.GeneralUtils;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

@Data
@Entity("cheques")
@NoArgsConstructor
public class ChequeData {

    @Id
    private ObjectId id = new ObjectId();

    @Getter
    @Setter
    private String addressee;
    @Getter
    @Setter
    private int flories;
    @Getter
    @Setter
    private int cash;
    @Getter
    @Setter
    private int coins;
    @Getter
    @Setter
    private String discordId;


    public ChequeData(String uuid, boolean minecraft) {

        if (minecraft) {
            this.addressee = uuid;
            if (Bukkit.getPlayer(uuid) == null) {
                this.discordId = "";
            } else {
                this.discordId = FlorialDatabase.getPlayerData(Bukkit.getPlayer(uuid)).join().getDiscordId();
            }
        }
        else {
            this.discordId = uuid;
            this.addressee = FlorialDatabase.getDiscordUserData(uuid).join().getMcUUID();
        }
        this.flories = 0;
        this.cash = 0;
        this.coins = 0;
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
        ChequeData user = this;
        GeneralUtils.runAsync(new BukkitRunnable() {
            @Override
            public void run() {
                FlorialDatabase.getDatastore().save(user);
            }
        });
    }

    public ChequeData getInstance() {
        return this;
    }
}