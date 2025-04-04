package ua.com.blizartproduction.infohub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder; // Імпорт додано
import org.springframework.web.bind.annotation.*;
import ua.com.blizartproduction.infohub.dto.ApiResponseDto;
import ua.com.blizartproduction.infohub.dto.LoginRequest;
import ua.com.blizartproduction.infohub.dto.LoginResponse;
import ua.com.blizartproduction.infohub.security.JwtUtils;
import java.util.Map; // Імпорт додано

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder; // Ін'єкція додана

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Login attempt for username: {}", loginRequest.getUsername());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            log.info("Authentication successful for user: {}", loginRequest.getUsername());
        } catch (BadCredentialsException e) {
            log.warn("Authentication failed for user {}: Invalid credentials", loginRequest.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponseDto(false, "Неправильний логін або пароль"));
        } catch (Exception e) {
            log.error("Authentication failed for user {}: {}", loginRequest.getUsername(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDto(false, "Помилка автентифікації"));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);
        log.info("JWT generated successfully for user: {}", loginRequest.getUsername());

        return ResponseEntity.ok(new LoginResponse(jwt));
    }

    @GetMapping("/hash/{password}")
    public ResponseEntity<Map<String, String>> getPasswordHash(@PathVariable String password) {
        String hashedPassword = passwordEncoder.encode(password);
        log.info("Generated hash for password '{}': {}", password, hashedPassword);
        return ResponseEntity.ok(Map.of("rawPassword", password, "hashedPassword", hashedPassword));
    }
}