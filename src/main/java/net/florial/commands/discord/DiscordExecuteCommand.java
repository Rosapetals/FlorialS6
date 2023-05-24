package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.florial.Florial;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

@CommandInfo(
        name = "execute",
        requirements = {}
)
public class DiscordExecuteCommand extends SlashCommand {

    public DiscordExecuteCommand() {
        this.name = "execute";
        this.help = "Execute a command via Discord";
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "command", "The command to output").setRequired(true));
        this.options = options;
    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        Member member = slashCommandEvent.getMember();
        String command = slashCommandEvent.getOption("command").getAsString();

        if (!member.getRoles().contains(Florial.getDiscordServer().getRoleById(Florial.getInstance().getConfig().getString("discord.trustedStaffId")))) {
            slashCommandEvent.reply("No permissions").setEphemeral(true).queue();
            return;
        }

        Bukkit.getScheduler().runTask(Florial.getInstance(), () -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
        });

        slashCommandEvent.reply("Successfully executed the command: **" + command + "**").queue();

    }
}