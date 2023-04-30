package co.istad.mbanking.api.accountType;

import java.util.List;

public interface AccountTypeService {

    List<AccountTypeDto> findAll();

    AccountType insert(AccountTypeDto accountTypeDto);

//    AccountTypeDto delete(AccountTypeDto accountTypeDto);
     Integer deleted(Integer id);
}
