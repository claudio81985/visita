// package com.analista.proyecto.Service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.analista.proyecto.Config.JwtService;
// import com.analista.proyecto.Controller.Models.AuthResponse;
// import com.analista.proyecto.Controller.Models.AuthenticateRequest;
// import com.analista.proyecto.Controller.Models.RegistroRequest;
// import com.analista.proyecto.Model.Role;
// import com.analista.proyecto.Model.UsuarioEntity;
// import com.analista.proyecto.Repository.IUsuarioRepository;

// @Service
// public class AuthServiceImpl implements AuthService{

//     @Autowired
//     IUsuarioRepository usuarioRepository;

//     @Autowired
//     PasswordEncoder passwordEncoder;

//     @Autowired
//     JwtService jwtService;

//     @Autowired
//     AuthenticationManager authenticationManager;

//     @Override
//     public AuthResponse registro(RegistroRequest request) {
//         var usuario = UsuarioEntity.builder()
//         .email(request.getEmail())
//         .nombre(request.getNombre())
//         .password(passwordEncoder.encode(request.getPassword()))
//         .role(Role.USER)
//         .build();

//         usuarioRepository.save(usuario);
//         var jwtToken = jwtService.generarToken(usuario);

//         return AuthResponse.builder()
//         .token(jwtToken).build();
//     }

//     @Override
//     public AuthResponse authenticate(AuthenticateRequest request) {
//         authenticationManager.authenticate(
//             new UsernamePasswordAuthenticationToken(
//                 request.getEmail(),
//                 request.getPassword()
//             )
//         );

//         var usuario = usuarioRepository.findByEmail(request.getEmail())
//                 .orElseThrow();
//         var jwtToken = jwtService.generarToken(usuario);

//         return AuthResponse.builder().token(jwtToken).build();
        
//     }

// }
