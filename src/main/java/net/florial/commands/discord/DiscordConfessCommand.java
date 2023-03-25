package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.florial.Florial;
import net.florial.utils.GeneralUtils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CommandInfo(
        name = "confess",
        requirements = {}
)
public class DiscordConfessCommand extends SlashCommand {

    public DiscordConfessCommand() {
        this.name = "confess";
        this.help = "Send an anonymous message to the admins";
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "message", "The message").setRequired(true));
        this.options = options;
    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {
        GeneralUtils.runAsync(new BukkitRunnable() {
            @Override
            public void run() {
                Member member = Florial.getDiscordServer().getMemberById(slashCommandEvent.getUser().getId());
                if (member == null) {
                    slashCommandEvent.reply("There was an error trying to perform this command").setEphemeral(true).queue();
                    return;
                }
                if (!member.getRoles().contains(Florial.getDiscordServer().getRoleById(Florial.getInstance().getConfig().getString("discord.staffId")))) {
                    slashCommandEvent.reply("No permissions").setEphemeral(true).queue();
                    return;
                }

                Florial.getDiscordServer().getTextChannelById(Florial.getInstance().getConfig().getString("discord.adminChannel")).sendMessage("").setEmbeds(new EmbedBuilder()
                        .setTitle("New Anonymous Message")
                        .setDescription(slashCommandEvent.getOption("message").getAsString())
                        .setFooter("Tulip is a furry uwu").build()
                ).queue();

                slashCommandEvent.reply("Message sent successfully").setEphemeral(true).queue();
            }
        });
    }
}
