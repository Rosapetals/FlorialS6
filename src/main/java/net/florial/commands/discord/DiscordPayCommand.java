package net.florial.commands.discord;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.florial.database.FlorialDatabase;
import net.florial.models.DiscordUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CommandInfo(
        name = "pay",
        requirements = {}
)
public class DiscordPayCommand extends SlashCommand {

    public DiscordPayCommand() {
        this.name = "pay";
        this.help = "Time a user out for a given length of time";
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.USER, "user", "The user to pay").setRequired(true));
        options.add(new OptionData(OptionType.INTEGER, "amount", "The amount to be paid").setRequired(true));
        this.options = options;
    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        Member member = slashCommandEvent.getMember();

        if (member == null) {
            slashCommandEvent.reply("There was an error trying to perform this command").queue();
            return;
        }

        Member payee = slashCommandEvent.getOption("user").getAsMember();
        if (payee == null) {
            slashCommandEvent.reply("This user does not exist").queue();
            return;
        }

        FlorialDatabase.getDiscordUserData(slashCommandEvent.getUser().getId()).thenAccept(discordUser -> {
            if (discordUser == null) {
                System.out.println("it was null");
                new DiscordUser(slashCommandEvent.getUser().getId()).createNew();
            } else {

                int price = Objects.requireNonNull(slashCommandEvent.getOption("amount")).getAsInt();

                if (discordUser.getCoins() >= price) {

                    discordUser.setCoins(discordUser.getCoins() - price);
                    FlorialDatabase.getDiscordUserData(payee.getId()).thenAccept(discordUserTarget -> {

                            discordUserTarget.setCoins(discordUserTarget.getCoins() + price);
                            discordUserTarget.save(true);
                    });
                    discordUser.save(true);

                    slashCommandEvent.reply("Successfully paid <:florialcoin:1108293880971014184> " + price + " coins to " + payee.getEffectiveName() + " UwU").setEphemeral(true).queue();


                } else {
                    slashCommandEvent.reply("You don't have enough <:florialcoin:1108293880971014184> coins to pay that person. >w<").setEphemeral(true).queue();
                }

            }
        });

    }
}

