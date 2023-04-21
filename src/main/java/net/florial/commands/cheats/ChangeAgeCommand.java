package net.florial.commands.cheats;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import net.florial.Florial;
import net.florial.features.age.Age;
import net.florial.models.PlayerData;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ChangeAgeCommand extends BaseCommand {

    @CommandAlias("changeage")
    @CommandPermission("tester")
    public void onInfoPanel(Player p, String s){

        //test

        Age age;

        try { age = Age.valueOf(s.toUpperCase().replace(" ", "_")); }
        catch (Exception e){
            p.sendMessage("§cInvalid Age, Ages are: " + Arrays.stream(Age.values()).map(Enum::name).collect(Collectors.joining(", ")));
            return;
        }

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        data.setAge(age);

    }
}