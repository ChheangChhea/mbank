package co.istad.mbanking.api.accountType;

import co.istad.mbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static net.sf.jsqlparser.parser.feature.Feature.delete;

@RestController
@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
public class AccountTypeRestController {

    private final AccountTypeService accountTypeService;
    @GetMapping
    public BaseRest<?> findAll(){
        var accountTypeDtoList = accountTypeService.findAll();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account Type Service")
                .data(accountTypeDtoList)
                .build();
    }
    //=================Add User Type============================
//    @PostMapping("/add")
//    public BaseRest<?>insert(@RequestBody AccountTypeDto accountType){
//        return BaseRest.builder()
//                .status(true)
//                .code(HttpStatus.OK.value())
//                .message("Add successfully")
//                .data(accountTypeService.insert(accountType))
//                .build();
//        }

    @DeleteMapping("/{id}")
    public BaseRest<?>delete(@PathVariable Integer id){
        accountTypeService.deleted(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Add successfully")
                .data(id)
                .build();
    }




    @GetMapping("/{id}")
    public BaseRest<?> findById(@PathVariable Integer id) {
        var accountType = accountTypeService.findById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account type has been found")
                .timestamp(LocalDateTime.now())
                .data(accountType)
                .build();
    }
    }
