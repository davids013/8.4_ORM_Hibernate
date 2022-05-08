package ru.netology.orm_hibernate.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private DataSource dataSource;

//    @Bean
//    public PasswordEncoder encoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    private String encode(CharSequence rawPassword) {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(rawPassword);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(encode("iddqd")).roles("ADMIN").and()
                .withUser("verified").password(encode("0000")).roles("VERIFIED").and()
                .withUser("user").password(encode("0000")).roles("USER").and()
                .withUser("guest").password("{noop}0000").roles("USER").accountLocked(true).and()
                .withUser("reader").password(encode("0000")).roles("READ").and()
                .withUser("writer").password(encode("0000")).roles("WRITE").and()
                .withUser("deleter").password(encode("0000")).roles("DELETE");

//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("SELECT name, surname, 'true' FROM persons WHERE name=?")
//                .authoritiesByUsernameQuery("SELECT name, phone_number FROM persons WHERE name=?");
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
