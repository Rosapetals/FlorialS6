package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.florial.utils.general.CC;
import org.bukkit.entity.Player;

public class VoteCommand extends BaseCommand {

    @CommandAlias("vote")
    public void voteCommand(Player p) {
        p.sendMessage(CC.translate("#ff3c55⚫ #ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤ #ff3c55&l&nVoting #FFCAD8⚫ "));
        p.sendMessage(CC.translate("#ff3c55⚫#ffb8c1 https://minezone.live/server/florial/ "));
        p.sendMessage(CC.translate("#ff3c55⚫#ffb8c1 https://serverlist101.com/server/2823/vote/ "));
        p.sendMessage(CC.translate("#ff3c55⚫#ffb8c1 https://minelist.net/server/3968 "));
        p.sendMessage(CC.translate("#ff3c55&l&nVote#ffb8c1 and earn a Florie + 2k for each site you vote for!"));

    }



}
