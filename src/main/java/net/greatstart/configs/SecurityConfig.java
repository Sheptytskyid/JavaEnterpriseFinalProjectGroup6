package net.greatstart.configs;

import net.greatstart.services.GreatStartUserDetailsService;
import net.greatstart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;
    private DataSource dataSource;

    @Autowired
    public SecurityConfig(UserService userService, DataSource dataSource) {
        this.userService = userService;
        this.dataSource = dataSource;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(new GreatStartUserDetailsService(userService))
            .passwordEncoder(passwordEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/project/new", "/project/my", "/invinterest/add", "/invinterest").authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .usernameParameter("email")
                // TODO: change the routing when home page is ready
                .defaultSuccessUrl("/project/")
                .and()
                .logout()
                .logoutSuccessUrl("/project/")
                .and()
                .rememberMe()
                .key("greatStartKey")
                .rememberMeParameter("remember-me");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
