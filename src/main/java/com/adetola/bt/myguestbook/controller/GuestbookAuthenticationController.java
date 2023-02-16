package com.adetola.bt.myguestbook.controller;

import com.adetola.bt.myguestbook.entity.AuthenticationRequest;
import com.adetola.bt.myguestbook.entity.AuthenticationResponse;
import com.adetola.bt.myguestbook.entity.RegisterRequest;
import com.adetola.bt.myguestbook.service.GuestbookAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class GuestbookAuthenticationController {

    private final GuestbookAuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
