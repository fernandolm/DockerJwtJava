package com.ticketreceiver.service.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {

        if(!username.equals("test_user")) {
            throw new UsernameNotFoundException("Invalid user: " + username);
        }

        return new org.springframework.security
                .core.userdetails.User("test_user", "$2a$10$QBASd71FatUnkWcXsZ0OVe9dGjtut5adt6W8EE.24c.d2DmpyRdQa",
                true, true, true,
                true, getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
