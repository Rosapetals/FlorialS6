package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.florial.Florial;
import org.bukkit.Bukkit;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@CommandInfo(
        name = "punish",
        requirements = {}
)
public class DiscordPunishCommand extends SlashCommand {

    public DiscordPunishCommand() {
        this.name = "punish";
        this.help = "Punish staff members";
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.USER, "user", "The user you are punishing").setRequired(true));
        options.add(new OptionData(OptionType.STRING, "reason", "The punishment reason").setRequired(true));
        options.add(new OptionData(OptionType.INTEGER, "warnings", "The amount of warnings").setRequired(true));
        options.add(new OptionData(OptionType.STRING, "notes", "Extra notes").setRequired(true));
        this.options = options;
    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {
        Member member = slashCommandEvent.getMember();
        Bukkit.getLogger().info(member.toString());
        if (member == null) {
            slashCommandEvent.reply("There was an error trying to perform this command").queue();
            return;
        }
        if (!member.getRoles().contains(Florial.getDiscordServer().getRoleById(Florial.getInstance().getConfig().getString("discord.trustedStaffId")))) {
            slashCommandEvent.reply("No permissions").setEphemeral(true).queue();
            return;
        }
        // slashCommandEvent.deferReply().queue();

        Member victim = slashCommandEvent.getOption("user").getAsMember();
        if (victim == null) {
            slashCommandEvent.reply("This user does not exist").queue();
            return;
        }

        victim.getUser().openPrivateChannel().queue(privateChannel -> {
            privateChannel.sendMessage("").setEmbeds(new EmbedBuilder()
                    .setTitle("You have been warned (" + slashCommandEvent.getOption("warnings").getAsInt() + ") time(s)")
                            .setColor(Color.RED)
                            .addField("You have been warned for:", slashCommandEvent.getOption("reason").getAsString(), false)
                            .addField("Warned by:", member.getEffectiveName(), false)
                            .addField("Extra notes:", slashCommandEvent.getOption("notes").getAsString(), false)
                            .setDescription("Please remember that further infractions will result in further punishment. Contact an Admin if you have any questions")
                            .setImage("https://cdn.discordapp.com/attachments/803862530438332417/1086025631759863858/e_iCrV1QvxuH4aKxPWIfTJw1HGiIoG-O1Ga8PRjmta0.png")
                    .build()
            ).queue();
        });
        slashCommandEvent.reply("").setEmbeds(new EmbedBuilder()
                .setTitle("Punishment processed")
                .addField("Victim", victim.getEffectiveName(), false)
                .addField("Staff", slashCommandEvent.getUser().getName(), false)
                .addField("Reason", slashCommandEvent.getOption("reason").getAsString(), false)
                .addField("Notes", slashCommandEvent.getOption("notes").getAsString(), false)
                .setColor(0xffd4ee)
                .setFooter("Tulip is a furry uwu").build()
        ).queue();
    }
}