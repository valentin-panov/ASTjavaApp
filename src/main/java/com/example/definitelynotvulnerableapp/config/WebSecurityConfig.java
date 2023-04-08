package com.example.definitelynotvulnerableapp.config;

import com.example.definitelynotvulnerableapp.security.user.DnvUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DnvUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/rest**/**").authenticated().and().httpBasic().and()
                .authorizeRequests()
                    .antMatchers("/", "/favicon.ico", "/static**/**", "/signup", "/signin**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/signin")
                    .permitAll()
                    .failureUrl("/signin?error=1")
                    .loginProcessingUrl("/authenticate")
                    .and()
                .logout()
                .logoutUrl("/logout")
                    .permitAll()
                    .logoutSuccessUrl("/")
                    .and()
                .headers().frameOptions().disable().and()
                .csrf().disable()
                .sessionManagement().sessionFixation().none();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .eraseCredentials(true)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
