package ar.edu.itba.paw.webapp.auth;


import ar.edu.itba.paw.User;
import ar.edu.itba.paw.services.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


/* No es un servicio en sí. */
@Component
public class PawUserDetailsService implements UserDetailsService {

    private UserService us;

    public PawUserDetailsService(UserService us) {
        this.us = us;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = us.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No such user"));

        Set<SimpleGrantedAuthority> authorities = Set.of("user", "editor", "reviewer").stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());

        return new PawUserDetails(user, authorities);
    }

}
