package net.florial.commands.ranks;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import lombok.Getter;
import net.florial.Florial;
import net.florial.species.SpecieType;
import net.florial.species.Species;
import net.florial.utils.general.CC;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

public class SwitchNickNameCommand extends BaseCommand {

    @Getter
    private static final HashMap<UUID, String> nickSwitch = new HashMap<>();

    @CommandAlias("switchnickname")
    public void switchSpecies(Player p, @Optional @Flags("other") Player arg1, @Optional String nick) {

        if (arg1 == null) return;
        if (nick == null) return;

        UUID u2 = arg1.getUniqueId();
        UUID u = p.getUniqueId();

        if (!(nick.contains("accept") && (!nick.contains("deny")))) {

            if (!(p.hasPermission("pearlite"))) return;

            nickSwitch.put(u2, nick);

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully sent a request to #FF9AB6" + arg1.getName() + ",&f if they accept, their nickname will be switched to #FF9AB6" + nick + ",&f you won't be notified if they accept."));
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f A nickname change request was sent to you by #FF9AB6" + p.getName() + ",&f do #FF9AB6/switchnickname " + arg1.getName() + " accept&f to switch your nickname to #FF9AB6" + nickSwitch.get(u2) + "&f for free. Or do #FF9AB6/switchnickname " + arg1.getName() + " deny"));

        } else if (nick.contains("accept")) {

            if (nickSwitch.get(u) == null) return;

            Florial.getInstance().ess.getUser(p).setNickname(CC.translate(nick));

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Your nickname has been successfully changed to #FF9AB6" + nickSwitch.get(u) + "!"));

            nickSwitch.remove(u);

        } else if (nick.contains(("deny"))) {

            if (nickSwitch.get(u) == null) return;


            nickSwitch.remove(u);
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully cancelled!"));
        }
    }
}