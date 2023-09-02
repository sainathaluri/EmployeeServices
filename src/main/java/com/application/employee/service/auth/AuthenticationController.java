package com.application.employee.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
//        return ResponseEntity.ok(service.register(request));
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
        @RequestBody AuthenticationRequest request
    ) {
    AuthenticationResponse response = service.authenticate(request);
    if (response == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(response);
    }

    @PostMapping("/resetPassword")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> restPassword(String userId){
        return service.reset(userId);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(String userId, String password){
        return service.updatePassword(userId, password);
    }

}
