package com.fabiankevin.springsecurity7_password_x509.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManagerFactories;
import org.springframework.security.config.annotation.authorization.EnableMultiFactorAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.core.authority.FactorGrantedAuthority.*;

@Configuration
@EnableMultiFactorAuthentication(authorities = { PASSWORD_AUTHORITY, X509_AUTHORITY })
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) {
        var x509AuthorityGrantedFactor = AuthorizationManagerFactories.multiFactor()
                .requireFactors(
                        FactorGrantedAuthority.X509_AUTHORITY
                )
                .build();
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/").access(x509AuthorityGrantedFactor.authenticated())
                        .anyRequest().authenticated()
                )
                .x509(withDefaults())
                .formLogin(withDefaults());
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
