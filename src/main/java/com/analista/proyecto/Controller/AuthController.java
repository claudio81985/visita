// package com.analista.proyecto.Controller;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.analista.proyecto.Controller.Models.AuthResponse;
// import com.analista.proyecto.Controller.Models.AuthenticateRequest;
// import com.analista.proyecto.Controller.Models.RegistroRequest;
// import com.analista.proyecto.Service.AuthService;

// import lombok.RequiredArgsConstructor;

// @RestController
// @RequestMapping("/api/auth")
// @RequiredArgsConstructor
// public class AuthController {

//     @Autowired
//     AuthService authService;

//     @PostMapping("/registro")
//     public ResponseEntity<AuthResponse> registro(@RequestBody RegistroRequest request){
//         return ResponseEntity.ok(authService.registro(request));
//     }

//     @PostMapping("/authenticate")
//     public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticateRequest request){
//         return ResponseEntity.ok(authService.authenticate(request));
//     }

// }
