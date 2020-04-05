package com.fatihmayuk.kartaca.backend.controller;

import com.fatihmayuk.kartaca.backend.dto.LoginRequestDto;
import com.fatihmayuk.kartaca.backend.dto.RegistrationRequestDto;
import com.fatihmayuk.kartaca.backend.dto.TokenResponseDto;
import com.fatihmayuk.kartaca.backend.model.User;
import com.fatihmayuk.kartaca.backend.repository.UserRepository;
import com.fatihmayuk.kartaca.backend.security.JwtTokenUtil;
import com.fatihmayuk.kartaca.backend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/token")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AccountController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto request) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final User user = userRepository.findByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        System.out.println(token);
        return ResponseEntity.ok(new TokenResponseDto(user.getUsername(),token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Boolean> register(@RequestBody RegistrationRequestDto registrationRequest) throws AuthenticationException {
        Boolean response = userServiceImpl.register(registrationRequest);
        return ResponseEntity.ok(response);
    }



}
