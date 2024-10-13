package com.example.recipes.controller;

import com.example.recipes.controller.dto.AuthResponse;
import com.example.recipes.controller.dto.Signin;
import com.example.recipes.controller.dto.Signup;
import com.example.recipes.model.Role;
import com.example.recipes.model.User;
import com.example.recipes.repository.RoleRepository;
import com.example.recipes.repository.UserRepository;
import com.example.recipes.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody Signin signin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signin.getUsername(), signin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody Signup signup) {
        if (userRepository.existsByUsername(signup.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(signup.getUsername());
        user.setPassword(passwordEncoder.encode(signup.getPassword()));
        Role role = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(role));
        user.setEnabled(true);
        userRepository.save(user);

        return new ResponseEntity<>("User signed up!", HttpStatus.OK);
    }
}
