package ru.sshibko.bsss.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sshibko.bsss.entity.Role;
import ru.sshibko.bsss.entity.User;

import java.util.Collection;

@RequiredArgsConstructor
public class AppUserPrincipal implements UserDetails {

    private final User user;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(Role::toAuthority).toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
