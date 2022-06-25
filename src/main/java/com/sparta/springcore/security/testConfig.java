//package com.sparta.springcore.security;
//
//import org.springframework.security.config.http.SessionCreationPolicy;
////restAPI 설정
//public class testConfig {
//
//  http
//          .cors()  // cors 허용
//          .and()
//                    .sessionManagement()  // sessionManagement 정책 설정
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 막고
//                .and()
//                    .csrf().disable()  // 세션을 사용안하므로 csrf 처리 안함.
//                    .formLogin().disable()  // 폼기반 로그인 인증 사용 x, 백엔드서버는 필요x
//                    .httpBasic().disable()  // http 기반 인증 사용 x, 백엔드서버는 필요x
//                    .exceptionHandling()
//                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)  //인증실패
//                    .accessDeniedHandler(jwtAccessDeniedHandler)  //인가실패 -> 인터페이스 받아서 구현
//    			.and()
//                    .authorizeRequests()  // 토큰이 없어도 되는 것들은 permitAll , 나머지는 인증 필요
//                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                    .antMatchers("/api/v1/member/login").permitAll()
//                    .antMatchers("/api/v1/member/refresh").permitAll()
//                    .antMatchers("/actuator/health_check").permitAll()
//                    .anyRequest().authenticated(); // <-- 나머지는 인증 필요
//        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//    //tokenAuthenticationFilter() 구현필요 -> 한번 로그인하면 토큰을 주고받음. -> 토큰을 검증하는 필터사용
//}
