
package ax.ha.clouddevelopment.webshopapi.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

/**
 * Note domain model
 *
 * @author Dag Karlsson <Dag.Karlsson@crosskey.fi>
 */
@Data // These two annotations actually generates the constructors, equal & builder methods aswell as setter and getters,
@Builder // But depending on used IDE you might not have support for annotation processing.
public class Note {
    private UUID id;
    private String name;
    private String description;

    public Note() {
    }

    public Note(final UUID id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static NoteBuilder builder() {
        return new NoteBuilder();
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public static class NoteBuilder {
        private UUID id;
        private String name;
        private String description;

        NoteBuilder() {
        }

        public NoteBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        public NoteBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public NoteBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public Note build() {
            return new Note(this.id, this.name, this.description);
        }
    }
}