package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CommandInfo(
        name = "warn",
        description = "warn a member for a specified reason"
)
public class DiscordWarnCommand extends SlashCommand {

    public DiscordWarnCommand() {
        this.name = "warn";
        this.help = "warn a member for a specified reason";
        this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE};
        this.guildOnly = false;
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.USER, "username", "the individual being warned in question").setRequired(true));
        options.add(new OptionData(OptionType.STRING, "reason", "the reason for the warning").setRequired(true));
        options.add(new OptionData(OptionType.STRING, "note", "a personal staff note for the user").setRequired(true));

        this.options = options;
    }

    @Override
    protected void execute(SlashCommandEvent event) {

        OptionMapping option = event.getOption("username");
        OptionMapping option2 = event.getOption("reason");
        OptionMapping option4 = event.getOption("note");
        assert option != null;
        User user = option.getAsUser();

        assert option4 != null;
        assert option2 != null;

        event.reply("It was sent to the user.").queue();

        EmbedBuilder e = new EmbedBuilder();
        e.setTitle("**Florial Warning**");
        e.addField("**Reason:**", option2.getAsString(), false);
        e.addField("**Issued By:**", event.getUser().getName(), false);
        e.addField("**Note:**", option4.getAsString(), false);
        e.addField("**Expires:**", "*1/1/2023*", false);
        e.setFooter("If you repeat your action, harsher punishment will be taken.");
        e.setColor(Color.PINK);
        e.setImage(Objects.requireNonNull(event.getUser().getAvatar()).getUrl());
        e.setTimestamp(Instant.now());

        user.openPrivateChannel().queue((channel) -> channel.sendMessageEmbeds(e.build()).queue());


        Objects.requireNonNull(event.getJDA().getTextChannelById("1053237074620325918")).sendMessage("" +  user.getAsMention() + " has been warned by " + event.getUser().getAsMention() + " for " + option2.getAsString() + ". " + option4.getAsString()).queue();
    }
}
