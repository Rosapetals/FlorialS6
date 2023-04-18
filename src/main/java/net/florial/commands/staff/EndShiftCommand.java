package net.florial.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import net.dv8tion.jda.api.EmbedBuilder;
import net.florial.Florial;
import net.florial.models.ShiftData;
import net.florial.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EndShiftCommand extends BaseCommand {

    @CommandAlias("endshift")
    @CommandPermission("florial.staff")
    public void onShiftEnd(Player player) {
        if (!Florial.getStaffWithShifts().containsKey(player.getUniqueId())) {
            new Message("&cYou do not have a shift running").send(player);
            return;
        }
        ShiftData data = Florial.getStaffWithShifts().get(player.getUniqueId());
        Florial.getDiscordServer().getTextChannelById(Florial.getInstance().getConfig().getString("discord.shiftChannel")).sendMessage("").setEmbeds(new EmbedBuilder()
                        .setTitle(player.getName() + "'s shift")
                        .addField("Shift duration", formatTimeSpan(System.currentTimeMillis() - data.getShiftStart()), true)
                        .addField("Players when started", String.valueOf(data.getPlayersOnStart()), true)
                        .addField("Highest player count", String.valueOf(data.getHighestPlayerCount()), true)
                        .addField("Players on shift end", String.valueOf(Bukkit.getOnlinePlayers().size()), true)
                .build()).queue();
        Florial.getStaffWithShifts().remove(player.getUniqueId());
    }

    private static String formatTimeSpan(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        seconds = seconds % 60;
        minutes = minutes % 60;

        String formattedTimeSpan = "";
        if (hours > 0) {
            formattedTimeSpan += hours + " hours, ";
        }
        if (minutes > 0) {
            formattedTimeSpan += minutes + " minutes and ";
        }
        if (seconds > 0 || formattedTimeSpan.isEmpty()) {
            formattedTimeSpan += seconds + " seconds";
        }

        return formattedTimeSpan.trim();
    }

}
