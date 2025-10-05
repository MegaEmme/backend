package org.lessons.java.spring_cineteca.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// Spring Security framework gestisce i due principi base, autenticazione e
// autorizzazione
// Configurazione principale definisce i tre bean essenziali:
@EnableWebSecurity
public class SecurityConfiguration {
    // A:
    // 1) catena dei filtri(security filter chain)
    // 2) form di login/logout
    // 3) disabilitazione cors/csfr
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/").permitAll()
                .requestMatchers("films/create", "films/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/films/**").hasAuthority("ADMIN")
                .requestMatchers("/categories", "/categories/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/films", "/films/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/**").permitAll());

        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        http.exceptionHandling(Customizer.withDefaults());

        // Disabilito CORS e CSRF, che non si trovano nel blocco authorizeHttpRequests
        http.cors(cors -> cors.disable());
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    @SuppressWarnings("deprecation")
    // il DAOAuthenticationProvider è un intermediario che orchestra il processo di
    // autenticazione,
    // separando la logica del recupero dati (UserDetailService) da quella di
    // confronto delle password (PasswordEncoder)
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    // implementazione di userDetailsService fa da ponte tra Spring Security e il
    // database, è la logica di recupero dati
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    // responsabile di criptare e everificare la password
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
