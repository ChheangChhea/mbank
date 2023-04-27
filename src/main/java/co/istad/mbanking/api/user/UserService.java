package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;

public interface UserService {
UserDto createNewUser(CreateUserDto createUserDto);
}
