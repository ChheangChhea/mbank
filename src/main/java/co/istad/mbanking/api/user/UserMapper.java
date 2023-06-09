package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UserMapper {
    @InsertProvider(type = UserProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("u") User user);


    @SelectProvider(type = UserProvider.class, method = "buildSelectByIdSql")
    @Results(id = "userResultMap", value = {@Result(column = "student_card_id", property = "studentCardId"), @Result(column = "is_student", property = "isStudent")})
    Optional<User> selectById(@Param("id") Integer id);

    @SelectProvider(type = UserProvider.class, method = "buildSelectByStudentCardIdSql")
    @ResultMap("userResultMap")
    Optional<User> selectByStudentCardId(@Param("studentCardId") String studentCardId);

    @Select("SELECT EXISTS(SELECT * FROM users WHERE id =#{id})")
    boolean existsById(@Param("id") Integer id);

    @SelectProvider(value = UserProvider.class, method = "buildSelectSql")
    @ResultMap(("userResultMap"))
    List<User> select(@Param("name") String name);

    @DeleteProvider(type = UserProvider.class, method = "buildDeleteByIdSql")
    void deleteById(@Param("id") Integer id);


    @UpdateProvider(type = UserProvider.class, method = "isUpdateIsDeleteUserById")
    boolean updateIsDeletedById(@Param("id") Integer id, @Param("status") boolean status);

//===================== Security ================================
    @UpdateProvider(type = UserProvider.class, method = "buildUpdateByIdSql")
    void updateById(@Param("u") User user);

    @Select("SELECT EXISTS(SELECT * FROM users WHERE email = #{email})")
    boolean existByEmail(@Param("email") String email);

    @Select("SELECT EXISTS(SELECT * FROM roles WHERE id = #{roleId})")
    boolean checkRoleId(@Param("roleId") Integer roleId);

    //=====================================================

}
