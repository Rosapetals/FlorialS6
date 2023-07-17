package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import net.florial.Florial;
import org.bukkit.Bukkit;

import java.awt.*;
import java.time.Instant;

@CommandInfo(
        name = "shop",
        requirements = {}
)
public class DiscordShopCommand extends SlashCommand {

    public DiscordShopCommand() {
        this.name = "shop";
        this.help = "set up the discord shop";
        this.guildOnly = false;
        this.ownerCommand = true;
    }
    @Override
    protected void execute(SlashCommandEvent event) {
        EmbedBuilder e = new EmbedBuilder();
        e.setTitle("\uD83D\uDC90 **HOW TO GET COINS** \uD83D\uDC90");
        e.addField("**Information**", "Coins are obtained simply through Discord chatting.\n" +
                "\n" +
                "Spamming will lower your chances of gaining coins, but thoughtful constructed messages yield more coins. You also get doubled coins if you are currently boosting our server. You can also do **/profile** to see your coin count. Lastly, it would be best to **link your account via /link in-game** as some shop options require your Discord to be linked to our Minecraft server.", false);
        e.setColor(Color.pink);
        e.setTimestamp(Instant.now());
        event.getJDA().getTextChannelById("1106833162379735110").sendMessage("").setEmbeds(e.build()).queue();

        EmbedBuilder e3 = new EmbedBuilder();
        e3.setTitle("\uD83D\uDC90 **Purchase a Name Color** \uD83D\uDC90");
        e3.addField("**Information**", "Are you confused on how to obtain Coins? Read the top of this channel!\n" +
                "\n" +
                "View your coins by doing **/profile** in any channel!", false);
        e3.addField("**Costs**", "**350 Coins**", false);
        e3.setColor(Color.pink);
        e3.setImage("https://media.discordapp.net/attachments/803862530438332417/1106831680246915122/chart.png");
        e3.setTimestamp(Instant.now());
        event.getJDA().getTextChannelById("1106833162379735110").sendMessage("").setEmbeds(e3.build()).queue();

        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), () -> event.getChannel().sendMessage(" ").addActionRow(
                        StringSelectMenu.create("choose-color")
                                .addOptions(SelectOption.of("Blue", "1107164531299454986")
                                .withDescription("the color of the sky")
                                .withEmoji(Emoji.fromUnicode("\uD83D\uDD35")))
                                .addOptions(SelectOption.of("Red", "1107164346443907082")
                                        .withDescription("seductive and spooky")
                                        .withEmoji(Emoji.fromUnicode("\uD83D\uDD34")))
                                .addOptions(SelectOption.of("Purple", "1107164548965871696")
                                        .withDescription("reminiscent of space")
                                        .withEmoji(Emoji.fromUnicode("\uD83D\uDFE3")))
                                .addOptions(SelectOption.of("Orange", "1107164563943723028")
                                        .withDescription("the color of a fruit")
                                        .withEmoji(Emoji.fromUnicode("\uD83D\uDFE0")))
                                .addOptions(SelectOption.of("Yellow", "1107164607635792023")
                                        .withDescription("the color of p-")
                                        .withEmoji(Emoji.fromUnicode("\uD83D\uDFE1")))
                                .addOptions(SelectOption.of("White", "1107164620373897279")
                                        .withDescription("blank paper")
                                        .withEmoji(Emoji.fromUnicode("⚪")))
                                .addOptions(SelectOption.of("Black", "1107164629488111658")
                                        .withDescription("like the sky at night")
                                        .withEmoji(Emoji.fromUnicode("⚫")))
                                .addOptions(SelectOption.of("Green", "1107164655379562506")
                                        .withDescription("who likes this color anyway?")
                                        .withEmoji(Emoji.fromUnicode("\uD83D\uDFE2")))
                                .addOptions(SelectOption.of("Rosy Red", "1107164674404909116")
                                        .withDescription("a color most won't understand")
                                        .withEmoji(Emoji.fromUnicode("\uD83C\uDF39")))
                                .build())
                                .queue(), 20L);


        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), new Runnable() {
            public void run() {
                EmbedBuilder e2 = new EmbedBuilder();
                e2.setTitle("\uD83D\uDC90 **Flories Conversion - 50 = 5 Flories** \uD83D\uDC90");
                e2.addField("**Information**", "Are you confused on how to obtain Coins? Read the top of this channel!\n" +
                        "\n" +
                        "View your coins by doing **/profile** in any channel!", false);
                e2.addField("**Costs**", "**50 Coins**", false);
                e2.setColor(Color.pink);
                e2.setImage("https://media.discordapp.net/attachments/803862530438332417/1106831680246915122/chart.png");
                e2.setTimestamp(Instant.now());
                event.getJDA().getTextChannelById("1106833162379735110").sendMessage("").setEmbeds(e2.build()).queue();
            }
        }, 50L);

        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), new Runnable() {
            public void run() {
                event.getJDA().getTextChannelById("1106833162379735110").sendMessage(" " )
                        .addActionRow(
                                Button.primary("convert", "\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38 Convert \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38"))
                        .queue();
            }
        }, 60L);

        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), new Runnable() {
            public void run() {
                EmbedBuilder e2 = new EmbedBuilder();
                e2.setTitle("\uD83D\uDC90 **Purchase Diamond Rank** \uD83D\uDC90");
                e2.addField("**Information**", "Are you confused on how to obtain Coins? Read the top of this channel!\n" +
                        "\n" +
                        "View your coins by doing **/profile** in any channel!", false);
                e2.addField("**Costs**", "**3000 Coins**", false);
                e2.setColor(Color.pink);
                e2.setImage("https://media.discordapp.net/attachments/803862530438332417/1106831680246915122/chart.png");
                e2.setTimestamp(Instant.now());
                event.getJDA().getTextChannelById("1106833162379735110").sendMessage("").setEmbeds(e2.build()).queue();
            }
        }, 70L);

        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), new Runnable() {
            public void run() {
                event.getJDA().getTextChannelById("1106833162379735110").sendMessage(" " )
                        .addActionRow(
                                Button.primary("diamond", "\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38 Purchase \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38"))
                        .queue();
            }
        }, 90L);

    }
}
