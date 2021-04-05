package com.samsungsds.eshop.security;

import com.samsungsds.eshop.user.MyUserDetails;
import com.samsungsds.eshop.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@RequiredArgsConstructor
@Log4j2
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource(name="userService")
    private UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        // AuthenticaionFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
        String userEmail = token.getName();
        String userPw = (String) token.getCredentials();

        // UserDetailsService를 통해 DB에서 아이디로 사용자 조회
        MyUserDetails userDetails = userService.loadUserByUsername(userEmail);
        if (!passwordEncoder.matches(userPw, userDetails.getPassword())) {
            throw new BadCredentialsException(userDetails.getUsername() + "Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, userPw, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}