package com.samsungsds.eshop.user;

import com.samsungsds.eshop.security.UserNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service("userService")
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public MyUserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email).map(u -> new MyUserDetails(u,
                Collections.singleton(new SimpleGrantedAuthority(u.getRole().getValue())))).orElseThrow(() -> new UserNotFoundException(email));
    }

}