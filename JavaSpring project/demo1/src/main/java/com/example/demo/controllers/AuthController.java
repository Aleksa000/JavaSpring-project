package com.example.demo.controllers;

import com.example.demo.models.UserModel;
import com.example.demo.services.IUserService;
import com.example.demo.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final IUserService userService;
    private final PasswordEncoder passwordEncoderService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;

    public AuthController(IUserService userService,PasswordEncoder passwordEncoderService, AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil) {
        this.userService = userService;
        this.passwordEncoderService = passwordEncoderService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("registration")
    @CrossOrigin("*")
    public ResponseEntity<?> Registration(@RequestBody @Valid UserModel model, BindingResult result) {
        if (result.hasErrors())
        {
            return new ResponseEntity<String>("Failed registration!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        model.setPassword(passwordEncoderService.encode(model.getPassword()));
        try
        {
            var user = userService.CreateUser(model);
            return new ResponseEntity<String>("Successful registration!", HttpStatus.OK);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<String>("Failed registration!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("login")
    @CrossOrigin("*")
    private ResponseEntity<?> getResponseEntity(@RequestBody UserModel model) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(model.getEmail(), model.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect email or password", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(model.getEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }





}
