package pro.sachin.ms1.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        this is http method level security and you can use method level security too
//        the next thing is you have to use above annotation in order to enable method level securoty
//        if you do so, you dont need the bellow security configurations.
        http.httpBasic(Customizer.withDefaults());
//        here if you want you can use a regular expression to forbidon the wildcard**
//        if you want anybody to access certain uri use permitAll()
        http.authorizeHttpRequests(authorize->authorize.requestMatchers(HttpMethod.GET,"/coupons/**").hasAnyRole("USER","ADMIN").requestMatchers(HttpMethod.POST,"/coupons/**").hasAnyRole("ADMIN"));
        http.csrf(csrf->csrf.disable());

        http.cors(cors->{
            CorsConfigurationSource corsConfigurationSource = request -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowedOrigins(List.of("http://localhost:5337"));
                corsConfiguration.setAllowedMethods(List.of("GET", "POST"));
                return corsConfiguration;
            };
            cors.configurationSource(corsConfigurationSource);
        });
        return http.build();
    }
}
