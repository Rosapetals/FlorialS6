package net.florial.commands.ranks;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import org.bukkit.entity.Player;

public class ProfileCommand extends BaseCommand {


    @CommandAlias("profile")
    @Default
    public void openProfile(Player p, @Optional @Flags("other") Player arg1) {

        Player target = (arg1 == null) ? p : arg1;

        PlayerData data = Florial.getPlayerData().get(target.getUniqueId());

        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r"));
        p.sendMessage(CC.translate("#ff3c55&l➤ Species: &f " + data.getSpecies().getName()));
        p.sendMessage(CC.translate("#ff3c55&l➤ Age: &f " + data.getAge()));
        p.sendMessage(CC.translate("#ff3c55&l➤ DNA: &f " + data.getDna()));

    }
}