package com.helpdesk.api.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.helpdesk.api.components.CORSFilter;
import com.helpdesk.api.components.JWTAuthenticationFilter;
import com.helpdesk.api.components.JWTAuthenticationProvider;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JWTAuthenticationProvider jwtAuthenticationProvider;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final CORSFilter corsFilter;

    @Autowired
    public SecurityConfiguration(
            JWTAuthenticationProvider jwtAuthenticationProvider,
            JWTAuthenticationFilter jwtAuthenticationFilter,
            CORSFilter corsFilter) {

        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.corsFilter = corsFilter;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
            .antMatchers("/authentication/**")
            .permitAll()
            .antMatchers("/management/**")
            .permitAll()
            .antMatchers("/api/**")
            .authenticated()
            .anyRequest()
            .authenticated();

        http.csrf().disable();

        http.logout()
            .disable();

        http.requestCache()
            .disable();

        http.addFilterBefore(this.corsFilter, ChannelProcessingFilter.class);
        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling()
            .authenticationEntryPoint(
                    (req, res, e) -> res.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized"));

    }

}
