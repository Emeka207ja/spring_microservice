package com.imprint.security.Service;

import com.imprint.security.Dto.LoginDto;
import com.imprint.security.Dto.LoginResponse;
import com.imprint.security.Dto.SignupDto;

public interface AuthenticationService {
    public LoginResponse createAccount(SignupDto signupDto);
    public  LoginResponse signIn(LoginDto loginDto);
}
