package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import net.florial.Florial;
import net.florial.utils.general.CC;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

@CommandAlias("playtime")
public class PlayTimeCommand extends BaseCommand {

    private static final Florial florial = Florial.getInstance();


    @Default
    public static void onGetPlaytime(Player player, @Optional @Flags("other") Player arg1) {

        player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_STEP, 2, 1);


        Player target = (arg1 == null) ? player : arg1;

        int hours = player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 72000;

        player.sendMessage((CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f " + target.getName() + "'s &f playtime: #ff5b70" + hours + "&f hours")));
        player.sendMessage((CC.translate("&6&l* ➤ #c17012D#c27416o #c37819/#c47b1dp#c57f20t#c58324r#c68727e#c78b2be #c88e2et#c99232o #ca9635v#cb9a39i#cc9e3ce#cda240w #cda543y#cea947o#cfad4au#d0b14er #d1b551P#d2b855l#d3bc58a#d4c05cy#d5c45ft#d6c863i#d6cb66m#d7cf6ae #d8d36dR#d9d771e#dadb74w#dbdf78a#dce27br#dde67fd#deea82s #deee86T#dff289r#e0f58de#e1f990e#e2fd94!")));




    }
}
