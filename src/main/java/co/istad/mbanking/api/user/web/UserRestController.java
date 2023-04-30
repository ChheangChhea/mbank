package co.istad.mbanking.api.user.web;

import co.istad.mbanking.api.user.UserDto;
import co.istad.mbanking.api.user.UserService;
import co.istad.mbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping( "/api/v1/users")
public class UserRestController {
    private final UserService userService;
//===============================================

//    @PutMapping("/{id}")
//    public BaseRest<?>updateUserById(@PathVariable("id") Integer id,@RequestBody UpdateUserDto updateUserDto){
//        UserDto userDto = userService.updateUserById(id, updateUserDto);
//
//        return BaseRest.builder()
//                .status(true)
//                .code(HttpStatus.OK.value())
//                .message("User has been disabled successfully.kjkjk")
//                .timestamp(LocalDateTime.now())
//                .data(userDto)
//                .build();
//    }
    @PutMapping("/{id}")
    public BaseRest<?> updateUserById(@PathVariable("id") Integer id,@RequestBody UpdateUserDto updateUserDto){
        UserDto userDto = userService.updateUserById(id, updateUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been disabled successfully.jghvhgv ")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }
//======================= Update ==================================

    @PutMapping("/{id}/is-deleted")
    public BaseRest<?> updateIsDeletedStatusById(@PathVariable Integer id,@RequestBody IsDeletedDto dto ){
        Integer deletedId = userService.updateIsDeletedStatusById(id, dto.status());
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been disabled successfully.")
                .timestamp(LocalDateTime.now())
                .data(deletedId)
                .build();
    }

//=================== Delete =============================
    @DeleteMapping("/{id}")
    public BaseRest<?> deleteUserById(@PathVariable Integer id){
       Integer deleteId = userService.deleteUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been deleted successfully.")
                .timestamp(LocalDateTime.now())
                .data(deleteId)
                .build();
    }

    //=====================================
    @GetMapping
    public BaseRest<?>findAllUsers(@RequestParam(name = "page",required = false,defaultValue = "1") int page,
                                   @RequestParam(name = "limit",required = false,defaultValue = "20") int limit
                                   ){

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been found successfully.")
                .timestamp(LocalDateTime.now())
                .data(userService.page(page, limit))
                .build();
    }
    //=====================================
    @GetMapping("/{id}")
    public BaseRest<?> findUserById(@PathVariable Integer id){
        UserDto userDto=userService.findUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been found successfully.")
                .data(userDto)
                .build();
    }

    //=====================================
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
