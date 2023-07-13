package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import net.florial.Florial;
import net.florial.features.duels.Duel;
import net.florial.menus.DuelMenu;
import net.florial.utils.general.CC;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DuelCommand extends BaseCommand {

    private static final DuelMenu duelMenu = new DuelMenu();

    @CommandAlias("duel")
    @Default
    public static void duelCommandInteraction(Player player, String arg1, @Optional @Flags("other") Player arg2) {

        UUID u = player.getUniqueId();

        switch(arg1) {

            case "invite" -> {

                if (!(arg2.isEmpty())) {

                    if (Florial.getOngoingDuel().get(u) != null) {
                        player.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You already have an ongoing invitation. Cancel it with /duel cancel"));
                        return;
                    }

                    Florial.getOngoingDuel().put(u, new Duel(u, arg2.getUniqueId(), false, null, null));
                    Florial.getOngoingDuel().put(arg2.getUniqueId(), Florial.getOngoingDuel().get(u));

                    duelMenu.activate(player);

                } else {
                    player.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c That player doesn't exist or isn't online."));

                }

            }
            case "cancel" -> {

                Duel duel = Florial.getOngoingDuel().get(u);

                if (duel == null) return;

                duel.delete();

                player.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully cancelled."));

            }
            case "deny", "accept" -> {

                Duel duel = Florial.getOngoingDuel().get(u);

                if (duel == null || duel.getOpponentWhoWasInvited() != u) {
                    return;
                }

                if (!(duel.passNullCheck())) {
                    return;
                }

                Player opponent = Bukkit.getPlayer(duel.getOpponentWhoInvited());

                if (arg1.contains("accept")) {

                    player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 2, 1);
                    opponent.playSound(opponent.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 2, 1);

                } else {
                    opponent.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Your duel request has been denied by #ff3c55" + player.getName()));
                    player.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully denied the invite."));
                }


            }
        }

    }



}
