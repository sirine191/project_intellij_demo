package com.springdemo.ipvproject.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Exemple simple avec un utilisateur fictif
        if ("user".equals(username)) {
            return User.builder()
                    .username("user")
                    .password("{noop}password") // Utilisation de {noop} pour le mot de passe en clair
                    .roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
    }
}
