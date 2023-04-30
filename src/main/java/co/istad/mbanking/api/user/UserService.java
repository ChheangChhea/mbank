package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.UpdateUserDto;
import co.istad.mbanking.api.user.web.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public interface UserService {
UserDto createNewUser(CreateUserDto createUserDto);
UserDto findUserById(Integer id);

//===============================
    PageInfo<UserDto>page(int page,int limit);

Integer deleteUserById(Integer id );

Integer updateIsDeletedStatusById(Integer id, boolean status);

UserDto updateUserById(Integer id, UpdateUserDto updateUserDto);
}
