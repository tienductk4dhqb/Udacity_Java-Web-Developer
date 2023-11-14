package udacity.project1.service;

import java.util.List;
import org.springframework.stereotype.Service;

import udacity.project1.mapper.NoteMapper;
import udacity.project1.model.Note;

@Service
public class NoteService {

    private final NoteMapper notes;

    public NoteService(NoteMapper mapper) {
        this.notes = mapper;
    }

    public List<Note> allBy(String UID) {
        return notes.allFrom(UID);
    }

    public void remove(Note note) {
        notes.delete(note);
    }

    public void add(Note note) {
        if (note.getId() == null) {
            notes.insert(note);
            return;
        }

        notes.update(note);
    }

}