package net.florial.commands.cheats;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.TargetedDisguise;
import net.florial.utils.general.CC;
import org.bukkit.entity.Player;

public class ViewPlayerCommand extends BaseCommand {


    @CommandAlias("adminview")
    @Default
    public void disableDisguiseForMe(Player p, @Optional @Flags("other") Player arg1) {

        if (DisguiseAPI.getDisguise(arg1) == null) {
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c This player is not disguised."));
            return;
        }

        if (((TargetedDisguise) DisguiseAPI.getDisguise(arg1)).canSee(p)) {

            ((TargetedDisguise) DisguiseAPI.getDisguise(arg1)).addPlayer(p);
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f You should be able to see them now. Do the command again to see them again."));

        } else {

            ((TargetedDisguise) DisguiseAPI.getDisguise(arg1)).removePlayer(p);
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f You should be able to see the disguise now. Do the command again to hide it again."));
        }


    }

}
