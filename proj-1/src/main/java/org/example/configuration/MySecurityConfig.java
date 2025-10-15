package org.example.configuration;

import org.example.security.MySecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;

@Configuration
public class MySecurityConfig {

//    @Bean
//    UserDetailsService userDetailsService(){
//       InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails user = User.withUsername("sachin").password(bCryptPasswordEncoder().encode("sachin")).authorities("read").build();
//        userDetailsManager.createUser(user);
//        return userDetailsManager;
//    }
//
//    @Bean
//    BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        here if we are using basic authentication it would be like this,
//        if we are using form based login it would be like formLogin
        http.httpBasic();
//        in order to get access to a single engpoint, we can use requestMatchers("/hello")
//        this will allow anyone who is authenticated to get access to hello endpoint
//        if you want to deny access to all other requests make it .anyRequest().denyAll()
        http.authorizeHttpRequests().anyRequest().authenticated();
//         here we are adding a security filter before the basic authentication filter
        http.addFilterBefore(new MySecurityFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }
}
