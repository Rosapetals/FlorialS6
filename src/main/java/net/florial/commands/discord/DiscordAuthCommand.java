package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.utils.Message;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CommandInfo(
        name = "auth",
        requirements = {}
)
public class DiscordAuthCommand extends SlashCommand {

    public DiscordAuthCommand() {
        this.name = "auth";
        this.help = "Authenticate yourself on the server";
        this.aliases = new String[] {"authenticate"};
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "username", "Ingame Username").setRequired(true));
        this.options = options;
    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {
        Member member = slashCommandEvent.getMember();
        if (member == null) {
            slashCommandEvent.reply("There was an error trying to perform this command").setEphemeral(true).queue();
            return;
        }
        if (!member.getRoles().contains(Florial.getDiscordServer().getRoleById(Florial.getInstance().getConfig().getString("discord.staffId")))) {
            slashCommandEvent.reply("No permissions").setEphemeral(true).queue();
            return;
        }

        PlayerData targetUser = Florial.getInstance().getPlayerData(Bukkit.getPlayer(slashCommandEvent.getOption("username").getAsString()));
        if (targetUser == null) {
            slashCommandEvent.reply("This player is not currently online").setEphemeral(true).queue();
            return;
        }
        if (!Florial.getInstance().getStaffToVerify().contains(UUID.fromString(targetUser.getUUID()))) {
            slashCommandEvent.reply("This player does not need to be verified").setEphemeral(true).queue();
            return;
        }
        if (!slashCommandEvent.getUser().getId().equals(targetUser.getDiscordId())) {
            slashCommandEvent.reply("You do not have permission to authenticate this user").queue();
            return;
        }

        Florial.getInstance().getStaffToVerify().remove(UUID.fromString(targetUser.getUUID()));
        new Message("&aSuccessfully Authenticated").send(Bukkit.getPlayer(slashCommandEvent.getOption("username").getAsString()));
        slashCommandEvent.reply("Successfully authenticated " + slashCommandEvent.getOption("username").getAsString()).queue();
    }
}