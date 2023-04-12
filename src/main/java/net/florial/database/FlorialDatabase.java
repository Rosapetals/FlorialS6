package net.florial.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.filters.Filters;
import lombok.Getter;
import lombok.val;
import net.florial.Florial;
import net.florial.models.DiscordUser;
import net.florial.models.FilterEntry;
import net.florial.models.PlayerData;
import net.florial.utils.GeneralUtils;
import org.bson.UuidRepresentation;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.exists;
import static dev.morphia.query.filters.Filters.eq;
import static net.florial.models.PlayerData.getFieldValue;

public class FlorialDatabase {

    @Getter
    private static Datastore datastore;

    @Getter
    private static MongoClient mongo;

    @Getter
    private static MongoDatabase database;

    public static void initializeDatabase() {
        if (Florial.getInstance().getConfig().getString("mongo.uri") == null || Florial.getInstance().getConfig().getString("mongo.database") == null) {
            Bukkit.getLogger().severe("Could not connect to the database, you forgot the enter stuff in the config you twat.");
            return;
        }

        // mongo = MongoClients.create(Florial.getInstance().getConfig().getString("mongo.uri"));
        val settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(Florial.getInstance().getConfig().getString("mongo.uri")))
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
                .build();

        mongo = MongoClients.create(settings);
        database = mongo.getDatabase(Florial.getInstance().getConfig().getString("mongo.database"));
        datastore = Morphia.createDatastore(mongo, Florial.getInstance().getConfig().getString("mongo.database"));
        datastore.getMapper().map(PlayerData.class);
        datastore.getMapper().map(FilterEntry.class);
        datastore.getMapper().map(DiscordUser.class);
        datastore.ensureIndexes();
    }

    /**
     * Returns the first found version of PlayerData from the database for the given UUID
     *
     * @param uuid The UUID of the player you want to search
     * @return the first found entry as a PlayerData object
     *
     */
    public static CompletableFuture<PlayerData> getPlayerData(UUID uuid) {
        CompletableFuture<PlayerData> future = new CompletableFuture<>();
        GeneralUtils.runAsync(new BukkitRunnable() {

            @Override
            public void run() {
                val temp = datastore.find(PlayerData.class).filter(eq("UUID", uuid.toString()));
                future.complete(temp.stream().findFirst().orElse(new PlayerData(uuid.toString())));
            }
        });
        return future;
    }

    public static CompletableFuture<DiscordUser> getDiscordUserData(String id) {
        CompletableFuture<DiscordUser> future = new CompletableFuture<>();
        GeneralUtils.runAsync(new BukkitRunnable() {

            @Override
            public void run() {
                val temp = datastore.find(DiscordUser.class).filter(eq("uuid", id));
                future.complete(temp.stream().findFirst().orElse(null));
            }
        });
        return future;
    }
    /**
     * Returns the first found version of PlayerData from the database for the given UUID
     * @param player the player you want to get the PlayerData of
     * @return a completable future of the first found PlayerData
     */
    public static CompletableFuture<PlayerData> getPlayerData(Player player) {
        return getPlayerData(player.getUniqueId());
    }

    public static CompletableFuture<PlayerData> getCachedOrDBPlayerData(UUID uuid) {
        CompletableFuture<PlayerData> future = new CompletableFuture<>();
        GeneralUtils.runAsync(new BukkitRunnable() {
            @Override
            public void run() {
                if (Florial.getPlayerData().get(uuid) != null) {
                    future.complete(Florial.getPlayerData().get(uuid));
                }
                val temp = datastore.find(PlayerData.class).filter(eq("UUID", uuid.toString()));
                future.complete(temp.stream().findFirst().orElse(new PlayerData(uuid.toString())));
            }
        });
        return future;
    }

    public static CompletableFuture<PlayerData> getCachedOrDBPlayerData(Player player) {
        return getCachedOrDBPlayerData(player.getUniqueId());
    }

    /**
     * Creates or overwrites a new copy of PlayerData
     * @param data
     */

    public static void createNewPlayerDataAsync(PlayerData data) {
        GeneralUtils.runAsync(new BukkitRunnable() {
            @Override
            public void run() {
                datastore.find(PlayerData.class).filter(Filters.eq("UUID", data)).delete();
                datastore.save(data);
            }
        });
    }

    public static void restartEvent(){

        GeneralUtils.runAsync(new BukkitRunnable() {
            @Override
            public void run() {

                datastore.find(PlayerData.class)
                        .filter(Filters.exists("event"))
                        .iterator()
                        .forEachRemaining(document -> {
                            document.setEvent(0);
                            datastore.save(document);
                        });
            }

        });
    }

    public static void removeField(String field) {
        GeneralUtils.runAsync(new BukkitRunnable() {
            @Override
            public void run() {

                Bson filter = exists(field);

                datastore.getDatabase().getCollection("playerdata", PlayerData.class)
                        .find()
                        .forEach(document -> {
                            datastore.getDatabase().getCollection("playerdata")
                                    .updateOne(filter, Updates.unset(field));
                            datastore.save(document);
                        });
            }
        });
    }

    public static CompletableFuture<List<String>> sortDataByField(String field, boolean descending, int limit) {

        CompletableFuture<List<String>> future = new CompletableFuture<>();

        GeneralUtils.runAsync(new BukkitRunnable() {
            @Override
            public void run() {
                List<PlayerData> playerList = new ArrayList<>();
                datastore.find(PlayerData.class)
                        .filter(Filters.exists(field))
                        .iterator()
                        .forEachRemaining(playerList::add);

                playerList.sort((PlayerData p1, PlayerData p2) -> {
                    if (descending) return getFieldValue(p2, field) - getFieldValue(p1, field);
                    else return getFieldValue(p1, field) - getFieldValue(p2, field);
                });

                List<String> sortedList = playerList.subList(0, limit)
                        .stream()
                        .map(playerData -> {
                            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(UUID.fromString(playerData.getUUID()));
                            return offlinePlayer.getName() + ": " + getFieldValue(playerData, field);
                        })
                        .collect(Collectors.toList());

                future.complete(sortedList);
            }

        });
        return future;

    }

    public static CompletableFuture<List<String>> sortDataByField(String field, boolean descending) {
        return sortDataByField(field, descending, 10);
    }
    public static void fetchBoard(String field, boolean descending, Consumer<List<String>> callback){
        sortDataByField(field, descending, 10).thenAccept(callback);
    }

    public static void createNewPlayerData(PlayerData data) {
        datastore.find(PlayerData.class).filter(Filters.eq("UUID", data)).delete();
        datastore.save(data);
    }

    public static void closeConnection() {
        mongo.close();
    }
}

