package co.istad.mbanking.api.accountType;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountTypeMapper {
    @SelectProvider(type = AccountTypeProvider.class, method ="buildSelectSQL")
//    @Select("SELECT *FROM account_type")
    List<AccountType> select();


    @InsertProvider(type = AccountTypeProvider.class,method = "buildInsertSql")

    @Result (column = "student_card_id", property = "studentCardId")
    @Result(column = "is_student",property = "isStudent")
    void insert(@Param("n") AccountTypeDto accountTypeDto);


    @DeleteProvider(type = AccountTypeProvider.class,method = "buildDeleteAccountType")
    void delete(@Param("id")Integer id);
}
