package net.florial.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import lombok.val;
import me.clip.placeholderapi.PlaceholderAPI;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import net.florial.Florial;
import net.florial.database.FlorialDatabase;
import net.florial.features.dailyrewards.Reward;
import net.florial.features.playershops.PlayerShopsMenu;
import net.florial.features.thirst.HydrateEvent;
import net.florial.features.thirst.ThirstManager;
import net.florial.menus.species.SpeciesMenu;
import net.florial.models.PlayerData;
import net.florial.species.disguises.Morph;
import net.florial.species.events.impl.SpeciesTablistEvent;
import net.florial.utils.Cooldown;
import net.florial.utils.Message;
import net.florial.utils.game.MorphAdjuster;
import net.florial.utils.game.RegionDetector;
import net.florial.utils.general.CC;
import net.florial.utils.general.FilterUtils;
import net.florial.utils.iridiumcolorapi.IridiumColorAPI;
import net.kyori.adventure.text.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static dev.morphia.query.filters.Filters.eq;

public class PlayerListeners implements Listener {

    private static final ThirstManager ThirstManager = new ThirstManager();

    private static final SpeciesMenu speciesMenu = new SpeciesMenu();

    private static final Morph morph = new Morph();

    private static final Florial florial = Florial.getInstance();

    private static final HashMap<UUID, Integer> previousMessages = new HashMap<>();

    private static final HashMap<UUID, MobDisguise> savedDisguise = new HashMap<>();


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        UUID u = p.getUniqueId();

        FlorialDatabase.getPlayerData(p).thenAccept(playerData -> {
            Florial.getPlayerData().put(u, playerData);
            Florial.getPlayerData().get(u).refresh();
            new Message("&a[MONGO] &fLoaded your player data successfully!").showOnHover(playerData.toString()).send(p);
        });

        if (Florial.getPlayerData().get(u) == null) {
            val temp = FlorialDatabase.getDatastore().find(PlayerData.class).filter(eq("UUID", u.toString()));
            Florial.getPlayerData().put(u, temp.stream().findFirst().orElse(new PlayerData(u.toString())));
            new Message("&a[MONGO] &fLoaded your player data successfully!").showOnHover(Florial.getPlayerData().get(u).toString()).send(p);
        }

