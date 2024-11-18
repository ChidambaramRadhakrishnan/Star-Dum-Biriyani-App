package com.StarDumBiriyani.App.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    // Define users and their roles
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin34")) // Set admin password
                .roles("ADMIN") // Assign ADMIN role
                .build();

        UserDetails user = User.withUsername("user")
                .password(encoder.encode("user34")) // Set user password
                .roles("USER") // Assign USER role
                .build();

        return new InMemoryUserDetailsManager(admin, user); // Return in-memory users
    }

    // Configure HTTP security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection if necessary
                .authorizeRequests(auth -> auth
                        .requestMatchers("/admin/**").authenticated() // Admins need to be authenticated to access this URL
                        .requestMatchers("/user/**").permitAll() // Users can access all user pages without authentication
                        .requestMatchers("/**").permitAll() // Allow unrestricted access to other URLs
                )
                .formLogin(form -> form
//                        .loginPage("/login") // You can provide a custom login page here if needed
                        .permitAll() // Allow everyone to access the login page
                )
                .logout(logout -> logout
                        .permitAll() // Allow everyone to log out
                );

        return security.build(); // Return the security configuration
    }

    // Password encoder bean for encoding passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCryptPasswordEncoder for password encoding
    }
}
