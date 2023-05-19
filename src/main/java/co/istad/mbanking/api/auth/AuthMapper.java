package co.istad.mbanking.api.auth;

import co.istad.mbanking.api.user.Role;
import co.istad.mbanking.api.user.web.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Mapper
@Repository
public interface AuthMapper {
    @InsertProvider(type = AuthProvider.class, method = "buildRegisterSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean register(@Param("u") User user);

    @InsertProvider(type = AuthProvider.class, method = "buildCreateUserRoleSql")
    void createUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);


    @Select("SELECT * FROM users WHERE email = #{email} AND is_deleted = FALSE")
    @Results(id = "authResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "studentCardId", column = "student_card_id"),
            @Result(property = "isStudent", column = "is_student"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "verifiedCode", column = "verified_code"),
            @Result(column = "id",property = "roles",
            many = @Many(select = "loadUserRoles"))
    })
    Optional<User> selectByEmail(@Param("email") String email);


    @SelectProvider(value = AuthProvider.class, method = "buildLoadUserRolesSql")
    List<Role> loadUserRoles(Integer userId);

    @SelectProvider(type = AuthProvider.class, method = "buildSelectByEmailAndVerifiedCodeSql")
    @ResultMap("authResultMap")
    Optional<User> selectByEmailAndVerifiedCode(@Param("email") String email, @Param("verifiedCode") String verifiedCode);


    @UpdateProvider(type = AuthProvider.class, method = "buildUpdateVerifiedCodeSQL")
    boolean updateVerifiedCode(@Param("email") String email, @Param("verifiedCode") String verifiedCode);

    @Select("SELECT * FROM users WHERE email = #{email} AND is_deleted = FALSE")
    @ResultMap("authResultMap")
    Optional<User> loadUsrByUsername(@Param("email") String email);

}
