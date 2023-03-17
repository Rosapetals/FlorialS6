package net.florial.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.florial.features.thirst.ThirstManager;
import net.florial.Florial;
import net.florial.database.FlorialDatabase;
import net.florial.models.PlayerData;
import net.florial.scoreboard.Scoreboard;
import net.florial.utils.general.CC;
import net.florial.utils.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;

import java.util.Objects;
import java.util.UUID;

public class PlayerListeners implements Listener {

    private static final ThirstManager ThirstManager = new ThirstManager();

    private static final Scoreboard Scoreboard = new Scoreboard();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        UUID u = p.getUniqueId();

        FlorialDatabase.getPlayerData(p).thenAccept(playerData -> {
            Florial.getPlayerData().put(u, playerData);
            new Message("&a[MONGO] &fLoaded your player data successfully!").showOnHover(playerData.toString()).send(p);
        });


        ThirstManager.thirstRunnable(p);
        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> {
            if (Florial.getBoards().get(u) == null) Scoreboard.createBoard(p);
            Scoreboard.boardRunnable(u, p);
            Florial.getPlayerData().get(u).refresh();
            }, 100L);


    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Florial.getBoards().remove(event.getPlayer().getUniqueId());

        PlayerData data = Florial.getPlayerData().get(event.getPlayer().getUniqueId());
        data.save(true);
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        String prefix = Florial.getPlayerData().get(event.getPlayer().getUniqueId()).getPrefix();
        if (Objects.equals(prefix, "")) {
            try {
                prefix = Objects.requireNonNull(Florial.getInstance().getLpapi().getUserManager().getUser(event.getPlayer().getUniqueId())).getCachedData().getMetaData().getPrefix();
            } catch (NullPointerException e) {
                prefix = "";
            }
        }

        if (prefix == null) {
            prefix = "Default";
        }
        event.setCancelled(true);
        Bukkit.broadcast(Component.text(CC.translate("&7" + prefix + " " + event.getPlayer().getName().trim() + ": " + ((TextComponent) event.message()).content())));
    }
}
