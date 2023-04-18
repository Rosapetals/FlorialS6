package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import me.santio.utils.bukkit.impl.MessageUtils;
import me.santio.utils.minecraft.message.Message;
import net.florial.Florial;
import net.florial.database.FlorialDatabase;
import net.florial.models.ChequeData;
import net.florial.models.DiscordUser;
import net.florial.models.PlayerData;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CashoutCommand extends BaseCommand {

    @CommandAlias("cashout")
    @CommandPermission("florial.cashout")
    public void onCashOut(Player player) {
        PlayerData playerData = FlorialDatabase.getCachedOrDBPlayerData(player).join();
        if (playerData.getDiscordId().isEmpty()) {
            new Message("&c&lLink your discord account with /link first");
            return;
        }
        DiscordUser discordUser = FlorialDatabase.getDiscordUserData(playerData.getDiscordId()).join();
        ChequeData cheque = FlorialDatabase.getChequeData(player.getUniqueId()).join();
        playerData.setFlories(playerData.getFlories() + cheque.getFlories());
        discordUser.setCoins(discordUser.getCoins() + cheque.getCoins());
        discordUser.save(true);
        playerData.save(true);
        EconomyResponse payment = Florial.getInstance().getEconomy().depositPlayer(player, cheque.getCash());

        if (!payment.transactionSuccess()) {
            MessageUtils.sendMessage(player, new Message("&cThere was a problem paying the money into your account, please contact an administrator with the following information: " + cheque.getCash()));
        }

    }

}
