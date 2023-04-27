package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStruct {

    User createUserDtoToUser(CreateUserDto createUserDto);
    UserDto userToUserDto( User user);
    User userDtoToUser(UserDto userDto);

}
