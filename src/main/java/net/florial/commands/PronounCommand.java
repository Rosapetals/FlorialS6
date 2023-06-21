package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Optional;
import net.florial.Florial;
import net.florial.features.age.Age;
import net.florial.models.PlayerData;
import net.florial.utils.game.AgeSuffixAdjuster;
import net.florial.utils.general.CC;
import net.florial.utils.general.FilterUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PronounCommand extends BaseCommand {

    @CommandAlias("pronouns")
    public void pronounCommand(Player p, @Optional String s2) {
        if (s2 == null) {
            p.sendMessage((CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Available pronouns#ff3c55&l ⚫")));
            p.sendMessage((CC.translate("#ff3c55&l⚫&f She/her #ff5b70/pronouns " + p.getName() + " she/her")));
            p.sendMessage((CC.translate("#ff3c55&l⚫&f She/they #ff5b70/pronouns " + p.getName() + " she/they")));
            p.sendMessage((CC.translate("#ff3c55&l⚫&f He/him #ff5b70/pronouns " + p.getName() + " he/him")));
            p.sendMessage((CC.translate("#ff3c55&l⚫&f He/they #ff5b70/pronouns " + p.getName() + " he/they")));
            p.sendMessage((CC.translate("#ff3c55&l⚫&f They/them #ff5b70/pronouns " + p.getName() + " they/them")));
            p.sendMessage((CC.translate("#ff3c55&l⚫&f Any pronouns #ff5b70/pronouns " + p.getName() + " any/all")));
            p.sendMessage((CC.translate("#ff3c55&l⚫&f When someone does /profile " + p.getName() + " they'll see your pronouns. In chat, too. #ff3c55&l⚫")));
        } else {

            if (!(s2.contains("/"))) return;
            if (s2.length() > 10) return;
            if (FilterUtils.check(p, s2)) return;

            PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

            data.setPronouns(s2);
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " meta setsuffix \"" + AgeSuffixAdjuster.cache(p) + "\"");
            p.sendMessage((CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully set your pronouns to #ff3c55" + s2
                    + " want to make them invisible and only viewable through /profile? Do /pronouns " + s2 + " invisible")));

            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, (float) 3);

        }
    }
}
