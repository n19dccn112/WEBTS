package com.nhom10.webts.controller;

import com.nhom10.webts.model.LoginRequest;
import com.nhom10.webts.model.SignupRequest;
import com.nhom10.webts.service.NguoiDungService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600) //cho phép http, get post..
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final NguoiDungService service;

    public AuthController(NguoiDungService service) {
        this.service = service;
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return service.checkLogin(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        return service.register(signUpRequest);
    }
}
