package com.example.bookreview.configuration;

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
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/member/register","/css/**").permitAll()
                    .anyRequest().authenticated()
                    .and()// 로그인 설정
                .formLogin()
                    .loginPage("/member/login")
                    .permitAll()
                    .and()// 로그아웃 설정
                .logout()
                    .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select member_email,member_password,member_nickname,enabled "
                        + "from memberInfo "
                        + "where member_email = ?")
                .authoritiesByUsernameQuery("select m.member_email,r.name "
                        + "from user_role ur inner join memberinfo m on ur.member_id = m.member_id "
                        + "inner join role r on ur.role_id = r.id "
                        + "where m.member_email = ?");
    }
}