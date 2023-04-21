package net.florial.commands.menu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.florial.features.skills.Skills;
import org.bukkit.entity.Player;

public class SkillsMenuCommand extends BaseCommand {

    Skills skillsMenu = new Skills();

    @CommandAlias("skills")
    @Default
    public void onOpenSkills(Player player) {skillsMenu.skillMenu(player);}
}