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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
@Transactional

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
        User user = userMapStruct.fromRegisterDtoToUser(registerDto);
        user.setIsVerified(false);
        log.info("User:{}", user.getEmail());
        log.info("User:{}", user.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));
        if(authMapper.register(user)){
            for(Integer role: registerDto.roleIds()){
               authMapper.createUserRole(user.getId(),role);
            }
        }
    }

    public void verify(String email) {

        User user = authMapper.selectByEmail(email).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found"));
        String verifiedCode = UUID.randomUUID().toString();

        if (authMapper.updateVerifiedCode(email, verifiedCode)) {
            user.setVerifiedCode(verifiedCode);

        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "user cannot be verified");
        }

        MailUtil.Mata<?> meta = MailUtil.Mata.builder()
                .to(email)
                .from(appMail)
                .subject("Account Verification")
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


    @Override
    public void checkVerify(String email, String verifiedCode) {
        User user = authMapper.selectByEmailAndVerifiedCode(email, verifiedCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " User is not exist in  the Database"));
        System.out.println("verified : "+user.getIsVerified());
        System.out.println("uer : "+user);
        if (!user.getIsVerified()) {
            authMapper.updateVerifiedCode(email,verifiedCode);
//            authMapper.verify(email, verifiedCode);
        }

    }
}