package com.sparta.springcore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//WebSecurityConfigureAdapter 를 상속받은 클래스에 @EnalbeWebSecurity 어노테이션을 사용하면 SpringSecurityFilterChain이 자동 포함되며, 기본적인 Web 보안을 활성화하겠다는 의미
@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 사용가능하게 설정
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // BCryptPasswordEncoder : spring security에서 제공 및 권고 // BCrypt 해싱 함수(BCrypt hashing function)를 사용해서 비밀번호를 인코딩해주는 메서드와 사용자의 의해 제출된 비밀번호와 저장소에 저장되어 있는 비밀번호의 일치 여부를 확인해주는 메서드를 제공
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        //스프링 시큐리티는 세션 기반으로 되어있다 -> rest api 기반으로 하려면 추가 작업필요.
        // 세션 생성성을 막고 -> stateless하게 ->
        http.authorizeRequests()
                .antMatchers("/images/**").permitAll()  // image 폴더를 login 없이 허용
                .antMatchers("/css/**").permitAll()  // css 폴더를 login 없이 허용
                .antMatchers("/user/**").permitAll() // 회원 관리 URL 전부를 login 없이 허용
                .antMatchers("/h2-console/**").permitAll()// h2-console URL 을 login 없이 허용
                .anyRequest().authenticated() //어떤 요청이 오던지 로그인 과정이 없었으면 로그인을 하도록 하겠다.
                .and()
                .formLogin()
                .loginPage("/user/login")  // 스프링 시큐리티의 default 로그인 페이지가 호출되었을 때 호출 될 경로
                .failureUrl("/user/login/error") // 로그인이 실패 했을 경우 경로
                .defaultSuccessUrl("/") //로그인이 완료되었을 때 이동할 위치
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/user/forbidden"); //인가되지 않은 사용자 접근시 접근 금지 페이지로 이동

        // + 인증만 JWT로 FILTER붙여서 사용할 수 있다. Chainofresponsible pattern ???
        // 스프링을 자유자재로 쓸 수 있을 때 프레임워크를 공부
        //인증이 안되있을 때 자동으로 로그인 페이지로 가는데 이걸 필터로 인터셉트해서 우리가 인증처리를 한다.?

    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}