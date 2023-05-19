package co.istad.mbanking.api.auth.web;

import co.istad.mbanking.api.auth.AuthService;
import co.istad.mbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthRestController {


    private  final AuthService authService;


    //====================Login================
    @PostMapping("/login")
    public BaseRest<?>login(@Valid @RequestBody LogInDto loginDto){

        AuthDto authDto=authService.login(loginDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("YOU have Been login Success ")
                .timestamp(LocalDateTime.now())
                .data(authDto)
                .build();




    }

    //==================== Register ================
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

    //==================== Verify ================
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

    //====================Check Verify ================
    @GetMapping("/check-verify")
    public BaseRest<?> checkVerify(@RequestParam String email,
                                   @RequestParam String verifiedCode){

        log.info("Email:{}",email);
        log.info("Verified Code{}",verifiedCode);
        authService.checkVerify(email, verifiedCode);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You has been verified Successfully")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }
    }

