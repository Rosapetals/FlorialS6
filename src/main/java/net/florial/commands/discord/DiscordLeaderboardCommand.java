package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.florial.database.FlorialDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@CommandInfo(
        name = "leaderboard",
        requirements = {}
)
public class DiscordLeaderboardCommand extends SlashCommand {

    public DiscordLeaderboardCommand() {
        this.name = "leaderboard";
        this.help = "View Discord coins leaderboard";
    }

    @Override
    protected void execute(SlashCommandEvent event) {


        FlorialDatabase.sortDataByField("coins", true)
                .thenCompose(res -> {
                    List<String> leaderboard = new ArrayList<>();
                    int iteration = 1;
                    for (String it : res) {
                        leaderboard.add("**" + iteration + ": " + it + "**");
                        iteration++;
                    }
                    return CompletableFuture.completedFuture(leaderboard);
                })
                .thenAccept(leaderboard -> {
                    String description = String.join("\n", leaderboard);

                    MessageEmbed embed = new EmbedBuilder()
                            .setTitle("<:florialcoin:1108293880971014184> **Florial Coins Leaderboard** <:florialcoin:1108293880971014184>")
                            .setDescription(description)
                            .setColor(0x00FFFF)
                            .build();

                    event.replyEmbeds(embed).queue();
                });


    }


}