package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.UpdateUserDto;
import co.istad.mbanking.api.user.web.User;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapStruct {


    User createUserDtoToUser(CreateUserDto createUserDto);
 //==================== Update =================================
    User updateUserDtoToUser(UpdateUserDto updateUserDto);
//=============================================================
    UserDto userToUserDto( User user);
    User userDtoToUser(UserDto userDto);
    PageInfo <UserDto>userPageInfotoUserDtoPageInfo(PageInfo<User>pageInfo);
//=====================Search=============================
List<UserDto>fro(List<User>users);
}
