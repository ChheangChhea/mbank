package co.istad.mbanking.api.auth;

import co.istad.mbanking.api.auth.web.RegisterDto;
import co.istad.mbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.ap.internal.util.IgnoreJRERequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {


    private  final AuthService authService;
    @PostMapping ("/register")
    public BaseRest<?> register(@Valid @RequestBody RegisterDto registerDto ){
      //Call service
        authService.register(registerDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("YOU have Been register Success ")
                .timestamp(LocalDateTime.now())
                .data(registerDto.email())
                .build();



    }
    @PostMapping("/verify")
    public BaseRest<?>verify(@RequestParam String email){
        authService.verify(email);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Please check email and verify")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }
}
