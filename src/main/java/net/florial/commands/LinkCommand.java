package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.florial.Florial;
import net.florial.database.FlorialDatabase;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class LinkCommand extends BaseCommand {

    private Random random = new Random();

    @CommandAlias("link")
    public void onCommand(@NotNull Player player) {
        FlorialDatabase.getCachedOrDBPlayerData(player.getUniqueId()).thenAccept(data -> {
            if (!data.getDiscordId().equals("")) {
                player.sendMessage("§cYou are already linked to a discord account!");
                return;
            }

            String code = generateCode(6);
            Florial.getLinkCodes().put(code, player.getUniqueId());
            Component message = Component.text("§7Use ")
                    .append(Component.text("§d§n/link " + code)
                            .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/link " + code))
                            .hoverEvent(Component.text("§aClick to copy!")))
                    .append(Component.text(" §7in the discord to link your account!")).asComponent();

            player.sendMessage(message);
        });
    }

    private String generateCode(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {
            code.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        return code.toString();
    }

}
