package com.fabiankevin.springsecurity7_webauthn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authorization.EnableMultiFactorAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.core.authority.FactorGrantedAuthority.*;

@Configuration
@EnableMultiFactorAuthentication(authorities = { PASSWORD_AUTHORITY, WEBAUTHN_AUTHORITY })
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/webauthn/authenticate/options").permitAll()

                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .webAuthn(webAuthn -> webAuthn
                                .rpId("localhost")
                                .rpName("X.509+WebAuthn MFA Sample")
                                .allowedOrigins("http://localhost:8080")
                        // optional properties
                );
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
}
