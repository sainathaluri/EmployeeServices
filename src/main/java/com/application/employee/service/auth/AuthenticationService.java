package com.application.employee.service.auth;

import com.application.employee.service.config.JwtService;
import com.application.employee.service.user.Role;
import com.application.employee.service.user.User;
import com.application.employee.service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationProvider authenticationProvider;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .id(UUID.randomUUID().toString())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .role(request.getRole())
                .build();
        if(request.getTempPassword() != null)
            user.setTempPassword(passwordEncoder.encode(request.getTempPassword()));
        if(request.getPassword() != null)
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().build();
    }

    public ResponseEntity<String> reset(String userId) {
        var user = repository.findById(userId);
        String tempPassword = UUID.randomUUID().toString();
        System.out.println("Temp Password:"+tempPassword);
        user.setTempPassword(passwordEncoder.encode(tempPassword));
        repository.save(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Password Reset");
    }

    public ResponseEntity<String> updatePassword(String userId, String password) {
        var user = repository.findById(userId);
        user.setPassword(passwordEncoder.encode(password));
        user.setTempPassword(null);
        repository.save(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Password updated");
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        if(user.getTempPassword() != null){
            user.setPassword(user.getTempPassword());
        }

        var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .id(user.getId())
                    .role(user.getRole())
                    .build();
    }

}
