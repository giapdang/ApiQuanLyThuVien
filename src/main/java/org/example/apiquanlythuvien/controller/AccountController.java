package org.example.apiquanlythuvien.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.apiquanlythuvien.data.request.AccountQuanTriRequest;
import org.example.apiquanlythuvien.data.request.AccountRequest;
import org.example.apiquanlythuvien.data.request.LoginRequest;
import org.example.apiquanlythuvien.data.response.LoginResponse;
import org.example.apiquanlythuvien.service.account.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping("/create/user")
  public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequest request) {
    accountService.createAccount(request);
    return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
  }

  @PostMapping("/create/admin")
  public ResponseEntity<?> createAccountQuanTri(@Valid @RequestBody AccountQuanTriRequest request) {
    accountService.createAccountQuanTri(request);
    return new ResponseEntity<>("Admin account created successfully", HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    LoginResponse response = accountService.login(request);
    return ResponseEntity.ok(response);
  }
}
