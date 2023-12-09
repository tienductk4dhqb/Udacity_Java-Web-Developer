package udacity.project1.mappers;

import udacity.project1.models.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> getAll();

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> getAllByUserId(int userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, `key`, password, userid) " +
            "VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential file);

    @Select("SELECT * FROM CREDENTIALS WHERE id = #{credentialId}")
    Credential findById(int credentialId);

    @Delete("DELETE FROM CREDENTIALS WHERE id=#{credentialId}")
    int deleteById(int credentialId);

    @Update("UPDATE CREDENTIALS set url=#{url}, " +
            " username=#{username}, `key`=#{key}, password=#{password}, userid=#{userId} where id=#{credentialId}")
    int update(Credential credential);
}
