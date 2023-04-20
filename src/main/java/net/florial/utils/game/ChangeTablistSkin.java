package net.florial.utils.game;

import net.pinger.disguise.DisguiseAPI;
import net.pinger.disguise.packet.PacketProvider;
import org.bukkit.entity.Player;

public class ChangeTablistSkin {

    public void activate(Player player, String skinUrl) {

        PacketProvider provider = DisguiseAPI.getProvider();

        DisguiseAPI.getSkinManager().getFromImage(skinUrl, response -> {
            if (!response.success()) {
                return;
            }
        provider.updateProperties(player, response.get());
            provider.sendServerPackets(player);});
    }

}
