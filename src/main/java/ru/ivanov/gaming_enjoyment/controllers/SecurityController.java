package ru.ivanov.gaming_enjoyment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.gaming_enjoyment.dto.AuthDto;
import ru.ivanov.gaming_enjoyment.dto.UserDto;
import ru.ivanov.gaming_enjoyment.security.JwtUtils;
import ru.ivanov.gaming_enjoyment.services.intrf.UserService;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SecurityController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        userDto = userService.registerUser(userDto);
        return ResponseEntity.ok(jwtUtils.  generateToken(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDto authDto) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Incorrect credentials!");
        }

        String token = jwtUtils.generateToken(userService.findUserByUsername(authDto.getUsername()));
        return ResponseEntity.ok(token);
    }
}
