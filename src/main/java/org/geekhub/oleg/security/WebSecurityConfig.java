package org.geekhub.oleg.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, JdbcTemplate template) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(template.getDataSource())
                .usersByUsernameQuery("SELECT login, password, 'TRUE' as enabled " +
                        "FROM users " +
                        "JOIN role r ON r.id = users.role_id " +
                        "WHERE login = ?")
                .authoritiesByUsernameQuery("SELECT login, role as authority " +
                        "FROM users " +
                        "JOIN role r ON r.id = users.role_id " +
                        "WHERE login = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/registration").permitAll()
                .antMatchers("/event/purchase").hasAnyRole("USER")
                .antMatchers("/event/cabinet").hasAnyRole("USER")
                .antMatchers("/event/add").hasAnyRole("ADMIN")
                .antMatchers("/event/edit/*").hasAnyRole("ADMIN")
                .antMatchers("/event/analytic").hasAnyRole("ADMIN")
                .antMatchers("/event/{id}/feedback/{id}/delete").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/event")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll().logoutSuccessUrl("/login");
    }
}
