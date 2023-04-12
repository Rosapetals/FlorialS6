package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.florial.database.FlorialDatabase;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.UUID;

@CommandInfo(
    name = "Unlink",
    requirements = {}
)
public class DiscordUnlinkCommand extends SlashCommand {

    public DiscordUnlinkCommand() {
        this.name = "unlink";
        this.help = "Unlink your discord account from your minecraft account";
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        event.replyEmbeds(
            new EmbedBuilder()
                .setTitle(" ")
                .setDescription(":cyclone: Unlinking your account...")
                .setColor(Color.RED)
                .build()
        ).setEphemeral(true).queue((success) -> {
            FlorialDatabase.getDiscordUserData(event.getUser().getId()).thenAccept((discord) -> {
                if (discord.getMcUUID().equals("")) {
                    success.editOriginalEmbeds(
                        new EmbedBuilder()
                            .setTitle(" ")
                            .setDescription(":x: You are not linked to any account!")
                            .setColor(Color.RED)
                            .build()
                    ).queue();
                    return;
                }

                FlorialDatabase.getCachedOrDBPlayerData(UUID.fromString(discord.getMcUUID())).thenAccept((data) -> {
                    data.setDiscordId("");
                    data.save(true);

                    success.editOriginalEmbeds(
                        new EmbedBuilder()
                            .setTitle(" ")
                            .setDescription(":white_check_mark: Successfully unlinked your account!")
                            .setColor(Color.GREEN)
                            .build()
                    ).queue();

                    Player player = Bukkit.getPlayer(UUID.fromString(data.getUUID()));
                    if (player != null)
                        player.sendMessage("§7You are now no longer linked to §a" + event.getUser().getAsTag() + "§7!");

                });
            });
        });
    }

}
