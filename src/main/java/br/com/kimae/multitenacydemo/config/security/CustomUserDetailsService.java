package br.com.kimae.multitenacydemo.config.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.kimae.multitenacydemo.persistence.app.AppUser;
import br.com.kimae.multitenacydemo.persistence.app.AppUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final AppUser user = appUserRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
        return new AppPrincipal(user.getEmail(), user.getPassword(),  getAuthorities(ROLE_USER), user.getClientDatabase());
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }
}
