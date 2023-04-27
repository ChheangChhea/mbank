package co.istad.mbanking.api.user.web;

import co.istad.mbanking.api.user.UserDto;
import co.istad.mbanking.api.user.UserService;
import co.istad.mbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping( "/api/v1/users")
public class UserRestController {
    private final UserService userService;
    @PostMapping
    public BaseRest<?> createNewUser(@RequestBody  @Valid CreateUserDto createUserDto){
       UserDto user = userService.createNewUser(createUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been created successfully.")
                .data(user)
                .build();
    }

}
