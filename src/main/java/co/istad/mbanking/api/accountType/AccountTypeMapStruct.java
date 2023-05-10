package co.istad.mbanking.api.accountType;


import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface AccountTypeMapStruct {

List<AccountTypeDto> toDo(List<AccountType>model);
//    List<AccountTypeDto> toDtoList(List<AccountType> model);
   // AccountTypeDto toDto(AccountType model);



    //===========================
    AccountTypeDto toAccountTypeDto(AccountType model);
}
