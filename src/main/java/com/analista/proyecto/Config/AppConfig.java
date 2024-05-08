// package com.analista.proyecto.Config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import com.analista.proyecto.Repository.IUsuarioRepository;

// import lombok.RequiredArgsConstructor;

// @Configuration
// @RequiredArgsConstructor
// public class AppConfig {

//     @Autowired
//     IUsuarioRepository usuarioRepository;

//     @Bean
//     public UserDetailsService userDetailsService(){
//         return username -> usuarioRepository.findByEmail(username)
//         .orElseThrow(() -> new UsernameNotFoundException("El usuario no se encontro"));

//     }

//     @Bean
//     public AuthenticationProvider authenticationProvider(){
//         DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//         authenticationProvider.setUserDetailsService(userDetailsService());
//         authenticationProvider.setPasswordEncoder(passwordEncoder());
//         return authenticationProvider;
        
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
//         return config.getAuthenticationManager();
//     }

      

// }
