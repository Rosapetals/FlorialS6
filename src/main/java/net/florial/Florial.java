package net.florial;

import co.aikar.commands.PaperCommandManager;
import com.earth2me.essentials.Essentials;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.theokanning.openai.service.OpenAiService;
import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.pagination.InventoryManager;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.florial.commands.*;
import net.florial.commands.cheats.*;
import net.florial.commands.discord.*;
import net.florial.commands.menu.FloriesMenuCommand;
import net.florial.commands.menu.InstinctsMenuCommand;
import net.florial.commands.menu.ShopCommand;
import net.florial.commands.menu.SkillsMenuCommand;
import net.florial.commands.ranks.*;
import net.florial.commands.species.GrowCommand;
import net.florial.commands.species.ResetSpeciesCommand;
import net.florial.commands.species.SpeciesCommand;
import net.florial.commands.species.UserSpeciesCommand;
import net.florial.commands.staff.*;
import net.florial.database.FlorialDatabase;
import net.florial.features.crates.Crates;
import net.florial.features.enemies.impl.Boar;
import net.florial.features.enemies.impl.Crawlies;
import net.florial.features.enemies.impl.Snapper;
import net.florial.features.enemies.impl.Wisps;
import net.florial.features.quests.Quest;
import net.florial.features.quests.QuestProgressManager;
import net.florial.features.skills.attack.AttackSkillListener;
import net.florial.features.skills.scent.ScentManager;
import net.florial.features.thirst.ThirstManager;
import net.florial.listeners.*;
import net.florial.models.PlayerData;
import net.florial.models.ShiftData;
import net.florial.scoreboard.FastBoard;
import net.florial.scoreboard.Scoreboard;
import net.florial.species.events.SpeciesEventManager;
import net.florial.utils.Cooldown;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.VaultHandler;
import net.luckperms.api.LuckPerms;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Florial extends JavaPlugin {

    public static Florial getInstance() {
        return getPlugin(Florial.class);
    }

    @Setter @Getter
    public boolean speciesRegistered = false;
    public static final Scoreboard Scoreboard = new Scoreboard();

    @Getter private static final HashMap<UUID, PlayerData> playerData = new HashMap<>();
    @Getter private static final HashMap<UUID, Integer> thirst = new HashMap<>();
    @Getter private static final HashMap<UUID, FastBoard> boards = new HashMap<>();

    @Getter private static final HashMap<UUID, Quest> questData = new HashMap<>();
    @Getter private static final HashMap<UUID, BossBar> questBar = new HashMap<>();
    @Getter private static final HashMap<UUID, ShiftData> staffWithShifts = new HashMap<>();
    @Getter private static final HashMap<String, UUID> linkCodes = new HashMap<>();
    @Getter private static final HashMap<String, Short> botState = new HashMap<>();
    @Getter private static final HashMap<String, List<String>> answers = new HashMap<>();
    @Getter private static final HashMap<UUID, Boolean> bulkBuy = new HashMap<>();
    @Getter private static final HashMap<UUID, Location> signLocation = new HashMap<>();



    @Getter private static Guild discordServer;
    @Getter
    private JDA discordBot;
    @Getter
    private final InventoryManager manager = new InventoryManager(this);

    @Getter
    private Economy economy = null;
    @Getter
    private LuckPerms lpapi = null;

    public Essentials ess;


    private static final ThirstManager ThirstManager = new ThirstManager();

    @Getter
    private List<UUID> staffToVerify = new ArrayList<>();

    @Getter private static OpenAiService openAi;


    @SneakyThrows
    @Override
    public void onEnable() {

        RegisteredServiceProvider<Economy> rsp = Florial.getInstance().getServer()
                .getServicesManager().getRegistration(Economy.class);

        if (rsp == null) throw new NullPointerException("Economy service provider was not found");
        economy = rsp.getProvider();

        init();
        manager.invoke();

        FlorialDatabase.initializeDatabase();

        enableRecipes();

        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            throw new UnknownDependencyException("Vault was not found on this site");
        }
        initializeDiscord();

         rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) throw new NullPointerException("Economy service provider was not found");
        economy = rsp.getProvider();
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            lpapi = provider.getProvider();
        } else {
            throw new NullPointerException("No luckperms found");
         }
        Plugin worldGuardPlugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        if (!(worldGuardPlugin instanceof WorldGuardPlugin)) {
            throw new NullPointerException("WorldGuard is not on this server.");
        }
        ess = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");


    }


    @Override
    public void onDisable() {
        discordBot.shutdownNow();
        while (discordBot.getStatus() != JDA.Status.SHUTDOWN) {
            try {
               Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (PlayerData data : playerData.values()) data.save(false);
        FlorialDatabase.closeConnection();
        saveConfig();
    }

    private void init(){
        saveDefaultConfig();
        setupCommands();
        manager.invoke();
        VaultHandler.initiate();

         Bukkit.getScheduler().runTaskLater((this), () -> getServer().getOnlinePlayers().forEach(net.florial.scoreboard.Scoreboard::createBoard), 110L);

        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
        getServer().getPluginManager().registerEvents(new SpecieListener(), this);
        getServer().getPluginManager().registerEvents(new ThirstListener(), this);
        getServer().getPluginManager().registerEvents(new MobsListener(), this);
        getServer().getPluginManager().registerEvents(new InstinctListener(), this);
        getServer().getPluginManager().registerEvents(new AnimalListener(), this);
        getServer().getPluginManager().registerEvents(new DrillListener(), this);
        getServer().getPluginManager().registerEvents(new ClickablesListener(), this);
        getServer().getPluginManager().registerEvents(new AttackSkillListener(), this);
        getServer().getPluginManager().registerEvents(new QuestListener(), this);
        getServer().getPluginManager().registerEvents(new VoteRewardsListener(), this);
        getServer().getPluginManager().registerEvents(new PoolListener(), this);
        getServer().getPluginManager().registerEvents(new ColonyResourceListener(), this);
        getServer().getPluginManager().registerEvents(new BoardListener(), this);

        getServer().getPluginManager().registerEvents(new SpeciesEventManager(), this);
        getServer().getPluginManager().registerEvents(new ThirstManager(), this);
        getServer().getPluginManager().registerEvents(new ScentManager(), this);
        getServer().getPluginManager().registerEvents(new QuestProgressManager(), this);

        getServer().getPluginManager().registerEvents(new Crates(), this);
        getServer().getPluginManager().registerEvents(new Scoreboard(), this);


        getServer().getPluginManager().registerEvents(new Boar(EntityType.HOGLIN), this);
        getServer().getPluginManager().registerEvents(new Snapper(EntityType.RAVAGER), this);
        getServer().getPluginManager().registerEvents(new Wisps(EntityType.WITCH), this);
        getServer().getPluginManager().registerEvents(new Crawlies(EntityType.CAVE_SPIDER), this);



        if (!(Bukkit.getOnlinePlayers().size() > 0)) return;
        for (Player p : Bukkit.getOnlinePlayers()) {FlorialDatabase.getPlayerData(p).thenAccept(playerData -> {
            Florial.getPlayerData().put(p.getUniqueId(), playerData);});
            ThirstManager.thirstRunnable(p);
            Scoreboard.boardRunnable(p.getUniqueId(), p);}

        if (!(Cooldown.getCooldownMap("spam") == null)) Objects.requireNonNull(Cooldown.getCooldownMap("spam")).clear();
        if (!(Cooldown.getCooldownMap("c1") == null)) Cooldown.getCooldownMap("c1").clear();
        if (!(Cooldown.getCooldownMap("c2") == null)) Cooldown.getCooldownMap("c2").clear();
        if (!(Cooldown.getCooldownMap("c3") == null)) Cooldown.getCooldownMap("c3").clear();
        if (!(Cooldown.getCooldownMap("c4") == null)) Cooldown.getCooldownMap("c4").clear();
        if (!(Cooldown.getCooldownMap("menu") == null)) Cooldown.getCooldownMap("menu").clear();
        if (!(Cooldown.getCooldownMap("fly") == null)) Cooldown.getCooldownMap("fly").clear();
        if (!(Cooldown.getCooldownMap("sign") == null)) Cooldown.getCooldownMap("sign").clear();
        if (!(Cooldown.getCooldownMap("scent") == null)) Cooldown.getCooldownMap("scent").clear();
        if (!(Cooldown.getCooldownMap("drill") == null)) Cooldown.getCooldownMap("drill").clear();
        if (!(Cooldown.getCooldownMap("key") == null)) Cooldown.getCooldownMap("key").clear();
        if (Cooldown.getCooldownMap("c1") == null) Cooldown.createCooldown("c1");
        if (Cooldown.getCooldownMap("c2") == null) Cooldown.createCooldown("c2");
        if (Cooldown.getCooldownMap("c3") == null) Cooldown.createCooldown("c3");
        if (Cooldown.getCooldownMap("menu") == null) Cooldown.createCooldown("menu");
        if (Cooldown.getCooldownMap("fly") == null) Cooldown.createCooldown("fly");
        if (Cooldown.getCooldownMap("scent") == null) Cooldown.createCooldown("scent");
        if (Cooldown.getCooldownMap("drill") == null) Cooldown.createCooldown("drill");
        if (Cooldown.getCooldownMap("c4") == null) Cooldown.createCooldown("c4");
        if (Cooldown.getCooldownMap("key") == null) Cooldown.createCooldown("key");
        if (Cooldown.getCooldownMap("spam") == null) Cooldown.createCooldown("spam");
        if (Cooldown.getCooldownMap("sign") == null) Cooldown.createCooldown("sign");


    }

    private void enableRecipes() {

        ItemStack key1 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lTulip Crate Key", "", false), 1, "CustomModelData");
        ItemStack key2 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lExperience Crate Key", "", false), 2, "CustomModelData");
        key1= NBTEditor.set(key1, 1, "Crate");
        registerRecipes("tulip_key", true, "111", "121", "111", Arrays.asList(
                new ItemStack(Material.GOLD_INGOT),
                new ItemStack(Material.PINK_TULIP),
                null, null, null, null, null, null, null), key1);

        registerRecipes("xp_key", true, "111", "121", "111", Arrays.asList(
                new ItemStack(Material.LAPIS_LAZULI),
                key1,
                null, null, null, null, null, null, null), NBTEditor.set(key2, 2, "Crate"));
    }

    @SuppressWarnings("SameParameterValue")
    private void registerRecipes(String key, boolean isShaped, String column1, String column2, String column3, List<ItemStack> ritems, ItemStack output) {
        Recipe recipe = isShaped ? new ShapedRecipe(NamespacedKey.minecraft(key), output) : new ShapelessRecipe(
                NamespacedKey.minecraft(key), output);
        if (recipe instanceof ShapedRecipe shapedRecipe) {
            shapedRecipe.shape(column1, column2, column3);
            for (int i = 0; i < ritems.size(); i++) {

                if (i >= 9) break;

                ItemStack itemStack = ritems.get(i);

                if (itemStack != null)
                    shapedRecipe.setIngredient(Character.forDigit(i + 1, 10), new RecipeChoice.ExactChoice(itemStack));
            }
        } else {
            ShapelessRecipe shapelessRecipe = (ShapelessRecipe) recipe;
            ritems.forEach((itemStack) ->
                    shapelessRecipe.addIngredient(new RecipeChoice.ExactChoice(itemStack)));
        }
        Bukkit.removeRecipe(NamespacedKey.minecraft(key));
        Bukkit.addRecipe(recipe);

    }

    private void setupCommands() {
        PaperCommandManager manager = new PaperCommandManager(this);
        manager.registerCommand(new SpeciesCheckCommand());
        manager.registerCommand(new ChangeSpeciesCommand());
        manager.registerCommand(new ResetSpeciesCommand());
        manager.registerCommand(new SpeciesCommand());
        manager.registerCommand(new ShopCommand());
        manager.registerCommand(new ChangeDNACommand());
        manager.registerCommand(new LeaderboardCommand());
        manager.registerCommand(new ChangeSkillsCommand());
        manager.registerCommand(new NuzzleCommand());
        manager.registerCommand(new GrowCommand());
        manager.registerCommand(new SetDiscordIDCommand());
        manager.registerCommand(new StartShiftCommand());
        manager.registerCommand(new EndShiftCommand());
        manager.registerCommand(new LinkCommand());
        manager.registerCommand(new UnlinkCommand());
        manager.registerCommand(new RestorePlayerCommand());
        manager.registerCommand(new PlayTimeCommand());
        manager.registerCommand(new CheckShiftCommand());
        manager.registerCommand(new FloriesMenuCommand());
        manager.registerCommand(new UserSpeciesCommand());
        manager.registerCommand(new ShapeShiftCommand());
        manager.registerCommand(new ChangeAgeCommand());
        manager.registerCommand(new SkillsMenuCommand());
        manager.registerCommand(new DiamondFixWaterCommand());
        manager.registerCommand(new IridiumForestGenCommand());
        manager.registerCommand(new ChangeFloriesCommand());
        manager.registerCommand(new IridiumPumpkinCommand());
        manager.registerCommand(new GradientChatCommand());
        manager.registerCommand(new IridiumFlyCommand());
        manager.registerCommand(new SwitchSpeciesCommand());
        manager.registerCommand(new IridiumKeyAllCommand());
        manager.registerCommand(new SwitchNickNameCommand());
        // manager.registerCommand(new PrefixCommand());
        manager.registerCommand(new SellCommand());
        manager.registerCommand(new InstinctsMenuCommand());
        manager.registerCommand(new VoteCommand());
        manager.registerCommand(new RankFlySpeedCommand());
        manager.registerCommand(new RankGiveCommand());
        manager.registerCommand(new ProfileCommand());
        manager.registerCommand(new KeyAllCommand());
        manager.registerCommand(new ViewSelfCommand());
        manager.registerCommand(new NoFontCommand());
        manager.registerCommand(new PackOffCommand());




    }

    private void initializeDiscord() {
        try {
            CommandClientBuilder builder = new CommandClientBuilder();
            builder.setPrefix("/");
            builder.forceGuildOnly(getConfig().getString("discord.serverid"));
            builder.setOwnerId("349819317589901323");
            builder.setCoOwnerIds("366301720109776899");
            builder.addSlashCommands(new DiscordUwUCommand(), new DiscordMuteCommand(), new DiscordConfessCommand(), new DiscordAuthCommand(), new DiscordPunishCommand(), new DiscordUpdateDBCommand(), new DiscordLinkCommand(), new DiscordUnlinkCommand(), new DiscordVerifyCommand(), new DiscordSendCommand(), new DiscordProfileCommand(), new DiscordTaskCommand());
            builder.setHelpWord(null);
            builder.setActivity(Activity.watching("the RosaCage"));
            CommandClient commandClient = builder.build();
            discordBot = JDABuilder.createDefault(getConfig().getString("discord.token"),
                            GatewayIntent.GUILD_MEMBERS,
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.GUILD_MESSAGE_REACTIONS,
                            GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_EMOJIS_AND_STICKERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.MESSAGE_CONTENT)
                    .setActivity(Activity.watching("the rosacage"))
                    .addEventListeners(commandClient, new DiscordListeners())
                    .build();
            Bukkit.getLogger().info("Awaiting");
            discordBot.awaitReady();
            Bukkit.getLogger().info("Awaited");
        } catch (Exception e) {
            throw new RuntimeException("Could not initialize the discord bot, did you forget to add the information to the config file?");
        }


        if (getConfig().getString("discord.serverid") == null ||
                getConfig().getString("discord.staffId") == null ||
                getConfig().getString("discord.verificationChannel") == null ||
                getConfig().getString("discord.trustedStaffId") == null ||
                getConfig().getString("discord.adminChannel") == null ||
                getConfig().getString("discord.chatbotChannel") == null ||
                getConfig().getString("discord.openaiToken") == null ||
                getConfig().getString("discord.shiftChannel") == null) {
            throw new RuntimeException("ADD THE DATA YA TURD");
        }
        discordServer = discordBot.getGuildById(getConfig().getString("discord.serverid"));
        if (discordServer == null) {
            throw new RuntimeException("Could not find discord server from ID, did you forget to add the information to the config file?");
        }

        openAi = new OpenAiService(getConfig().getString("discord.openaiToken"));
        //        discordServer.updateCommands().addCommands(Commands.slash("uwu", "uwu")).queue();

    }

    public PlayerData getPlayerData(Player player) {return playerData.get(player.getUniqueId());}

    public static HashMap<UUID, Integer> getThirst(){return thirst;}

    public static HashMap<UUID, Quest> getQuest(){return questData;}
    public static HashMap<UUID, BossBar> getQuestBar(){return questBar;}

    public static HashMap<UUID, Location> getBoardLocation(){return signLocation;}


}