package ar.edu.itba.paw.webapp.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class PawUserDetails extends User {

    private final ar.edu.itba.paw.User user;

    public PawUserDetails(final ar.edu.itba.paw.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
        this.user = user;
    }

    // Agrega un getter para tu clase User
    public ar.edu.itba.paw.User getUser() {
        return this.user;
    }


}
