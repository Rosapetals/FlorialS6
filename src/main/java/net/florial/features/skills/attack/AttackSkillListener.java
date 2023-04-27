package net.florial.features.skills.attack;

import net.florial.Florial;
import net.florial.features.skills.Skill;
import net.florial.models.PlayerData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AttackSkillListener implements Listener {

    @EventHandler
    public void attackSkillBoost(EntityDamageByEntityEvent e){

        if (!(e.getDamager() instanceof Player p)) return;

        if (!(e.getEntity()  instanceof LivingEntity)) return;


        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        int attackSkill =  data.getSkills().get(Skill.STRENGTH);

        //broke

        if (attackSkill > 1) e.setDamage(e.getDamage() + attackSkill*2);

    }
}
