package net.florial.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.florial.Florial;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StaffChatCommand extends BaseCommand {

    @CommandAlias("staffchat|sc")
    public void staffChat(Player player, String msg) {
        String format = Florial.getInstance().getConfig().getString("general.staffChatFormat");

        format = format.replaceAll("%player%", player.getName()).replaceAll("%message%", msg);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("staff")) p.sendMessage(MiniMessage.miniMessage().deserialize(format));
        }
    }

}
