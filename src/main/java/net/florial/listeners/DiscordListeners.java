package net.florial.listeners;

import dev.morphia.query.filters.Filters;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.requests.RestAction;
import net.florial.Florial;
import net.florial.database.FlorialDatabase;
import net.florial.models.DiscordUser;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import net.florial.utils.math.GetChance;
import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class DiscordListeners extends ListenerAdapter {

    private static final List<String> specialChannels = List.of(
            "919361125739548743",
            "943028902828322826",
            "938256684286083132",
            "1108299163579461672"
            );
    private static final List<String> allowedChannels = List.of(
            "832689488513204235",
            "814301616764747816",
            "938257050364968981",
            "1023840490845650995",
            "912206285930381403",
            "919361125739548743",
            "943028902828322826",
            "938256684286083132",
            "1108299163579461672"
    );


        @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();

        if (event.getMessage().isFromType(ChannelType.PRIVATE)) {
            User user = event.getAuthor();
            Florial.getDiscordServer().getTextChannelById(Florial.getInstance().getConfig().getString("discord.verificationChannel")).sendMessage("**" + event.getAuthor().getName() + ":** " + event.getMessage().getContentRaw()).queue();
            if (Florial.getBotState().get(user.getId()) == null) return;
            switch (Florial.getBotState().get(user.getId())) {
                case 1:
                    message.getChannel().sendMessage("**Have you read & Acknowledged the rules of the server?**").queue();
                    Florial.getBotState().put(user.getId(), (short) 2);
                    Florial.getAnswers().get(user.getId()).add((event.getMessage().getContentRaw()));
                    break;
                case 2:
                    message.getChannel().sendMessage("What do you plan to do in Florial Official?").queue();
                    Florial.getBotState().put(user.getId(), (short) 3);
                    Florial.getAnswers().get(user.getId()).add((event.getMessage().getContentRaw()));
                    break;
                case 3:
                    message.getChannel().sendMessage("**What is your Minecraft Username? Don't misspell it!** If none, you MUST say **'none'** and nothing else.").queue();
                    Florial.getBotState().put(user.getId(), (short) 4);
                    Florial.getAnswers().get(user.getId()).add((event.getMessage().getContentRaw()));
                    break;
                case 4:
                    Florial.getAnswers().get(user.getId()).add((event.getMessage().getContentRaw()));
                    message.getChannel().sendMessage("Please wait while we review your application! Once accepted, you will receive your 25 Flories and special rank immediately if you have a Minecraft account.").queue();
                    EmbedBuilder e = new EmbedBuilder();
                    e.setTitle("**Verification Application of " + user.getName() + "**");
                    e.setFooter("Press the buttons below to accept or deny entry.");
                    e.addField("**How did you find Florial?**", Florial.getAnswers().get(user.getId()).get(0), false);
                    e.addField("**Have you read the rules?**", Florial.getAnswers().get(user.getId()).get(1), false);
                    e.addField("**What do you plan to do in Florial?**", Florial.getAnswers().get(user.getId()).get(2), false);
                    e.addField("**Minecraft Username:**", Florial.getAnswers().get(user.getId()).get(3), false);
                    e.addField("**Account Created:** ", "" + user.getTimeCreated(), false);
                    e.setColor(Color.pink);
                    e.setThumbnail(user.getAvatarUrl());
                    e.setImage("https://media.discordapp.net/attachments/842010486009626625/1055353813965475870/bow.png");
                    e.setTimestamp(Instant.now());
                    TextChannel channel = event.getJDA().getTextChannelById("950565475107098654");
                    channel.sendMessageEmbeds(e.build()).queue();
                    channel.sendMessage("" + user.getAsMention());
                    Bukkit.getScheduler().runTaskLater(Florial.getInstance(), new Runnable() {
                        public void run() {
                            Florial.getDiscordServer().getTextChannelById(Florial.getInstance().getConfig().getString("discord.verificationChannel")).sendMessage("@here")
                                    .addActionRow(
                                            Button.primary("ide," + Florial.getAnswers().get(user.getId()).get(3) + "," + user.getId(), "Grant Access"))
                                    .addActionRow(
                                            Button.primary("ida" + user.getId(), "Deny Access"))
                                    .queue();
                        }
                    }, 110L);
                    Florial.getBotState().remove(user.getId());
                    break;
            }
        }

        if (event.getChannelType() == ChannelType.TEXT) {

            if (event.getChannel().getId().contains("910011802883092521")) {

                String name = (Objects.requireNonNull(event.getMember()).getNickname() == null) ? event.getMember().getEffectiveName() : event.getMember().getNickname();

                Bukkit.broadcastMessage(CC.translate("&9[Discord]#ff3c55 " + name + ":&f " + event.getMessage().getContentRaw()));

            }
            if (!(allowedChannels.contains(event.getChannel().getId()))) return;

            FlorialDatabase.getDiscordUserData(event.getAuthor().getId()).thenAccept(discordUser -> {
                if (discordUser == null) {
                    System.out.println("it was null");
                    new DiscordUser(event.getAuthor().getId()).createNew();
                } else {

                    if (specialChannels.contains(event.getChannel().getId())) {
                        discordUser.setCoins(discordUser.getCoins() + 2);
                        discordUser.save(true);
                        return;
                    }

                    int maxChance = 25;
                    int minChance = 5;
                    int maxMessageLength = 100;

                    double slope = (double)(maxChance - minChance) / (double)(maxMessageLength - 10);

                    int chance = (int)Math.round(slope * message.getContentRaw().length() + minChance - slope * 10);

                    if (GetChance.chanceOf(chance)) {

                        discordUser.setCoins(discordUser.getCoins() + (Objects.requireNonNull(event.getMember()).isBoosting() ? 2 : 1));
                    }


                    discordUser.setExp(discordUser.getExp() + chance/4);

                    if (discordUser.getExp() >= 100) {
                        discordUser.setExp(0);
                        discordUser.setLevel(discordUser.getLevel() + 1);
                    }

                    discordUser.save(true);
                }
            });

        }
    }


    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        FlorialDatabase.getDatastore().find(DiscordUser.class).filter(Filters.eq("uuid", event.getUser().getId())).stream().findFirst().ifPresent(discordUser -> {
            FlorialDatabase.getDatastore().find(DiscordUser.class).filter(Filters.eq("uuid", event.getUser().getId())).delete();
        });
        Florial.getBotState().remove(event.getUser().getId());
        Florial.getAnswers().remove(event.getUser().getId());
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {

        if (event.getComponentId().equals("verify")) {
            Role role = event.getGuild().getRoleById(Florial.getInstance().getConfig().getString("discord.verifiedRole"));
            if (role == null) {
                throw new NullPointerException("Awaiting role is null");
            }
            if (event.getMember().getRoles().contains(role)) {
                event.reply("You are already verified.").setEphemeral(true).queue();
                return;
            } else if (event.getMember().getRoles().contains(event.getGuild().getRoleById("1055994303350063208"))) {
                event.reply("You are already in the verification process. If you are stuck and unable to verify, contact a staff member IMMEDIATELY.").setEphemeral(true).queue();
                return;
            }
            User user = event.getUser();
            event.reply("You have activated verification and should have received a DM. If you did not, please enable your DMS and try again so the bot can DM you.").setEphemeral(true).queue();
            ArrayList<String> list = new ArrayList<>();
            if (Florial.getAnswers().get(user.getId()) == null) Florial.getAnswers().put(user.getId(), list);
            user.openPrivateChannel().queue((channel) -> channel.sendMessage("You have initiated Florial's Verification process. I'm Tulip! I'll be asking you some questions.. hope you don't mind! **How did you find Florial**").queue());
            Florial.getBotState().put(user.getId(), (short) 1);
            Role awaiting = event.getJDA().getRoleById(Florial.getInstance().getConfig().getString("discord.awaitingVerificationRole"));
            if (awaiting == null) {
                throw new NullPointerException("Awaiting role is null");
            }
            Florial.getDiscordServer().addRoleToMember(user, awaiting).queue();

        }
        //Grant
        if (event.getComponentId().contains("ide")) {
            String data = event.getComponentId();
            String[] id = data.split("ide,", 2);
            String[] id2 = id[1].split(",", 2);
            String userid = id2[1];
            String username = id2[0];
            userid = userid.replaceAll(" ", "");
            event.getJDA().retrieveUserById(userid).queue((user) -> {
                event.reply("The member has been granted access to the server!").setEphemeral(true).queue();
                Guild guild = Florial.getDiscordServer();
                Role unverified = guild.getRoleById("1051772631407415346");
                Role tulip = guild.getRoleById("801913598644846604");
                Role pronoundiv = guild.getRoleById("1055271837120086016");
                Role likesdiv = guild.getRoleById("1055246346262687837");
                Role pingsdiv = guild.getRoleById("989259813601046578");
                Role rolesdiv = guild.getRoleById("1055272730196443136");
                Role awaiting = guild.getRoleById("1055994303350063208");
                Role aboutdiv = guild.getRoleById("1055272722671865966");
                Role levelsdiv = guild.getRoleById("1055272580153606174");
                guild.removeRoleFromMember(user, unverified).queue();
                guild.removeRoleFromMember(user, awaiting).queue();
                guild.addRoleToMember(user, tulip).queue();
                guild.addRoleToMember(user, pronoundiv).queue();
                guild.addRoleToMember(user, likesdiv).queue();
                guild.addRoleToMember(user, levelsdiv).queue();
                guild.addRoleToMember(user, pingsdiv).queue();
                guild.addRoleToMember(user, rolesdiv).queue();
                guild.addRoleToMember(user, aboutdiv).queue();
                TextChannel channel = guild.getTextChannelById("944360964755701790");
                RestAction<Message> msg = channel.sendMessage("" + user.getAsMention());
                user.openPrivateChannel().queue((channel2) -> channel2.sendMessage("Congratulations, you were accepted and given your rewards on Minecraft too, if you had an account!").queue());
                msg.queue(success -> {
                    channel.deleteMessageById(success.getId()).queue();
                });
                //guild.getTextChannelById("803862530438332417").sendMessage("**" + user.getName() + ":** " + Core.getInstance().lists.get(user)).queue();
                Bukkit.getScheduler().runTask(Florial.getInstance(), () -> {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + username+ " parent add special");
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "changeflories " + username + " flories 25");
                });
                Florial.getAnswers().remove(user.getId());
                Florial.getBotState().remove(user.getId());
                event.getMessage().delete().queue();

            });
        } else if (event.getComponentId().contains("ida")) {
            String userid = event.getComponentId();
            userid = userid.replaceAll("ida", "");
            System.out.print(userid);
            event.getJDA().retrieveUserById(userid).queue((user) -> {
                event.reply("The member has been denied access, " + event.getUser() + ". Be sure to tell them why with the /send command.").setEphemeral(true).queue();
                user.openPrivateChannel().queue((channel2) -> channel2.sendMessage("You were denied entry into Florial Official. Shortly, the reason will be stated.").queue());
                Florial.getBotState().remove(user.getId());
                event.getMessage().delete().queue();
                Florial.getAnswers().remove(user.getId());
            });
        } else if (event.getComponentId().contains("convert")) {

            coinCheck(event.getUser(), 50).thenAccept(pair -> {

                boolean outcome = pair.getLeft();
                DiscordUser discordUser = pair.getRight();

                if (outcome) {

                    if (Objects.equals(discordUser.getMcUUID(), "")) {
                        event.reply("You need to have your Minecraft account linked to do this. Do /link in-game to start.").setEphemeral(true).queue();
                        return;
                    }

                    UUID u = UUID.fromString(discordUser.getMcUUID());

                    PlayerData data = Florial.getPlayerData().get(u);

                    data.setFlories(data.getFlories() + 5);

                    event.reply("Successfully converted! Your flories now: " + data.getFlories()).setEphemeral(true).queue();

                } else {
                    event.reply("You need at least 100 Coins <:florialcoin:1108293880971014184> -- but you don't have it! Read the top of the channel to learn how to get coins").setEphemeral(true).queue();
                }
            });
        } else if (event.getComponentId().contains("diamond")) {

            coinCheck(event.getUser(), 3000).thenAccept(pair -> {

                boolean outcome = pair.getLeft();
                DiscordUser discordUser = pair.getRight();

                if (outcome) {

                    if (Objects.equals(discordUser.getMcUUID(), "")) {
                        event.reply("You need to have your Minecraft account linked to do this. Do /link in-game to start.").setEphemeral(true).queue();
                        return;
                    }

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + Bukkit.getOfflinePlayer(UUID.fromString(discordUser.getMcUUID())).getName());

                    event.reply("Successfully purchased! If you did not get it, open a ticket!").setEphemeral(true).queue();

                } else {
                    event.reply("You need at least 3000 Coins <:florialcoin:1108293880971014184> -- but you don't have it! Read the top of the channel to learn how to get coins").setEphemeral(true).queue();
                }
            });
        } else if (event.getComponentId().contains("emoji")) {

            coinCheck(event.getUser(), 600).thenAccept(pair -> {

                boolean outcome = pair.getLeft();

                if (outcome) {

                    (Florial.getDiscordServer().getTextChannelById("842010486009626625")).sendMessage("" + event.getUser().getName()).queue();

                    event.reply("Successfully purchased! You will be reached out to soon.").setEphemeral(true).queue();

                } else {
                    event.reply("You need at least 600 Coins <:florialcoin:1108293880971014184> -- but you don't have it! Read the top of the channel to learn how to get coins").setEphemeral(true).queue();
                }
            });
        }
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        FlorialDatabase.getDiscordUserData(event.getUser().getId()).thenAccept(discordUser -> {
            if (discordUser == null) {
                FlorialDatabase.getDatastore().insert(new DiscordUser(event.getUser().getId()));
            }
        });
    }

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {

        Guild guild = Florial.getDiscordServer();
        if (!event.getComponentId().equals("choose-color")) return;
        coinCheck(event.getUser(), 350).thenAccept(pair -> {

            boolean outcome = pair.getLeft();

            if (outcome) {

                for (SelectOption option : event.getComponent().getOptions()) {

                    if (!(Objects.requireNonNull(event.getMember()).getRoles().contains(guild.getRoleById(option.getValue()))))
                        continue;
                    Florial.getDiscordServer().removeRoleFromMember(event.getUser(), Objects.requireNonNull(guild.getRoleById(option.getValue()))).queue();

                }

                Florial.getDiscordServer().addRoleToMember(event.getUser(), Objects.requireNonNull(guild.getRoleById(event.getValues().get(0)))).queue();
                event.reply("Successfully purchased this color.").setEphemeral(true).queue();

            } else {
                event.reply("You need at least 350 Coins <:florialcoin:1108293880971014184> -- but you don't have it! Read the top of the channel to learn how to get coins").setEphemeral(true).queue();
            }
        });
    }

    private static CompletableFuture<Pair<Boolean, DiscordUser>> coinCheck(User user, int target) {

        CompletableFuture<Pair<Boolean, DiscordUser>> future = new CompletableFuture<>();

        FlorialDatabase.getDiscordUserData(user.getId()).thenAccept(discordUser -> {
            if (discordUser == null) {
                new DiscordUser(user.getId()).createNew();
                future.complete(Pair.of(false, null));
            } else {
                if (discordUser.getCoins() >= target) {
                    discordUser.setCoins(discordUser.getCoins() - target);
                    discordUser.save(true);
                    future.complete(Pair.of(true, discordUser));
                } else {
                    future.complete(Pair.of(false, discordUser));
                }
            }
        });

        return future;
    }
}
