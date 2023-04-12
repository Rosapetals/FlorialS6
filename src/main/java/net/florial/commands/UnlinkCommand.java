package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.florial.Florial;
import net.florial.database.FlorialDatabase;
import net.florial.utils.GeneralUtils;
import org.bukkit.entity.Player;

public class UnlinkCommand extends BaseCommand {

    @CommandAlias("unlink")
    public void onUnlink(Player player) {
        GeneralUtils.runAsync(() -> {
            FlorialDatabase.getCachedOrDBPlayerData(player).thenAccept((data) -> {
                if (data.getDiscordId().equals("") && !Florial.getLinkCodes().containsValue(player.getUniqueId())) {
                    player.sendMessage("§cYou are not linked to a discord account!");
                } else if (!data.getDiscordId().equals("")) {
                    data.setDiscordId("");
                    data.save(true);
                    player.sendMessage("§7You have §c§nunlinked§7 your discord account!");
                } else if (Florial.getLinkCodes().containsValue(player.getUniqueId())) {
                    String code = Florial.getLinkCodes().keySet()
                            .stream()
                            .filter(key -> Florial.getLinkCodes().get(key).equals(player.getUniqueId()))
                            .findFirst()
                            .orElse(null);

                    Florial.getLinkCodes().remove(code);
                    player.sendMessage("§7You have §c§ncancelled§7 your linking process!");
                }
            });
        });
    }

}
