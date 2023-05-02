package com.pli.codes.platepal.controller;

import com.pli.codes.platepal.model.entity.Account;
import com.pli.codes.platepal.payload.request.LoginRequest;
import com.pli.codes.platepal.payload.response.JwtResponse;
import com.pli.codes.platepal.security.jwt.JwtUtils;
import com.pli.codes.platepal.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final AccountService accountService;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    public AuthController(
            AuthenticationManager authenticationManager,
            AccountService accountService,
            PasswordEncoder encoder,
            JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registerUser(@RequestBody LoginRequest registerRequest) {
        if (accountService.userExists(registerRequest.getEmailAddress())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Create new user's account
        Account account = new Account(registerRequest.getEmailAddress(),
                encoder.encode(registerRequest.getPassword()));
        accountService.createUser(account);

        return login(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
