package co.istad.mbanking.api.accountType;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeMapStruct;

    @Override
    public List<AccountTypeDto> findAll() {
        List<AccountType> accountTypes=accountTypeMapper.select();
//        List<AccountTypeDto>accountTypeDtoList=accountTypes.stream()
//                .map(accountType -> new AccountTypeDto(accountType.getName())).toList();

        return accountTypeMapStruct.toDo(accountTypes);
    }
//    @Override
//    public AccountType insert(AccountTypeDto accountDto) {
//    accountTypeMapper.insert(accountDto);
//        return null;
//    }

    @Override
    public Integer deleted(Integer id) {
      accountTypeMapper.delete(id);
        return id;
    }




    @Override
    public AccountTypeDto findById(Integer id) {
        AccountType accountType = accountTypeMapper.selectById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Account type with %d is not found", id)));
        return accountTypeMapStruct.toAccountTypeDto(accountType);
    }


}
