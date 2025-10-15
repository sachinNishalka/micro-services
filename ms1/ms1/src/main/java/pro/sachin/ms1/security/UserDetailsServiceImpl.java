package pro.sachin.ms1.security;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pro.sachin.ms1.model.Role;
import pro.sachin.ms1.model.User;
import pro.sachin.ms1.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found for the email");
        }

        System.out.println("=== DEBUG ===");
        System.out.println("User: " + user.getEmail());
        System.out.println("Roles: " + user.getRoles());
        user.getRoles().forEach(role -> System.out.println("  - " + role.getName()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles()  // This works because Role implements GrantedAuthority
        );
    }
}
