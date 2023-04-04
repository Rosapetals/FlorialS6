package net.florial.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import lombok.val;
import net.florial.features.thirst.ThirstManager;
import net.florial.Florial;
import net.florial.database.FlorialDatabase;
import net.florial.menus.species.SpeciesMenu;
import net.florial.models.PlayerData;
import net.florial.scoreboard.Scoreboard;
import net.florial.species.disguises.Morph;
import net.florial.species.events.impl.SpeciesTablistEvent;
import net.florial.utils.game.ChangeTablistSkin;
import net.florial.utils.general.CC;
import net.florial.utils.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;
import java.util.UUID;

import static dev.morphia.query.filters.Filters.eq;

public class PlayerListeners implements Listener {

    private static final ThirstManager ThirstManager = new ThirstManager();

    private static final SpeciesMenu speciesMenu = new SpeciesMenu();

    private static final Morph morph = new Morph();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        UUID u = p.getUniqueId();

        FlorialDatabase.getPlayerData(p).thenAccept(playerData -> {
            Florial.getPlayerData().put(u, playerData);
            new Message("&a[MONGO] &fLoaded your player data successfully!").showOnHover(playerData.toString()).send(p);
        });

        if (Florial.getPlayerData().get(u) == null) {
            val temp = FlorialDatabase.getDatastore().find(PlayerData.class).filter(eq("UUID", u.toString()));
            Florial.getPlayerData().put(u, temp.stream().findFirst().orElse(new PlayerData(u.toString())));
            new Message("&a[MONGO] &fLoaded your player data successfully!").showOnHover(Florial.getPlayerData().get(u).toString()).send(p);
        }
       // if (p.hasPermission("florial.staff")) {

          //  if (Objects.equals(Florial.getPlayerData().get(u).getDiscordId(), "")) {
            //    new Message("&c&lPlease run /setDiscordId <Your ID> and then relog").send(p);
           // }
           // Florial.getInstance().getStaffToVerify().add(u);
       // }

        PlayerData data = Florial.getPlayerData().get(u);

        ThirstManager.thirstRunnable(p);

        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), data::refresh, 100L);

        if (Florial.getQuestBar().containsKey(u)) Florial.getQuestBar().get(u).addPlayer(p);

        if (data.getSpecieType().getSpecie() == null) {
            speciesMenu.speciesMenu(p);
            return;
        }
        morph.activate(p, 4, false, true, data.getSpecies());

        SpeciesTablistEvent e = new SpeciesTablistEvent(
                p
        );
        Bukkit.getPluginManager().callEvent(e);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Florial.getInstance().getStaffToVerify().remove(event.getPlayer().getUniqueId());

        Florial.getBoards().remove(event.getPlayer().getUniqueId());

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
        if (Florial.getInstance().getStaffToVerify().contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            new Message("&c&lPlease verify through discord").send(event.getPlayer());
        }

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

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (Florial.getInstance().getStaffToVerify().contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            new Message("&c&lPlease verify through discord").send(event.getPlayer());
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (Florial.getInstance().getStaffToVerify().contains(event.getPlayer().getUniqueId())) {
            event.setCancelled(true);
            new Message("&c&lPlease verify through discord").send(event.getPlayer());
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (Florial.getInstance().getStaffToVerify().contains(((Player) event.getDamager()).getPlayer().getUniqueId())) {
                event.setCancelled(true);
                new Message("&c&lPlease verify through discord").send(((Player) event.getDamager()).getPlayer());
            }
        } else if (event.getEntity() instanceof Player) {
            if (Florial.getInstance().getStaffToVerify().contains(((Player) event.getEntity()).getPlayer().getUniqueId())) {
                event.setCancelled(true);
                new Message("&c&lPlease verify through discord").send(((Player) event.getEntity()).getPlayer());
            }
        }
    }
}
