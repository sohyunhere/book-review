package com.example.bookreview.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Override
    public void configure(WebSecurity web) { // 4
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/member/register").permitAll()
                    .anyRequest().authenticated()
                    .and()// 로그인 설정
                .formLogin()
                    .loginPage("/member/login")
                    .loginProcessingUrl("/login")// /login 주소가 호출되면 시큐리티가 낚아채서 대산 로그인 진행
//                    .passwordParameter("password")
                    .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
                    .permitAll()
                    .and()// 로그아웃 설정
                .logout()
//                   .logoutSuccessUrl("/") // 로그아웃 성공시 리다이렉트 주소
                   .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery("select member_email, member_password, enabled "
//                        + "from memberInfo "
//                        + "where member_email = ?")
//                .authoritiesByUsernameQuery("select m.member_email, r.name "
//                        + "from user_role ur inner join memberinfo m on ur.member_id = m.member_id "
//                        + "inner join role r on ur.role_id = r.id "
//                        + "where m.member_email = ?");
//    }
}