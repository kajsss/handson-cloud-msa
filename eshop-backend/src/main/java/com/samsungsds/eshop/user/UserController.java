package com.samsungsds.eshop.user;

import com.samsungsds.eshop.security.AuthConstants;
import com.samsungsds.eshop.security.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
@Log4j2
public class UserController {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping(value = "signUp")
    public ResponseEntity signUp(@RequestBody User user) {
        user.setRole(UserRole.ROLE_USER);
        user.setPw(passwordEncoder.encode(user.getPw()));
        return userService.findByEmail(user.getEmail()).isPresent()
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(TokenUtils.generateJwtToken(userService.signUp(user)));
    }

    @GetMapping(value = "loginSuccess")
    public ResponseEntity loginSuccess(@RequestHeader(AuthConstants.AUTH_HEADER) String token) {
        log.debug("aefafw");
        return ResponseEntity.ok(token);
    }


    @GetMapping(value = "/findAll")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

}