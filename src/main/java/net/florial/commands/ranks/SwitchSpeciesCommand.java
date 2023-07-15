package net.florial.commands.ranks;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import lombok.Getter;
import net.florial.species.SpecieType;
import net.florial.species.Species;
import net.florial.utils.general.CC;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

public class SwitchSpeciesCommand extends BaseCommand {

    @Getter
    private static final HashMap<UUID, Species> speciesSwitch = new HashMap<>();

    @CommandAlias("switchspecies")
    public void switchSpecies(Player p, @Optional @Flags("other") Player arg1, @Optional String species) {

        if (arg1 == null) return;
        if (species == null) return;

        UUID u2 = arg1.getUniqueId();
        UUID u = p.getUniqueId();

        if (!(species.contains("accept") && (!species.contains("deny")))) {

            if (!(p.hasPermission("flourite")
                || species.contains("pearlite"))) return;

            SpecieType type;

            try { type = SpecieType.valueOf(species.toUpperCase().replace(" ", "_")); }
            catch (Exception e){
                p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c Invalid species, species are: " + Arrays.stream(SpecieType.values()).map(Enum::name).collect(Collectors.joining(", "))));
                return;
            }

            speciesSwitch.put(u2, type.getSpecie());

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully sent a request to #FF9AB6" + arg1.getName() + ",&f if they accept, their species will be switched to #FF9AB6" + species + ",&f you won't be notified if they accept."));
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f A species change request was sent to you by #FF9AB6" + p.getName() + ",&f do #FF9AB6/switchspecies " + arg1.getName() + " accept&f to switch your species to #FF9AB6" + speciesSwitch.get(u2) + "&f for free. Or do #FF9AB6/switchspecies " + arg1.getName() + " deny"));

        } else if (species.contains("accept")) {

            if (speciesSwitch.get(u) == null) return;

            Species.become(p, speciesSwitch.get(u).getName().toUpperCase());

            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Your species has been successfully changed to #FF9AB6" + speciesSwitch.get(u).getName() + "!"));

            speciesSwitch.remove(u);

        } else if (species.contains(("deny"))) {

            if (speciesSwitch.get(u) == null) return;


            speciesSwitch.remove(u);
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully cancelled!"));
        }
    }
}
