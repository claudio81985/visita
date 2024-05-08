// package com.analista.proyecto.Config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// import org.springframework.security.web.util.matcher.OrRequestMatcher;
// import org.springframework.security.web.util.matcher.RequestMatcher;

// import lombok.RequiredArgsConstructor;

// @Configuration
// @EnableWebSecurity
// @RequiredArgsConstructor
// @EnableMethodSecurity
// public class SecurityConfig {


//    private final JwtFilter jwtFilter;

//     @Autowired
//     AuthenticationProvider authenticationProvider;

//     @Bean   
//     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

//         httpSecurity
//                     .authorizeHttpRequests(auth -> auth.requestMatchers(EndpointsPublicas()).permitAll()
//                     .anyRequest().authenticated())
//                     .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                     .authenticationProvider(authenticationProvider)
//                     .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//                     return httpSecurity.build();

//     }

//     private RequestMatcher EndpointsPublicas(){
//         return new OrRequestMatcher(
//             new AntPathRequestMatcher("/layout/layout"),
//             new AntPathRequestMatcher("/home"),
//             new AntPathRequestMatcher("/login"),
//             new AntPathRequestMatcher("api/auth/**")
//         );
    
//     }
// }
