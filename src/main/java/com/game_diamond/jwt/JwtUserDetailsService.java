package com.game_diamond.jwt;

import com.game_diamond.entities.User;
import com.game_diamond.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOp = userRepo.findByUserName(username);
        return userOp.map(user -> new org.springframework.security.core.userdetails.User(user.getUserName(), "", new ArrayList<>())).orElse(null);
    }
}
