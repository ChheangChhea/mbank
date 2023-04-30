package co.istad.mbanking.api.accountType;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountTypeMapper {
    @SelectProvider(type = AccountTypeProvider.class,method = "buildSelectSQL")
//    @Select("SELECT *FROM account_type")
    List<AccountType> select();
}