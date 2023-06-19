package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ShareDNACommand extends BaseCommand {


    @CommandAlias("sharedna")
    @Default
    public void shareDNACommand(Player p, @Optional @Flags("other") Player arg1, Integer amount) {

        PlayerData data2 = Florial.getPlayerData().get(p.getUniqueId());

        if (arg1 == null || amount == null) {

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c Correct usage: /sharedna <player> <amount>"));


        } else if (data2.getDna() >= amount && amount > 0){

            PlayerData data = Florial.getPlayerData().get(arg1.getUniqueId());

            data.setDna(data.getDna() + amount);
            data2.setDna(data2.getDna() - amount);

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully transferred #ff5b70" + amount + "&f DNA to #ff5b70" + arg1.getName()));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 2);


        } else {
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You do not have " + amount + " DNA to give."));
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 2);

        }

    }
}
