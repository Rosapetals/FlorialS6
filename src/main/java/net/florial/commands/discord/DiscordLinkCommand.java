package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.florial.Florial;
import net.florial.database.FlorialDatabase;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CommandInfo(
        name = "link",
        requirements = {}
)
public class DiscordLinkCommand extends SlashCommand {

    public DiscordLinkCommand() {
        this.name = "link";
        this.help = "Link discord and minecraft accounts";
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "code", "The code").setRequired(true));
        this.options = options;
    }
    @SuppressWarnings("DataFlowIssue")
    @Override
    protected void execute(SlashCommandEvent event) {
        String code = event.getOption("code").getAsString();

        MessageEmbed embed = new EmbedBuilder()
            .setTitle(" ")
            .setDescription("Linking your account...")
            .setColor(0x00FFFF)
            .build();

        event.replyEmbeds(embed).setEphemeral(true).queue((success) -> {
            UUID link = Florial.getLinkCodes().get(code);

            if (link == null) {
                success.editOriginalEmbeds(
                    new EmbedBuilder()
                        .setTitle(" ")
                        .setDescription(":x: Invalid verification code!")
                        .setColor(0xFF0000)
                        .build()
                ).queue();
                return;
            }

            Florial.getLinkCodes().remove(code);
            FlorialDatabase.getCachedOrDBPlayerData(link).thenAccept((data) -> {
                data.setDiscordId(event.getUser().getId());

                success.editOriginalEmbeds(
                    new EmbedBuilder()
                        .setTitle(" ")
                        .setDescription(":white_check_mark: Successfully linked your account!")
                        .setColor(0x00FFFF)
                        .build()
                ).queue();

                data.save(true);

                FlorialDatabase.getDiscordUserData(event.getUser().getId()).thenAccept(otherdata -> {
                    otherdata.setMcUUID(link.toString());
                    otherdata.save(true);
                });

                Player player = Bukkit.getPlayer(link);
                if (player != null)
                    player.sendMessage("§7You are now linked to §a" + event.getUser().getAsTag() + "§7!");

            });
        });
    }

}