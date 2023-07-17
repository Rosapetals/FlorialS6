package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;

import java.util.List;

@CommandInfo(name = "role")
public class DiscordRoleCommand extends SlashCommand {

    public DiscordRoleCommand() {
        this.name = "role";
        this.guildOnly = true;
        this.options = List.of(

        );
    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

    }
}
