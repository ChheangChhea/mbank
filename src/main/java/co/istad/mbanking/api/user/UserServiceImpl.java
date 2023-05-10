package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.UpdateUserDto;
import co.istad.mbanking.api.user.web.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;

    @Override
    public UserDto createNewUser(CreateUserDto createUserDto) {
        User user = userMapStruct.createUserDtoToUser(createUserDto);
        userMapper.insert(user);
        log.info("User={}", user.getId());

        return this.findUserById(user.getId());
    }

    //=========================================================
    @Override
    public UserDto findUserById(Integer id) {
        User user = userMapper.selectById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %d is not found", id)));

        return userMapStruct.userToUserDto(user);
    }

    @Override
    public UserDto findUserByStudentCardId(String studentCardId) {
        User user = userMapper.selectByStudentCardId(studentCardId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %s is not found", studentCardId)));

        return userMapStruct.userToUserDto(user);
    }

    //=====================================================
    @Override
    public PageInfo<UserDto> findAllUsers(int page, int limit ,String name) {
        PageInfo<User>userPageInfo= PageHelper.startPage(page,limit)
                .doSelectPageInfo(() ->userMapper.select(name));
        return userMapStruct.userPageInfotoUserDtoPageInfo(userPageInfo);
    }
    //======================= Delete ==============================
    @Override
    public Integer deleteUserById(Integer id) {
        boolean isExisted = userMapper.existsById(id);
        if (isExisted) {
            userMapper.deleteById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found", id));
    }
    //======================== Update Is Deleted Status By Id ==========================

    @Override
    public Integer updateIsDeletedStatusById(Integer id, boolean status) {
        boolean isExisted = userMapper.existsById(id);
        if(isExisted){
            userMapper.updateIsDeletedById(id,status);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found", id));
    }
    //==================Update User ById===============================

    @Override
   public UserDto updateUserById(Integer id, UpdateUserDto updateUserDto) {
        if(userMapper.existsById(id)){
          User user = userMapStruct.updateUserDtoToUser(updateUserDto);
            user.setId(id);
            userMapper.updateById(user);
            return this.findUserById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with %d is not found", id));

    }

    @Override
    public List<UserDto> userSearchName(UserDtoSearchName userDtoSearchName) {

        return null;
    }

//    @Override
//    public List<UserDto> userSearchName(UserDtoSearchName userDtoSearchName) {
////        if(userMapper.searchUserName()){
////            return userMapStruct.fro(userSearchName(userMapper.searchUserName(userDtoSearchName)));
////        }else {
////            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
////                    String.format("User with name %s is not found",userDtoSearchName));
////        }
////
////    }

//    @Override
//    public UserDto findUserByName(String name) {
//        User search;
//        if (userMapper.existsById(search)) {
//            search = userMapStruct.findUserByName(updateUserDto);
//            search.setId(name);
//            userMapper.findUserByName(search);
//            return this.findUserByName(search);
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                String.format("User with %s is not found", search));
//
//    }
    //==================Search==============================


}

//    ======================================
//    public Integer update


