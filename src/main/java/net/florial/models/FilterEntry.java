package net.florial.models;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import lombok.Data;
import net.florial.database.FlorialDatabase;
import org.bson.types.ObjectId;

@Data
@Entity("filters")
public class FilterEntry {

    @Id
    private ObjectId id;

    private String reference;
    private FilterType type;
    private String entry;

    public void save() {
        FlorialDatabase.getDatastore().save(this);
    }
}
