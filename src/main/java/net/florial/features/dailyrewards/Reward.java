package net.florial.features.dailyrewards;

import lombok.Getter;
import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import net.florial.utils.general.VaultHandler;
import org.bukkit.entity.Player;

import java.time.*;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.Locale;

public enum Reward {

    REWARD1(1, 1, 2, 1000),
    REWARD2(2, 1, 3, 5000),
    REWARD3(3, 2, 4, 5000),
    REWARD4(4, 2, 5, 6000),
    REWARD5(5, 2, 6, 7000),
    REWARD6(6, 2, 7, 8000),
    REWARD7(7, 2, 8, 9000);

    @Getter
    private final int id;

    @Getter private final int flories;

    @Getter private final int DNA;
    @Getter private final int money;

    Reward(int id, int flories, int DNA, int money) {
        this.id = id;
        this.flories = flories;
        this.DNA = DNA;
        this.money = money;
    }

    public static Reward fromID(int id) {
        for (Reward e : values())
            if (e.id == id) return e;

        return null;
    }

    public static void checkWeekStatus(Player p) {

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        ZonedDateTime currentLoginDate = LocalDate.now().atStartOfDay(ZoneId.of("America/Chicago"));

        ZonedDateTime lastLoginDate = data.getLastLoggedIn().atStartOfDay(ZoneId.of("America/Chicago"));

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int lastLoginWeek = lastLoginDate.get(weekFields.weekOfWeekBasedYear());
        int currentLoginWeek = currentLoginDate.get(weekFields.weekOfWeekBasedYear());

        if (currentLoginWeek > lastLoginWeek || currentLoginWeek == 1 && lastLoginWeek == 53) {

            data.getLoggedInDays().replaceAll((key, value) -> false);

            System.out.println("Did it");

        }

    }

    public static void give(Weekday day, Player p) {

        ZoneId cstZoneId = ZoneId.of("America/Chicago");
        ZonedDateTime currentDateTime = ZonedDateTime.now(cstZoneId);

        DayOfWeek dayOfWeek = currentDateTime.getDayOfWeek();

        p.closeInventory();

        if (!(dayOfWeek.name().contains(day.name()))) {

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c It is not " + day.name() + " in the CST timezone. It is currently " + dayOfWeek.name() + " in the CST timezone. Hours until next day in the CST timezone: " + hoursUntilNextDayCST(currentDateTime)));
            return;

        }

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        HashMap<Weekday, Boolean> playersWeek = data.getLoggedInDays();

        if (playersWeek.get(day)) {

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You have already gotten a reward for this day."));
            return;

        }

        playersWeek.put(day, true);

        int completedDays = Math.toIntExact(playersWeek.values().stream().filter(Boolean::valueOf).count());

        Reward reward = fromID(completedDays);

        if (reward != null) {

            int flories = reward.getFlories();
            int dna = reward.getDNA();
            int money = reward.getMoney();

            data.setFlories(data.getFlories() + flories);
            data.setFlories(data.getFlories() + dna);
            VaultHandler.addMoney(p, money);

            data.getLoggedInDays().put(Weekday.valueOf(dayOfWeek.name().toUpperCase()), true);

            long hoursUntilNextDay = hoursUntilNextDayCST(currentDateTime);

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f You have been given your rewards! Check back the next day based on the Central Standard Timezone. Hours until next day: #ff3c55" + hoursUntilNextDay + "&f. Rewards: #ff3c55" + flories + "&f Flories, #ff3c55" + dna + "&f DNA, " + "#ff3c55 $" + money));

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c&l Login multiple days in a row for better rewards!"));


        } else {
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c There was an error giving you your reward. Forward this code via bugreport from /bugreport: null" + completedDays));

        }
    }

    public static long hoursUntilNextDayCST(ZonedDateTime currentDateTime) {

        ZonedDateTime nextDayDateTime = currentDateTime.plusDays(1).with(LocalTime.MIDNIGHT);
        Duration durationUntilNextDay = Duration.between(currentDateTime, nextDayDateTime);

        return durationUntilNextDay.toHours();

    }
}
