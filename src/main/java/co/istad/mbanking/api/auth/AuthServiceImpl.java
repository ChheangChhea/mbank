package co.istad.mbanking.api.auth;

import co.istad.mbanking.api.auth.web.RegisterDto;
import co.istad.mbanking.api.user.UserMapStruct;
import co.istad.mbanking.api.user.web.User;
import co.istad.mbanking.api.util.MailUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthMapper authMapper;
    private final UserMapStruct userMapStruct;
    private final PasswordEncoder encoder;
    private final MailUtil mailUtil;

    @Value("{spring.mail.username}")
    private String appMail;

    @Override
    public void register(RegisterDto registerDto) {
        User user = userMapStruct.registerDtoToUser(registerDto);
        user.setIsVerified(false);
        log.info("User:{}", user.getEmail());
        log.info("User:{}", user.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));
        authMapper.register(user);


    }

    @Override
    public void verify(String email) {

        User user = authMapper.selectByEmail(email).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "email search not found ..."
                )
        );
        user.setVerifiedCode(UUID.randomUUID().toString());
        MailUtil.Mata<?> meta = MailUtil.Mata.builder()
                .to(email)
                .from(appMail)
                .subject("Account ")
                .subject("Account Verify")
                .templateUrl("auth/verify")
                .data(user)
                .build();
        try {
            mailUtil.send(meta);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }
}