        PlayerData data = Florial.getPlayerData().get(u);
        ThirstManager.thirstRunnable(p);

        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), data::refresh, 100L);
        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> Reward.checkWeekStatus(p), 100L);




        if (Florial.getQuestBar().containsKey(u)) Florial.getQuestBar().get(u).addPlayer(p);

        if (data.getSpecieType().getSpecie() == null) speciesMenu.speciesMenu(p);

        Florial.getStaffWithShifts().forEach((uuid, shiftData) -> {
            if (Bukkit.getOnlinePlayers().size() > shiftData.getHighestPlayerCount()) shiftData.setHighestPlayerCount(Bukkit.getOnlinePlayers().size());
        });

        if (data.getSpecieType().getSpecie() == null) {
            Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> speciesMenu.speciesMenu(p), 40L);
            return;
        }


        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> {

            MorphAdjuster.activate(p);

            if (data.getSpecies().getMorph() == DisguiseType.FOX) morph.activate(p, 4, false, true, data.getSpecies());

            SpeciesTablistEvent e = new SpeciesTablistEvent(
                    p
            );
            Bukkit.getPluginManager().callEvent(e);

        }, 20L);
    }

    @EventHandler
    public void menuDrop(PlayerDropItemEvent e) {if (e.getPlayer().getOpenInventory().getTitle().contains("七")) e.setCancelled(true);}
    @EventHandler
    public void menuClick(InventoryClickEvent e) {if (e.getWhoClicked().getOpenInventory().getTitle().contains("七")) e.setCancelled(true);}


    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        UUID u = event.getPlayer().getUniqueId();

        Florial.getInstance().getStaffToVerify().remove(u);

        Florial.getBoards().remove(u);

        previousMessages.remove(u);

        PlayerData data = Florial.getPlayerData().get(u);
        data.save(true);

        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> {if (!event.getPlayer().isOnline())  Florial.getStaffWithShifts().remove(u);}, 6000L);



    }

    @EventHandler
    public void concreteCauldron(BlockPlaceEvent event) {

        Block block = event.getBlockPlaced();

        if (!(block.getType().toString().contains("POWDER")) || block.getRelative(BlockFace.DOWN).getType() != Material.CAULDRON) return;

        String[] colors = block.getType().toString().split("_");

        Material concreteMaterial = Material.valueOf(colors[0] + "_CONCRETE");

        block.setType(concreteMaterial);

    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {preventSliding(e.getPlayer());}

    @EventHandler
    public void dimensionChange(PlayerChangedWorldEvent e) {preventSliding(e.getPlayer());}

    @EventHandler
    public void hotBarClickSwitch(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_STEP, 0.3f, 1f);
    }

    @EventHandler
    public void crashPrevention(PlayerDeathEvent e) {

        Player p = e.getPlayer();

        if (!(p.getName().contains(".")) || DisguiseAPI.getDisguise(p) == null) return;

        MobDisguise mobDisguise = (MobDisguise) DisguiseAPI.getDisguise(p);

        savedDisguise.put(p.getUniqueId(), mobDisguise);

        mobDisguise.stopDisguise();
    }

    private static void preventSliding(Player p) {

        if (DisguiseAPI.getDisguise(p) == null && savedDisguise.get(p.getUniqueId()) == null) return;

        MobDisguise mobDisguise = DisguiseAPI.getDisguise(p) == null && savedDisguise.get(p.getUniqueId()) != null ? savedDisguise.get(p.getUniqueId()) : (MobDisguise) DisguiseAPI.getDisguise(p);

        Bukkit.getServer().getScheduler().runTaskLater(florial, () -> {

            mobDisguise.stopDisguise();
            mobDisguise.startDisguise();

        }, 15);

    }



    @EventHandler
    public void closeInventory(InventoryCloseEvent e) {

        if (e.getInventory().getType() != InventoryType.CHEST || (!(e.getReason().equals(InventoryCloseEvent.Reason.PLAYER)))) return;

        if (Florial.getPlayerData().get(e.getPlayer().getUniqueId()).getSpecieType().getSpecie() != null) return;

        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> speciesMenu.speciesMenu((Player) e.getPlayer()), 20L);
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {

        Player p = event.getPlayer();
        UUID u = p.getUniqueId();
        PlayerData data = Florial.getPlayerData().get(u);

        event.setCancelled(true);

        String message = ((TextComponent) event.message()).content();

        if (florial.ess.getUser(p).isMuted()) return;


        if (FilterUtils.check(p, message)) return;

        if (message.contains("[item]") && p.getInventory().getItemInMainHand().getType() != Material.AIR) {

            event.setCancelled(true);

            ItemStack i = p.getInventory().getItemInMainHand();
            ItemMeta meta = i.getItemMeta();

            StringBuilder itemBuilder = new StringBuilder()
                    .append(CC.translate((!(meta.getDisplayName().isBlank()) ? meta.getDisplayName() : "&9" + i.getType()) + "\n"))
                    .append(CC.translate("&b&o" + i.getType() + "\n"))
                    .append(CC.translate("&9Lore:\n"))
                    .append(meta.getLore() != null ? meta.getLore().stream().map(line -> "&f" + line + "\n").collect(Collectors.joining()) : "")
                    .append(CC.translate("&9Enchants:\n"))
                    .append(meta.hasEnchants() ? i.getEnchantments().entrySet().stream().map(entry -> CC.translate("&b") + entry.getKey().getKey() + CC.translate(": &f") + entry.getValue() + "\n").collect(Collectors.joining()) : "");


            String itemDescription = itemBuilder.toString();

            Bukkit.broadcastMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤#ff99a6 Hover your mouse under the dot to see #ff1d3a" + p.getName() + "'s #ff99a6 item."));
            Bukkit.broadcast(new ComponentBuilder(CC.translate("&f&l[*]"))
                    .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(CC.translate(itemDescription)).create()))
                    .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "i")).create());
        }

        if (Florial.getBoardLocation().get(u) != null) {

            String finalMessage1 = ChatColor.stripColor(message);

            if (RegionDetector.detect(p.getLocation()).contains("shops")) {

                new BukkitRunnable() {@Override public void run() {
                    Sign sign = (Sign) Florial.getBoardLocation().get(u).getBlock().getState();
                    sign.setLine(3, ChatColor.stripColor(finalMessage1));
                    sign.update();
                    Florial.getBoardLocation().remove(u);

                }}.runTask(florial);

            } else {
                new BukkitRunnable() {@Override public void run() {
                    BoardListener.writeBoard(p, finalMessage1, Florial.getBoardLocation().get(u));
                    Florial.getBoardLocation().remove(u);

                }}.runTask(florial);
            }
            return;
        }

        String prefix = data.getPrefix();
        if (Objects.equals(prefix, "")) {
            try {
                prefix = Objects.requireNonNull(Florial.getInstance().getLpapi().getUserManager().getUser(u)).getCachedData().getMetaData().getPrefix();
            } catch (NullPointerException e) {
                prefix = "";
            }
        }


        String suffix = Objects.requireNonNull(Florial.getInstance().getLpapi().getUserManager().getUser(u)).getCachedData().getMetaData().getSuffix();
        suffix = (suffix != null) ? suffix : "";

        String nickname = florial.ess.getUser(p).getNickname() != null ? florial.ess.getUser(p).getNickname() : p.getName();


        if (prefix == null) {
            prefix = "Default";
        }


        if (spamChecker(p)) {
            new BukkitRunnable() {@Override public void run() {Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mute " + p.getName() + " 15m You were muted for Possible Spam - Appeal: https://discord.com/invite/TRsjqSfHVq (Slow your messages!)");}}.runTask(florial);
            return;
        }

        message = (p.hasPermission("white") && (data.getGradient1().isBlank())) ? "&f" + message : (data.getGradient1().isBlank()) ? "&7" + message : message;

        if (!(data.getGradient1().isBlank())) {
            message = message.replaceAll("&", "and");
            message = message.replaceAll("%%", "percent");
            if (!(message.length() < 2)) {
                message = IridiumColorAPI.process("<GRADIENT:" +  data.getGradient1() +">"+message+"</GRADIENT:" + data.getGradient2()+">");
            } else {
                message = CC.translate("#" + data.getGradient1() + message);
            }

        }

        String town = "%townyadvanced_town%";
        if (town.isBlank()) town = "[]";
        town = PlaceholderAPI.setPlaceholders(p, town);


        Bukkit.broadcastMessage(CC.translate("#ff3c55[" + town + "] " + prefix + " &f" + nickname + suffix + ":&f " + message));
        String msg = ChatColor.stripColor(message);

        msg = msg.replaceAll("@", "@-");
        Florial.getDiscordServer().getTextChannelById(Florial.getInstance().getConfig().getString("discord.chatlogChannel")).sendMessage(event.getPlayer().getName() + ": " + msg).queue();
    }

    private static boolean spamChecker(Player p) {

        UUID u = p.getUniqueId();

        previousMessages.putIfAbsent(u, 0);
        previousMessages.put(u, previousMessages.get(u) + 1);

        if (Cooldown.isOnCooldown("spam", p) && previousMessages.get(u) > 3){
            previousMessages.put(u, 0);
            return true;
        } else if (!(Cooldown.isOnCooldown("spam", p))) {
            Cooldown.addCooldown("spam", p, 3);
            previousMessages.put(u, 0);
            return false;
        }
        return false;

    }

    @EventHandler
    public void healRefresh(PlayerCommandPreprocessEvent e) {

        if (e.getMessage().contains("heal")) Florial.getPlayerData().get(e.getPlayer().getUniqueId()).refresh();
    }

    @EventHandler
    public void onPlayerToggleSwim(EntityToggleSwimEvent event) {
        if (!(event.getEntity() instanceof Player p)) return;

        if (!(event.isSwimming())) return;

        HydrateEvent e = new HydrateEvent(p, null, net.florial.features.thirst.ThirstManager.getThirst(p), 2);
        Bukkit.getPluginManager().callEvent(e);
    }

    @EventHandler
    public void punishmentLog(PlayerCommandPreprocessEvent e) {

        Player p = e.getPlayer();
        String cmd = e.getMessage();

        Florial.getDiscordServer().getTextChannelById("1013321711753641994").sendMessage(p.getName() + " executed command: **" + cmd + "**").queue();

        if (!(p.hasPermission("staff"))) return;

        if (!(cmd.contains("ban")) && (!(cmd.contains("mute")))) return;

        Florial.getDiscordServer().getTextChannelById("950563023607722004").sendMessage("**" + p.getName() + " executed punishment: " + cmd + "**").queue();

    }


}
