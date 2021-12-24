package com.pac.smcc.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//Objeto para seguridad
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
        //implementa userdetails
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().
//                withUser("admin").
//                password("{noop}123").
//                roles("ADMIN","USER").
//                and().
//                withUser("user").
//                password("{noop}123").
//                roles("USER");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/css/**", "/js/**","/images/**", "/webjars/**", "**/favicon.ico").permitAll().
                antMatchers("/editar/**","/agregar/**","/eliminar","/codigo","/agregarcodigo").
                hasRole("ADMIN").
                antMatchers("/dashboard").
                hasAnyRole("USER","ADMIN").
                and().
                formLogin().
                loginPage("/login").permitAll().
                and()
                .logout()
                .permitAll().and().
                exceptionHandling().
                accessDeniedPage("/errores/403")
        ;
    }
}
