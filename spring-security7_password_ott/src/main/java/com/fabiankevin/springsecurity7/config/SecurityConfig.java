package com.fabiankevin.springsecurity7.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ott.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authorization.EnableMultiFactorAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableMultiFactorAuthentication(authorities = {
        FactorGrantedAuthority.PASSWORD_AUTHORITY, FactorGrantedAuthority.OTT_AUTHORITY
})
class SecurityConfig {

    @Bean
    SecurityFilterChain app(HttpSecurity http) {
        http.authorizeHttpRequests(
                        (authz) -> authz
                                .requestMatchers("/v1/api/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .oneTimeTokenLogin(httpSecurityOneTimeTokenLoginConfigurer -> {
                    httpSecurityOneTimeTokenLoginConfigurer
                            .tokenService(new OneTimeTokenService() {
                                private final Map<String, OneTimeToken> tokenStore = new ConcurrentHashMap<>();
                                @Override
                                public OneTimeToken generate(GenerateOneTimeTokenRequest request) {
                                    OneTimeToken token = new DefaultOneTimeToken("12345", request.getUsername(),
                                            Instant.now().plusSeconds(300));
                                    tokenStore.put(token.getTokenValue(), token);
                                    return token;
                                }

                                @Override
                                public @Nullable OneTimeToken consume(OneTimeTokenAuthenticationToken authenticationToken) {
                                    OneTimeToken token = tokenStore.remove(authenticationToken.getTokenValue());
                                    log.info("consumed token: " + token);
                                    return token;
                                }
                            });
                })
                .oneTimeTokenLogin(Customizer.withDefaults());
        return http.build();
    }


//    @Bean
//    Customizer<HttpSecurity> formLogin() {
//        return (http) -> http.formLogin(Customizer.withDefaults());
//    }

//    @Bean
//    Customizer<HttpSecurity> x509Login() {
//        return (http) -> http.x509(Customizer.withDefaults());
//    }
//

//    @Bean
//    Customizer<HttpSecurity> authz() {
////        AuthorizationManagerFactory<Object> mfa = AuthorizationMsanagerFactories.multiFactor()
////                .requireFactors(
////                        FactorGrantedAuthority.PASSWORD_AUTHORITY,
////                        FactorGrantedAuthority.X509_AUTHORITY).build();
//        return (http) -> http.authorizeHttpRequests((authorize) ->
//                authorize.requestMatchers("/v1/api/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated());
//    }

    @Bean
    UserDetailsService users() {
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder().username("admin").password("admin")
                        .authorities("ADMIN")
                        .roles("ADMIN")
                        .build());
    }

    @Bean
    OneTimeTokenGenerationSuccessHandler ottSuccessHandler() {
        return new LoggingOneTimeTokenGenerationSuccessHandler();
    }

    static final class LoggingOneTimeTokenGenerationSuccessHandler implements OneTimeTokenGenerationSuccessHandler {

        private static final String TOKEN_TEMPLATE = """
				********************************************************

				Use this one-time token: %s

				********************************************************""";

        private final Log logger = LogFactory.getLog(this.getClass());

        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, OneTimeToken oneTimeToken)
                throws IOException {
            this.logger.info(String.format(TOKEN_TEMPLATE, oneTimeToken.getTokenValue()));
            response.sendRedirect("/login/ott");
        }

    }
}