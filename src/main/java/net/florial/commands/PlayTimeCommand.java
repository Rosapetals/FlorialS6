package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import net.florial.Florial;
import net.florial.utils.general.CC;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

@CommandAlias("playtime")
public class PlayTimeCommand extends BaseCommand {

    private static final Florial florial = Florial.getInstance();


    @Default
    public static void onGetPlaytime(Player player, @Optional @Flags("other") Player arg1) {

        Player target = (arg1 == null) ? player : arg1;

        int playtime = target.getStatistic(Statistic.PLAY_ONE_MINUTE);
        int seconds = playtime / 20;
        int minutes = seconds / 60;
        int hours = minutes / 60;

        player.sendMessage((CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f " + target.getName() + "'s &f playtime: #ff5b70" + hours + "&f hours")));




    }
}
