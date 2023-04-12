package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CommandInfo(
        name = "mute",
        requirements = {}
)
public class DiscordMuteCommand extends SlashCommand {

    public DiscordMuteCommand() {
        this.name = "mute";
        this.help = "Time a user out for a given length of time";
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.USER, "user", "The user you are muting").setRequired(true));
        options.add(new OptionData(OptionType.STRING, "reason", "The punishment reason").setRequired(true));
        options.add(new OptionData(OptionType.STRING, "duration", "The length of the punishment").setRequired(true));
        this.options = options;
    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {
        Member member = slashCommandEvent.getMember();
        if (member == null) {
            slashCommandEvent.reply("There was an error trying to perform this command").queue();
            return;
        }
        if (!member.hasPermission(Permission.KICK_MEMBERS)) {
            slashCommandEvent.reply("No permissions").setEphemeral(true).queue();
            return;
        }
        // slashCommandEvent.deferReply().queue();

        Member victim = slashCommandEvent.getOption("user").getAsMember();
        if (victim == null) {
            slashCommandEvent.reply("This user does not exist").queue();
            return;
        }
        Bukkit.getLogger().info(victim.toString());

        String durationText = slashCommandEvent.getOption("duration").getAsString();
        long duration = 0;
        Pattern pattern;
        Matcher m;
        pattern = Pattern.compile("\\dd");
        m = pattern.matcher(durationText);
        while (m.find()) {
            duration += Integer.parseInt(m.group(0).replaceAll("d", "")) * 86400L;
        }
        pattern = Pattern.compile("\\dw");
        m = pattern.matcher(durationText);
        while (m.find()) {
            duration += Integer.parseInt(m.group(0).replaceAll("w", "")) * 604800L;
        }
        pattern = Pattern.compile("\\dh");
        m = pattern.matcher(durationText);
        while (m.find()) {
            duration += Integer.parseInt(m.group(0).replaceAll("h", "")) * 3600L;
        }
        pattern = Pattern.compile("\\dm");
        m = pattern.matcher(durationText);
        while (m.find()) {
            duration += Integer.parseInt(m.group(0).replaceAll("m", "")) * 60L;
        }
        pattern = Pattern.compile("\\ds");
        m = pattern.matcher(durationText);
        while (m.find()) {
            duration += Integer.parseInt(m.group(0).replaceAll("s", ""));
        }
        Bukkit.getLogger().info(String.valueOf(duration));
        if (duration > 1209600) {
            duration = 1209600;
        }
        victim.timeoutFor(duration, TimeUnit.SECONDS).queue();
        slashCommandEvent.reply("").setEmbeds(new EmbedBuilder()
                .setTitle("Punishment processed")
                .addField("Victim", victim.getEffectiveName(), false)
                .addField("Staff", slashCommandEvent.getUser().getName(), false)
                .addField("Reason", slashCommandEvent.getOption("reason").getAsString(), false)
                .addField("Duration", durationText, false)
                .addField("If the above punishment was not correct:", "Discord limits us to a timeout maximum of 2 weeks, so your punishment length is capped at 2 weeks", false)
                .setColor(0xffd4ee)
                        .setImage(slashCommandEvent.getUser().getAvatarUrl())
                .setFooter("Tulip is a furry uwu").build()
        ).queue();
    }
}
