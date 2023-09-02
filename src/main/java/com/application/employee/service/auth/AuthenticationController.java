package com.application.employee.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

@PostMapping("/resetPassword/{userId}")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<String> resetPassword(@PathVariable String userId) {
    ResponseEntity<String> response = service.reset(userId);

    if (response.getStatusCode() == HttpStatus.CREATED) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Password Reset");
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Password Reset Failed");
    }
}

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(String userId, String password){
        return service.updatePassword(userId, password);
    }

//@PostMapping("/updatePassword/{id}/{password}")
//public ResponseEntity<String> updatePassword(@PathVariable String id, @PathVariable String password) {
//    ResponseEntity<String> response = service.updatePassword(id, password);
//    if (response.getStatusCode() == HttpStatus.CREATED) {
//        return ResponseEntity.status(HttpStatus.CREATED).body("Password updated");
//    } else {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Password update failed");
//    }
//}

}
