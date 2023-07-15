package net.florial.commands.species;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class ResetSpeciesCommand extends BaseCommand {

    @CommandAlias("resetspecies")
    public void onInfoPanel(Player p) {

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        if (data.getDna() >= 25) {

            data.setSpecieId(0);
            data.setDna(data.getDna() - 25);
            data.getSkills().replaceAll((s, v) -> 0);

            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 1, 1);

            if (DisguiseAPI.getDisguise(p) == null) return;

            MobDisguise mobDisguise = (MobDisguise) DisguiseAPI.getDisguise(p);

            mobDisguise.stopDisguise();

            for (PotionEffect effect : p.getActivePotionEffects()) {p.removePotionEffect(effect.getType());}

        } else {
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You need at least 25 DNA for this!"));
        }

    }
}
