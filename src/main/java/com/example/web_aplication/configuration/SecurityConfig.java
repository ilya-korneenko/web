package com.example.web_aplication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    http.csrf().disable();
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/posts", "/comments","/users").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
//}
@Configuration

@EnableWebSecurity

public class SecurityConfig {

    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                .authorizeHttpRequests(auth -> auth

                        .anyRequest().permitAll() // Разрешить доступ ко всем эндпоинтам

                )

                .csrf(csrf -> csrf.disable()); // Отключить CSRF

//        http
//
//                .authorizeHttpRequests(auth -> auth
//
//                        .requestMatchers("/users").permitAll() // Разрешить доступ к /users
//
//                        .requestMatchers("/users/*").permitAll() // Разрешить доступ к /users/{id}
//
//                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
//
//                )
//
//                .csrf(csrf -> csrf.disable()); // Отключить CSRF, если это необходимо



        return http.build();

    }

}