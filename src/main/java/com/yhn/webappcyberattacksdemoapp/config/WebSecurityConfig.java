package com.yhn.webappcyberattacksdemoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf()
//                .disable()
//                .formLogin()
//                .loginPage("/login")
////                .loginProcessingUrl("/process-login")
//                .successForwardUrl("/xsrf/online-banking")
//                .failureForwardUrl("/login?error=true")
//
////                .permitAll()
////                .and()
////                .authorizeHttpRequests()
////                .requestMatchers("/xsrf/**").authenticated()
//                .and()
//                .csrf()
//                .disable().build();
       return httpSecurity
//               .securityContext(context -> context
//                       .requireExplicitSave(false)
//               )
               .csrf().disable()
               .authorizeHttpRequests()
               .requestMatchers("/public/**").permitAll()
               .requestMatchers("/favicon.ico").permitAll()
               .requestMatchers("/sql-injection/**").permitAll()
               .requestMatchers("/command-injection/**").permitAll()
               .requestMatchers("/path-traversal/**").permitAll()
               .requestMatchers("/xss/reflected/**").permitAll()
               .requestMatchers("/xss/stored/**").permitAll()
               .requestMatchers("/xxe/**").permitAll()
               .requestMatchers("/xsrf/**").authenticated()
               .requestMatchers("/images/**", "/css/**", "/js/**",
                       "/WEB-INF/jsp/noauth/**",
                       "/WEB-INF/jsp/search-posts-with-results.jsp",
                       "/WEB-INF/jsp/not-found.jsp",
                       "/WEB-INF/jsp/search-posts.jsp").permitAll()

               .anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/xsrf/online-banking")
                        .loginProcessingUrl("/process-login")
                        .failureUrl("/login?error=true")
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                ).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("yalco").password("123").authorities("read").build());
        return inMemoryUserDetailsManager;
    }
}
