package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Flags;
import co.aikar.commands.annotation.Optional;
import net.florial.Florial;
import net.florial.features.skills.Skill;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ProfileCommand extends BaseCommand {


    @CommandAlias("profile")
    @Default
    public void openProfile(Player p, @Optional @Flags("other") Player arg1) {

        Player target = (arg1 == null) ? p : arg1;

        PlayerData data = Florial.getPlayerData().get(target.getUniqueId());
        HashMap<Skill, Integer> skills = data.getSkills();

        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r"));
        p.sendMessage(CC.translate("#ff3c55&l➤ Species: &f " + data.getSpecies().getName()));
        assert data.getPronouns() != null;
        p.sendMessage(CC.translate("#ff3c55&l➤ Pronouns: &f " + (data.getPronouns().isEmpty() ? "None" : data.getPronouns())));
        p.sendMessage(CC.translate("#ff3c55&l➤ Age: &f " + data.getAge()));
        p.sendMessage(CC.translate("#ff3c55&l➤ DNA: &f " + data.getDna()));
        p.sendMessage(CC.translate("#ff3c55&l➤ Flories: &f " + data.getFlories()));
        p.sendMessage(CC.translate("#ff3c55&l➤ Scent: &f " + skills.get(Skill.SCENT)));
        p.sendMessage(CC.translate("#ff3c55&l➤ Resistance: &f " + skills.get(Skill.RESISTANCE)));
        p.sendMessage(CC.translate("#ff3c55&l➤ Strength: &f " + skills.get(Skill.STRENGTH)));
        p.sendMessage(CC.translate("#ff3c55&l➤ Survival: &f " + skills.get(Skill.SURVIVAL)));
        p.sendMessage(CC.translate("#ff3c55&l➤ Specific: &f " + skills.get(Skill.SPECIFIC)));



    }
}