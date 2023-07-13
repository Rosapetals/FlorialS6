package net.florial.commands.cheats;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import net.florial.Florial;
import net.florial.models.PlayerData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetEventCommand extends BaseCommand {

    @CommandAlias("changeevent")
    @CommandPermission("op")
    public void changeDnaCommand(CommandSender p, @Optional @Flags("other") Player target, int a) {

        if (target == null) return;

        PlayerData data = Florial.getPlayerData().get(target.getUniqueId());

        data.setEvent(a);

        if (p instanceof Player) p.sendMessage("succesful");
    }
}