package udacity.project1.services;

import udacity.project1.mappers.NoteMapper;
import udacity.project1.models.Note;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class NoteService {
    private final NoteMapper noteMapper;

    public void upsert(Note note) {
        if (Objects.isNull(note.getNoteId())) {
            noteMapper.insert(note);
        } else {
            noteMapper.update(note);
        }
    }

    public Note get(int id) {
        return noteMapper.findById(id);
    }

    public List<Note> getAllByUser(int userId) {
        return noteMapper.getAllByUserId(userId);
    }

    public boolean deleteById(int id) {
        return noteMapper.deleteById(id) == 1;
    }
}
