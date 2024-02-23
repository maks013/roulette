package com.casino.user;

import com.casino.infrastructure.security.jwt.JwtAuthenticator;
import com.casino.user.domain.UserFacade;
import com.casino.user.dto.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final JwtAuthenticator jwtAuthenticator;
    private final UserFacade userFacade;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        final LoginResponseDto loginResponseDto = jwtAuthenticator.authenticate(loginRequestDto);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResultDto> register(@Valid @RequestBody RegisterDto registerDto) {
        RegistrationResultDto registerResult = userFacade.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResult);
    }
}
