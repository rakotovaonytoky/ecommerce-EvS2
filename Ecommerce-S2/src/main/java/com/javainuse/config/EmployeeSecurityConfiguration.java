package com.javainuse.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
public class EmployeeSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    
    @Autowired
	private EmployeeAuthenticationSuccessHandler successHandler;

    //password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

    // Enable jdbc authentication
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**","/webapp/WEB-INF/jsp/assets/**");
    }

//    ampiasaina rehefa mienregistrer utilisateur any amin'base
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/register","/resources/**","/customer/shop","/customer/searchingProduct").permitAll()
                .antMatchers("/customer/**","/welcome","/getEmployees").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/getEmployees").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**","/addNewEmployee").hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin().loginPage("/login").successHandler(successHandler)
                .permitAll().and().logout().deleteCookies("JSESSIONID").permitAll();

        http.csrf().disable();
    }

    //remove this in memory authentication configuration 
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
//        authenticationMgr.inMemoryAuthentication().withUser("employee").password("employee")
//                .authorities("ROLE_USER").and().withUser("javainuse").password("javainuse")
//                .authorities("ROLE_USER", "ROLE_ADMIN");
//    }
}
