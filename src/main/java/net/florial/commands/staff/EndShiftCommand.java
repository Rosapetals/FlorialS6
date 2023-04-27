package net.florial.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import net.dv8tion.jda.api.EmbedBuilder;
import net.florial.Florial;
import net.florial.database.FlorialDatabase;
import net.florial.models.ChequeData;
import net.florial.models.PlayerData;
import net.florial.models.ShiftData;
import net.florial.utils.Message;
import net.florial.utils.general.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class EndShiftCommand extends BaseCommand {

    @CommandAlias("endshift")
    @CommandPermission("florial.staff")
    public void onShiftEnd(Player player) {

        if (!Florial.getStaffWithShifts().containsKey(player.getUniqueId())) {
            player.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff5b70&l➤&c You do not have a shift running"));
            return;
        }

        ShiftData data = Florial.getStaffWithShifts().get(player.getUniqueId());


        int minutes = Math.toIntExact(TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - data.getShiftStart()));

        if (Math.abs(minutes) < 45) {

            player.sendMessage(CC.translate(("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff5b70&l➤&c Your shift must have at least 45 minutes on it to end it. Do /checkshift to see how much time you currently have.")));
            return;
        }

        Florial.getDiscordServer().getTextChannelById(Florial.getInstance().getConfig().getString("discord.shiftChannel")).sendMessage("").setEmbeds(new EmbedBuilder()
                        .setTitle(player.getName() + "'s shift")
                        .addField("Shift duration", formatTimeSpan(System.currentTimeMillis() - data.getShiftStart()), true)
                        .addField("Players when started", String.valueOf(data.getPlayersOnStart()), true)
                        .addField("Highest player count", String.valueOf(data.getHighestPlayerCount()), true)
                        .addField("Players on shift end", String.valueOf(Bukkit.getOnlinePlayers().size()), true)
                        .setImage("https://crafatar.com/avatars/" + player.getUniqueId())
                        .setColor(Color.PINK)
                .build()).queue();

        Florial.getStaffWithShifts().remove(player.getUniqueId());
     //   ChequeData cheque = FlorialDatabase.getChequeData(player.getUniqueId()).join();
     //   PlayerData playerData = FlorialDatabase.getCachedOrDBPlayerData(player.getUniqueId()).join();
     //   if (cheque == null) cheque = new ChequeData(player.getUniqueId().toString(), true);
      //  cheque.setDiscordId(playerData.getDiscordId());
      //  cheque.setCash(cheque.getCash() + 10000);
      //  cheque.setCoins(cheque.getCoins() + 5);
      //  cheque.setFlories(cheque.getFlories() + 5);
     //   cheque.save(true);

        player.sendMessage(CC.translate(("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff5b70&l➤&f Your shift has been registered and the funds have been added to your cheque accordingly. Please use /cashout to claim your cheque")));
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
