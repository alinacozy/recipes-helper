package com.example.recipes_helper.config;

import com.example.recipes_helper.services.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService (){
        return new MyUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
//        requestMatchers("/login", "/"/login"").anonymous().
                .authorizeHttpRequests(auth ->auth.requestMatchers("/login", "/signup").anonymous().requestMatchers("/", "/recipes", "/login", "/new-user", "/signup").permitAll()
                    .requestMatchers("/**").authenticated())
//                .formLogin((form) -> form
//                        .loginPage("/register")
//                        .permitAll()
//                        .defaultSuccessUrl("/recipes")
//                        .successHandler((request, response, authentication) -> {
//                            response.setStatus(HttpServletResponse.SC_OK);
//                        })
//                        .failureHandler((request, response, exception) -> {
//                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        })
//                )
//                .logout(LogoutConfigurer::permitAll)
//                .build();
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll).build();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


}
