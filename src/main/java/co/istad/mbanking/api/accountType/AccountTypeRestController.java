package co.istad.mbanking.api.accountType;

import co.istad.mbanking.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
public class AccountTypeRestController {

    private final AccountTypeService accountTypeService;
    @GetMapping
    public BaseRest<?> findAll(){
        var accountTypeDtoList = accountTypeService.findAll();
        System.out.println("================================");
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Web")
                .data(accountTypeDtoList)

                .build();


    }

}

