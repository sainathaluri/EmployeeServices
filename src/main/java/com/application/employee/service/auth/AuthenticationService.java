package com.application.employee.service.auth;

import com.application.employee.service.config.JwtService;
import com.application.employee.service.user.Role;
import com.application.employee.service.user.User;
import com.application.employee.service.repositories.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
    private final AuthenticationProvider authenticationProvider;
    private final JavaMailSender mailSender;
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .id(UUID.randomUUID().toString())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .role(request.getRole())
                .build();
        String tempPassword = UUID.randomUUID().toString();
        user.setTempPassword(passwordEncoder.encode(tempPassword));
        sendTemporaryPasswordEmail(user.getEmail(), tempPassword);

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().build();
    }

    public ResponseEntity<String> reset(String email) {
        var user = repository.findByEmail(email);
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with given emailID");
        }
        String tempPassword = UUID.randomUUID().toString();
        System.out.println("Temp Password:" + tempPassword);
        user.get().setTempPassword(passwordEncoder.encode(tempPassword));
        sendTemporaryPasswordEmail(user.get().getEmail(), tempPassword);
        repository.save(user.get());
        return ResponseEntity.status(HttpStatus.CREATED).body("Password Reset");
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
        boolean tempPassword = StringUtils.hasText(user.getTempPassword());
        if(user.getTempPassword() != null){
            user.setPassword(user.getTempPassword());
        }

        var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .id(user.getId())
                    .tempPassword(tempPassword)
                    .role(user.getRole())
                    .build();
    }

    public void sendTemporaryPasswordEmail(String toEmail, String temporaryPassword) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Temporary Password");
            helper.setText("Your temporary password is: " + temporaryPassword);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
