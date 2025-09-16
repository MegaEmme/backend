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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                // Rotta base (localhos:8080)
                .requestMatchers("/").permitAll()
                // I percorsi per creare e modificare i film e le categorie sono per soli
                // ADMIN.
                .requestMatchers("films/create", "films/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/films/**").hasAuthority("ADMIN")
                .requestMatchers("/categories", "/categories/**").hasAnyAuthority("USER", "ADMIN")
                // I percorsi per i film sono accessibili sia da USER che da ADMIN.
                .requestMatchers("/films", "/films/**").hasAnyAuthority("USER", "ADMIN")
                // Tutti gli altri percorsi sono accessibili a chiunque.
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
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
