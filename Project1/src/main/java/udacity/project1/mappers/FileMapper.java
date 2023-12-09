package udacity.project1.mappers;

import udacity.project1.models.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES")
    List<File> getAll();

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> getAllByUser(int userId);

    @Insert("INSERT INTO FILES (name, content_type, size, userid, data) " +
            "VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Select("SELECT * FROM FILES WHERE id = #{fileId}")
    File findById(int fileId);

    @Delete("DELETE FROM FILES WHERE id=#{fileId}")
    int deleteById(int fileId);

    @Update("UPDATE FILES set name=#{filename}, " +
            " content_type=#{contentType}, filesize=#{size}, userid=#{userId}, data=#{fileData} where id=#{fileId}")
    int update(File file);

    @Select("SELECT * FROM FILES WHERE name = #{fileName}")
    File findByName(String fileName);
}
