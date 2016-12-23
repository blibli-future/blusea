package com.blibli.future.security;

/**
 * Created by adhikasp on 08/10/2016.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/catering/register")
                    .permitAll()
                // URL that need special access role need to
                // declared explicitly
                .antMatchers("/my-customer/register")
                    .permitAll()
                .antMatchers("/my-catering/register")
                    .permitAll()
                // Catch all rules for private page
                .antMatchers("/admin/**")
                    .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/my-customer/**")
                    .access("hasRole('ROLE_CUSTOMER')")
                .antMatchers("/my-catering/**")
                    .access("hasRole('ROLE_CATERING')")
                // Any other url is accessible by everyone
                .anyRequest()
                    .permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/login/process")
                    .failureUrl("/login?error=1")
                    .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from blusea_user where username=?")
                .authoritiesByUsernameQuery("select username, role from user_role where username=?  ");
    }
}
