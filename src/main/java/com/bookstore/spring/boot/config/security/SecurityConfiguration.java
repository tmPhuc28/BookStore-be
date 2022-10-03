package com.bookstore.spring.boot.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
    @Autowired
    private AuthEntryPointException unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationTokenFilterBean() {
        return new AuthTokenFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and()
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests((authz) -> authz
                        // Allow access public resource
                        .antMatchers(
                                HttpMethod.GET,
                                "/",
                                "/favicon.ico",
                                "/**/*.html",
                                "/**/*.css",
                                "/**/*.js",
                                "/**/*.png",
                                "/**/*.gif",
                                "/public/**",
                                "/**/*.json",
                                "/**/*.jpg",
                                // enable api docs
                                "/v3/api-docs/**"
                        ).permitAll()
                        // allow CORS option calls
                        .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        // Custom JWT based security filter
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();
        httpSecurity.headers().frameOptions().disable();
        return httpSecurity.build();
    }
}
