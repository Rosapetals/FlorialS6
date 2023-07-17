package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.florial.database.FlorialDatabase;
import net.florial.models.DiscordUser;
import net.florial.utils.GeneralUtils;
import org.bukkit.Bukkit;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CommandInfo(
        name = "confess",
        requirements = {}
)
public class DiscordProfileCommand extends SlashCommand {

    public DiscordProfileCommand() {
        this.name = "profile";
        this.help = "Get the discord profile of a user";
        this.guildOnly = true;
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.USER, "user", "The user you want to find the profile for").setRequired(true));
        this.options = options;
    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {
        GeneralUtils.runAsync(() -> {
            Member target = Objects.requireNonNull(slashCommandEvent.getOption("user")).getAsMember();
            if (target == null) {
                slashCommandEvent.reply("User not found").setEphemeral(true).queue();
                return;
            }

            DiscordUser data = FlorialDatabase.getDiscordUserData(target.getId()).join();

            String linkedAccount = "No account linked";

            if (!data.getMcUUID().isEmpty()) {
                linkedAccount = Bukkit.getOfflinePlayer(data.getMcUUID()).getName();
            }
            if (linkedAccount == null) {
                linkedAccount = "No account linked";
            }
            MessageEmbed embed = new EmbedBuilder()
                    .setTitle(target.getNickname() + "'s profile")
                    .setImage("https://media.discordapp.net/attachments/803862530438332417/1108294639674466334/q.png")
                    .addField("**Joined Florial On**", target.getTimeJoined().format(DateTimeFormatter.ofPattern("d MMM uuuu")), false)
                    .addField("<:orb:1108295171453493400> Level", String.valueOf(data.getLevel()), false)
                    .addField("<:orb:1108295171453493400> Experience", data.getExp() + "/100", false)
                    .addField("<:florialcoin:1108293880971014184> Coins", String.valueOf(data.getCoins()), false)
                    .addField("Minecraft Account", linkedAccount, false)
                    .setColor(Color.PINK)
                    .build();

            slashCommandEvent.replyEmbeds(embed).queue();
        });
    }
}