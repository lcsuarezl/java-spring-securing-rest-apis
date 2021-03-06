package io.jzheaux.springsecurity.resolutions;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserRepositoryUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  this.userRepository.findByUserName(username).map(BridgeUser::new).orElseThrow(() -> new UsernameNotFoundException("Invalid User"));
    }

    private static class BridgeUser  extends  User implements UserDetails {
        public BridgeUser(User user){
            super(user);
        }

        public List<GrantedAuthority> getAuthorities(){
            return this.getUserAuthorities().stream()
                    .map(UserAuthority::getAuthority)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }

        @Override
        public String getUsername() {
            return this.getUserName();
        }

        public boolean isAccountNonExpired(){
            return this.isEnabled();
        }

        public boolean isAccountNonLocked(){
            return this.isEnabled();
        }

        public boolean isCredentialsNonExpired(){
            return this.isEnabled();
        }
    }

}
