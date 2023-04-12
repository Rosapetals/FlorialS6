package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import dev.morphia.query.filters.Filters;
import net.dv8tion.jda.api.entities.Member;
import net.florial.Florial;
import net.florial.database.FlorialDatabase;
import net.florial.models.DiscordUser;
import org.bukkit.Bukkit;

@CommandInfo(
        name = "syncdb",
        requirements = {}
)
public class DiscordUpdateDBCommand extends SlashCommand {

    public DiscordUpdateDBCommand() {
        this.name = "syncdb";
        this.help = "Don't worry about it";
    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        slashCommandEvent.deferReply().queue();
        if (!slashCommandEvent.getUser().getId().equals("366301720109776899")) {
            slashCommandEvent.reply("Don't worry about it").setEphemeral(true).queue();
            return;
        }

        Bukkit.getScheduler().runTaskAsynchronously(Florial.getInstance(), bukkitTask -> {
            Florial.getDiscordServer().loadMembers().onSuccess(members -> {
                for (Member member1 : members) {

                    if (member1.getUser().isBot()) continue;

                    if (FlorialDatabase.getDatastore().find(DiscordUser.class).filter(Filters.eq("uuid", member1.getId())).stream().findAny().isPresent())
                        continue;

                    new DiscordUser(member1.getId()).createNew();
                }
            });
            slashCommandEvent.reply("Thing has been worried about").queue();
        });


    }
}
