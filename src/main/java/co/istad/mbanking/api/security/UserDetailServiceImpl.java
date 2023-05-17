package co.istad.mbanking.api.security;

import co.istad.mbanking.api.auth.AuthMapper;
import co.istad.mbanking.api.user.UserService;
import co.istad.mbanking.api.user.web.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("it work here ");
        System.out.println(authMapper.loadUsrByUsername(username));
        User user = authMapper.loadUsrByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException("User is not valid"));
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(user);
        return customUserDetails;
    }
}
