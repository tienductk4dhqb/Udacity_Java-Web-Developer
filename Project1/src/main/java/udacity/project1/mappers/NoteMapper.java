package udacity.project1.mappers;

import udacity.project1.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES")
    List<Note> getAll();

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getAllByUserId(int userId);

    @Insert("INSERT INTO NOTES (title, description, userid) " +
            "VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Select("SELECT * FROM NOTES WHERE id = #{noteId}")
    Note findById(int noteId);

    @Delete("DELETE FROM NOTES WHERE id=#{noteId}")
    int deleteById(int noteId);

    @Update("UPDATE NOTES set title=#{noteTitle}, description=#{noteDescription} where id=#{noteId}")
    int update(Note note);
}
