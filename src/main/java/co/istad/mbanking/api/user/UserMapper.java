package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.User;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
         @InsertProvider(type = UserProvider.class,method = "buildInsertSql")
    void insert(@Param("u") User user);
}
