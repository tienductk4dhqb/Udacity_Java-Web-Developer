package udacity.project1.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import udacity.project1.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class SecurityAdapter extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    public SecurityAdapter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/signup", "/css/**", "/js/**")
            .permitAll()
            .anyRequest()
            .authenticated();

        http.formLogin()
            .loginPage("/login")
            .failureUrl("/login-error")
            .permitAll();

        http.formLogin()
            .defaultSuccessUrl("/home", true)
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/logout-success")
            .permitAll();

    }

}
