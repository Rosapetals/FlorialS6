package net.florial.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.florial.features.thirst.ThirstManager;
import net.florial.Florial;
import net.florial.database.FlorialDatabase;
import net.florial.menus.SpeciesMenu;
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
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;
import java.util.UUID;

public class PlayerListeners implements Listener {

    private static final ThirstManager ThirstManager = new ThirstManager();

    private static final Scoreboard Scoreboard = new Scoreboard();

    private static final SpeciesMenu speciesMenu = new SpeciesMenu();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        UUID u = p.getUniqueId();

        FlorialDatabase.getPlayerData(p).thenAccept(playerData -> {
            Florial.getPlayerData().put(u, playerData);
            Florial.getPlayerData().get(u).refresh();
            new Message("&a[MONGO] &fLoaded your player data successfully!").showOnHover(playerData.toString()).send(p);
        });

        PlayerData data = Florial.getPlayerData().get(u);
        ThirstManager.thirstRunnable(p);
        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> {
            if (Florial.getBoards().get(u) == null) Scoreboard.createBoard(p);
            Scoreboard.boardRunnable(u, p);
            data.refresh();
            }, 100L);

        if (Florial.getQuestBar().containsKey(u)) Florial.getQuestBar().get(u).addPlayer(p);

        if (data.getSpecieType().getSpecie() == null) speciesMenu.speciesMenu(p);

        if (p.hasPermission("florial.staff")) {

            if (Objects.equals(Florial.getPlayerData().get(u).getDiscordId(), "")) {
                new Message("&c&lPlease run /setDiscordId <Your ID> and then relog").send(p);
            }
            Florial.getInstance().getStaffToVerify().add(u);
        }



    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        PlayerData data = Florial.getPlayerData().get(event.getPlayer().getUniqueId());
        data.save(true);
    }

    @EventHandler
    public void closeInventory(InventoryCloseEvent e) {

        if (e.getInventory().getType() != InventoryType.CHEST || (!(e.getReason().equals(InventoryCloseEvent.Reason.PLAYER)))) return;

        if (Florial.getPlayerData().get(e.getPlayer().getUniqueId()).getSpecieType().getSpecie() != null) return;

        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> speciesMenu.speciesMenu((Player) e.getPlayer()), 20L);
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
