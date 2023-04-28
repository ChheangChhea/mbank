package co.istad.mbanking.api.user;

import co.istad.mbanking.api.user.web.CreateUserDto;
import co.istad.mbanking.api.user.web.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    //=====================================================
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

    @Override
    public Integer updateIsDeletedStatusById(Integer id, boolean status) {
        boolean isExisted = userMapper.existsById(id);
        if(isExisted){
            userMapper.updateIsDeletedById(id,status);
        }
        System.out.println(isExisted);
        return null;
    }
}

//===========================================
//    @Override
//    public Integer updateIsDeletedStatusById(Integer id,boolean status) {
//        boolean isExisted = userMapper.existsById(id);
//        if (isExisted) {
//            userMapper.updateIsDeletedById(id, status);
//            return id;
//        }
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                String.format("User with %d is not found", id));
//    }
//
//    }
//    ======================================
//    public Integer update


