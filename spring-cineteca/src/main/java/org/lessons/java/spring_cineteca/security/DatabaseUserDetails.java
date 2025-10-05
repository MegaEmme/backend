package org.lessons.java.spring_cineteca.security;

import java.util.HashSet;
import java.util.Set;

import org.lessons.java.spring_cineteca.model.Role;
import org.lessons.java.spring_cineteca.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DatabaseUserDetails implements UserDetails {
    // Questo è l'oggetto che modella i dati dell'utente in formato comprensibile da
    // Spring Security
    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    // interfaccia che ha come solo metodo getAuthoriry() che ritorna una stringa
    // incapsula la nostra entità "user" e mappa i roles in oggetti
    // SimpleGrantedAuthority(getAuthorities())
    public DatabaseUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new HashSet<GrantedAuthority>();

        for (Role userRole : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(userRole.getName()));
        }
    }

    public Integer getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    // METODI OVERRIDE
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
