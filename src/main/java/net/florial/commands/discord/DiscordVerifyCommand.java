package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.florial.Florial;
import org.bukkit.Bukkit;

import java.awt.*;
import java.time.Instant;

@CommandInfo(
        name = "verify",
        requirements = {}
)
public class DiscordVerifyCommand extends SlashCommand {

    public DiscordVerifyCommand() {
        this.name = "verify";
        this.help = "Verify yourself on the server";
        this.guildOnly = false;
        this.ownerCommand = true;
    }
    @Override
    protected void execute(SlashCommandEvent event) {
        EmbedBuilder e = new EmbedBuilder();
        User user = event.getUser();
        e.setTitle("\uD83D\uDC90 **Verification How-To and Guidelines** \uD83D\uDC90");
        e.setFooter("Verifying keeps Florial safe.");
        e.addField("**HOW TO VERIFY**", "Click the button below to verify. After that, the Tulip bot will DM you and ask you a few questions. After you answer them, it will be approved shortly. However, please answer honestly to the questions! If you are from our Minecraft server, ensure you do not misspell your username, because you only get one chance.", false);
        e.addField("**GUIDELINES**", "By verifying in our server, you accept that you are 13 years or older as per Discord TOS requirements. While in this server, you may not break TOS, this involves promoting piracy, talking about illegal drugs or illegal drinking, or the promotion of the latter, and more. For a full list of what you can and cannot do here, please see https://discord.com/guidelines", false);
        e.addField("**NOTICE**", "If you have come here to troll, please note that we will send you to The Void on-sight of any suspicious troll-like behavior. Once you are in here, you can not speak in or view any other channels except that one.", false);
        e.setColor(Color.pink);
        e.setImage("https://media.discordapp.net/attachments/803862530438332417/1053088710545121291/line.png");
        e.setTimestamp(Instant.now());
        event.getJDA().getTextChannelById("950565475107098654").sendMessage("").setEmbeds(e.build()).queue(); // and this
        Bukkit.getScheduler().runTaskLater(Florial.getInstance(), new Runnable() {
            public void run() {
                event.getJDA().getTextChannelById("950565475107098654").sendMessage("") // REMEMBER TO CHANGE THIS
                        .addActionRow(
                                Button.primary("verify", "\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38 Verify \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38"))
                        .queue();
            }
        }, 110L);

    }
}
