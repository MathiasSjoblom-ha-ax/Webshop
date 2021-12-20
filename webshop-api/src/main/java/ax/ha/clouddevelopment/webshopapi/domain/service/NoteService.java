
package ax.ha.clouddevelopment.webshopapi.domain.service;

import ax.ha.clouddevelopment.webshopapi.domain.model.Note;

import java.util.List;
import java.util.UUID;

/**
 * Interface of a webshop service managing Notes and advertisements
 *
 * @author Dag Karlsson <Dag.Karlsson@crosskey.fi>
 */
public interface NoteService {

    List<Note> getNotes();

    Note getNote(UUID noteId);

    Note addNote(Note note);

    Note updateNote(Note note);

    void deleteNote(UUID noteId);
}