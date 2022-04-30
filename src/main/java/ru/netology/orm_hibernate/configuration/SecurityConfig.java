package ru.netology.orm_hibernate.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private String encode(CharSequence rawPassword) {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(rawPassword);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(encode("iddqd")).roles("ADMIN").and()
                .withUser("verified").password(encode("0000")).roles("VERIFIED").and()
                .withUser("user").password(encode("0000")).roles("USER").and()
                .withUser("guest").password("{noop}0000").roles("USER").accountLocked(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and().authorizeRequests().antMatchers("/persons/read**").permitAll()
                .and().authorizeRequests().antMatchers("/persons/delete**").hasRole("ADMIN")
                .and().authorizeRequests().antMatchers("/persons/create**").hasAnyRole("VERIFIED", "ADMIN")
                .and().authorizeRequests().antMatchers("/persons/update**").hasAnyRole("VERIFIED", "ADMIN")
                .and().authorizeRequests().anyRequest().authenticated();
    }
}
