package net.florial.species.events.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.florial.species.events.SpeciesEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpeciesTablistEvent extends SpeciesEvent implements Cancellable {

    @Setter
    boolean cancelled = false;
    final Player player;

    final Boolean birth;
    public SpeciesTablistEvent(Player player, Boolean birth) {

        this.player = player;
        this.birth = birth;
    }

}
