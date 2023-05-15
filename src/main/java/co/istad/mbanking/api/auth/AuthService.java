package co.istad.mbanking.api.auth;

import co.istad.mbanking.api.auth.web.RegisterDto;
import co.istad.mbanking.api.user.web.User;

public interface AuthService {

    void register(RegisterDto registerDto);

    void verify(String email);

}
