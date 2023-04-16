package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.florial.Florial;

import java.util.Arrays;

@CommandInfo(name = "send")
public class DiscordSendCommand extends SlashCommand {

    public DiscordSendCommand() {
        this.name = "send";
        this.options = Arrays.asList(
                new OptionData(OptionType.USER, "user", "The user to send the message to", true),
                new OptionData(OptionType.STRING, "message", "The message", true)
        );
    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {
        if (!slashCommandEvent.getMember().getRoles().contains(Florial.getDiscordServer().getRoleById(Florial.getInstance().getConfig().getString("discord.trustedStaffId")))) {
            slashCommandEvent.reply("No permissions").setEphemeral(true).queue();
            return;
        }

        slashCommandEvent.getOption("user").getAsUser().openPrivateChannel().queue(privateChannel -> {
            privateChannel.sendMessage(slashCommandEvent.getOption("message").getAsString()).queue();
        });

        slashCommandEvent.reply("Sent the message uwu").queue();
    }
}
