package net.florial.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.florial.Florial;
import net.florial.utils.GeneralUtils;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StaffChatCommand extends BaseCommand {

    @CommandAlias("staffchat|sc")
    @CommandPermission("staff")

    public void staffChat(Player player, String msg) {
        String format = Florial.getInstance().getConfig().getString("general.staffChatFormat");

        format = format.replaceAll("%player%", player.getName()).replaceAll("%message%", msg);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("staff")) p.sendMessage(MiniMessage.miniMessage().deserialize(format));
        }

        GeneralUtils.runAsync(new BukkitRunnable() {
            @Override
            public void run() {
                TextChannel chatLogChannel = Florial.getDiscordServer().getTextChannelById("803862530438332417");
                chatLogChannel.sendMessage("[STAFF CHAT] " + player.getName() + ": " + msg).queue();
            }
        });

    }

}
