
package ax.ha.clouddevelopment.webshopapi.domain.service;

import ax.ha.clouddevelopment.webshopapi.domain.model.EntityNotFoundException;
import ax.ha.clouddevelopment.webshopapi.domain.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Default implementation of {@link WebshopService}
 *
 * @author Dag Karlsson <Dag.Karlsson@crosskey.fi>
 */
@Service
public class DefaultNoteService implements NoteService {

    private final List<Note> notes;

    public DefaultNoteService() {
        this.notes = new ArrayList<>(Arrays.asList(Note.builder()
                .id(UUID.randomUUID())
                .name("Demo note")
                .description("Hello world!")
                .build()));
    }

    @Override
    public List<Note> getNotes() {
        return this.notes;
    }

    @Override
    public Note getNote(final UUID noteId) {
        return this.notes.stream()
                .filter(n -> n.getId().compareTo(noteId) == 0)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Cant find note with id " + noteId));
    }

    @Override
    public Note addNote(final Note note) {
        notes.add(note);
        return note;
    }

    @Override
    public Note updateNote(final Note note) {
        final Note noteToUpdate = this.getNote(note.getId());
        this.notes.remove(noteToUpdate);
        this.notes.add(note);
        return note;
    }

    @Override
    public void deleteNote(final UUID noteId) {
        final Note note = this.getNote(noteId);
        this.notes.remove(note);
    }
}