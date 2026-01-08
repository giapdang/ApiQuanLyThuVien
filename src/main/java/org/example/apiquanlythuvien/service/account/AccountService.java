package org.example.apiquanlythuvien.service.account;

import org.example.apiquanlythuvien.data.entity.Account;
import org.example.apiquanlythuvien.data.request.AccountQuanTriRequest;
import org.example.apiquanlythuvien.data.request.AccountRequest;
import org.example.apiquanlythuvien.data.request.LoginRequest;
import org.example.apiquanlythuvien.data.response.LoginResponse;

public interface AccountService {

  void createAccount(AccountRequest request);

  void createAccountQuanTri(AccountQuanTriRequest request);

  LoginResponse login(LoginRequest request);
}
