package com.imprint.security.Service;

import com.imprint.security.Dto.LoginDto;
import com.imprint.security.Dto.LoginResponse;
import com.imprint.security.Dto.SignupDto;
import com.imprint.security.Model.Role;
import com.imprint.security.Model.UserModel;
import com.imprint.security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public LoginResponse createAccount(SignupDto signupDto) {
        return null;
    }

    @Override
    public LoginResponse signIn(LoginDto loginDto) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = this.userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("invalid username or password"));

        return new User(user.getUsername(),user.getPassword(),mapRolesToAuthority(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthority(Set<Role> roles){
        return roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
