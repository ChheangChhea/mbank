package co.istad.mbanking.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    /*
   @Bean
   public InMemoryUserDetailsManager userDetailsService() {
       InMemoryUserDetailsManager userDetailsManager=new InMemoryUserDetailsManager();
       UserDetails admin = User.builder()
               .username("admin")
               .password(encoder.encode("123"))
               .roles("ADMIN")
               .build();
       UserDetails goldUser = User.builder()
               .username("gold")
               .password(encoder.encode("123"))
               .roles("ADMIN","ACCOUNT")
               .build();
       UserDetails user  = User.builder()
               .username("user")
               .password(encoder.encode("123"))
               .roles("USER")
               .build();

       userDetailsManager.createUser(admin);

       userDetailsManager.createUser(goldUser);

       userDetailsManager.createUser(user);
       return userDetailsManager;

   }
 */
    private final PasswordEncoder encoder;
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvide() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(encoder);
        return auth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());
        http.csrf(AbstractHttpConfigurer::disable);
        // Authorize URL mapping
        http.authorizeHttpRequests(request -> {
            request.requestMatchers("/api/v1/auth/**").permitAll();
            request.requestMatchers(HttpMethod.GET,"/api/v1/users/**").hasRole("ADMIN");
            request.requestMatchers(HttpMethod.POST,"/api/v1/users/**").hasRole("SYSTEM");
            request.requestMatchers(HttpMethod.POST,"/api/v1/account-types/**", "/api/v1/files/**").hasAnyRole("CUSTOMER", "SYSTEM");
            request.anyRequest().authenticated();
        });

        // Security
        http.httpBasic();
        //Make Security http Status
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }


}
