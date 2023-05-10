package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.UpdateUserDto;
import co.istad.mbanking.api.user.web.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
UserDto createNewUser(CreateUserDto createUserDto);
//=====================================
UserDto findUserById(Integer id);

UserDto findUserByStudentCardId(String studentCardId);
//===============Page Gination================
    PageInfo<UserDto>findAllUsers(int page,int limit,String name);

Integer deleteUserById(Integer id );

Integer updateIsDeletedStatusById(Integer id, boolean status);

UserDto updateUserById(Integer id, UpdateUserDto updateUserDto);

//===================Search============
 List<UserDto>userSearchName(UserDtoSearchName userDtoSearchName);
}
