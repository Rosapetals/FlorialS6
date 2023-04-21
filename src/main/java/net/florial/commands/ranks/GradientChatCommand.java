package net.florial.commands.ranks;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Optional;
import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GradientChatCommand extends BaseCommand {

    @CommandAlias("gradientchat")
    @CommandPermission("iridiumplus")
    public void ColorCommand(Player p, @Optional String s, @Optional String s2) {

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        if (s == null || (s2 == null)) {
            if (!(s == null) && s.contains("off")) {
                p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully disabled your Color Chat!"));
                data.setGradient1("");
                data.setGradient2("");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 2, (float) 1);

            } else {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 2, (float) 1);
                p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c Correct Usage: /gradient <color1> <color2>"));
                p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c Example: /gradient 111111 FFFFFF"));

            }

        } else {
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully set your Gradient Chat! Starts at " + s + " and fades to " + s2 + " do /colorchat off to disable it!"));
            data.setGradient1(s);
            data.setGradient2(s2);
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 2, (float) 1);

        }
    }


}
