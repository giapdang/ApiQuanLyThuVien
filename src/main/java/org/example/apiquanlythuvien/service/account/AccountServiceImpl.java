package org.example.apiquanlythuvien.service.account;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.apiquanlythuvien.data.entity.Account;
import org.example.apiquanlythuvien.data.entity.DocGia;
import org.example.apiquanlythuvien.data.entity.TheThuVien;
import org.example.apiquanlythuvien.data.request.AccountQuanTriRequest;
import org.example.apiquanlythuvien.data.request.AccountRequest;
import org.example.apiquanlythuvien.data.request.LoginRequest;
import org.example.apiquanlythuvien.data.response.LoginResponse;
import org.example.apiquanlythuvien.exception.NotFoundException;
import org.example.apiquanlythuvien.mapper.AccountMapper;
import org.example.apiquanlythuvien.responsitory.AccountRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  @Override
  @Transactional
  public void createAccount(AccountRequest request) {

    if (accountRepository.existsByUsername(request.getUsername())) {
      log.error("Username {} already exists", request.getUsername());
      throw new NotFoundException("Username already exists");
    }

    Account account = accountMapper.toAccountEntity(request);

    account.setPassword(passwordEncoder.encode(request.getPassword()));

    DocGia docGia = accountMapper.toDocGiaEntity(request);

    TheThuVien theThuVien = accountMapper.toTheThuVienEntity(request);

    docGia.setAccount(account);
    theThuVien.setDocGia(docGia);
    docGia.setTheThuVien(theThuVien);
    account.setDocGia(docGia);

    Account savedAccount = accountRepository.save(account);

    log.info("Successfully created account with ID: {}", savedAccount.getAccountId());
  }

  @Override
  public void createAccountQuanTri(AccountQuanTriRequest request) {

    if (accountRepository.existsByUsername(request.getUsername())) {
      log.error("Username {} already exists", request.getUsername());
      throw new NotFoundException("Username already exists");
    }

    Account account = accountMapper.toAccountQuanTriEntity(request);
    account.setPassword(passwordEncoder.encode(request.getPassword()));
    accountRepository.save(account);
  }

  @Override
  public LoginResponse login(LoginRequest request) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

    Account account = userDetails.getAccount();

    log.info("User {} logged in successfully", account.getUsername());

    LoginResponse response = new LoginResponse();
    response.setAccountId(account.getAccountId());
    response.setRole(account.getRole());

    return response;
  }

}
