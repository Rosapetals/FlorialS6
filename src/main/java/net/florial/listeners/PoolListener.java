package net.florial.listeners;

import de.netzkronehd.wgregionevents.events.RegionEnteredEvent;
import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.utils.Cooldown;
import net.florial.utils.general.VaultHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;

public class PoolListener implements Listener {


    @EventHandler
    public void onRegionEntered(RegionEnteredEvent e) {

        if (!(e.getRegion().getId().contains("afk"))) return;

        Player p = e.getPlayer();
        if (p.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE)) p.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);

        if (Cooldown.isOnCooldown("c3", p)) return;

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        int bonus = p.hasPermission("pearlite") ? 3 : p.hasPermission("flourite") ? 2 : 1;

        VaultHandler.addMoney(p, 100*bonus);
        data.setDna(data.getDna()+bonus);
        if (data.getSpecieId() == 10 || data.getSpecieId() == 17) p.giveExp(1000);

        Cooldown.addCooldown("c3", p, 120);



    }

}