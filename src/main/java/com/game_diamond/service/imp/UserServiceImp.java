package com.game_diamond.service.imp;

import com.game_diamond.jwt.JwtUtils;
import com.game_diamond.payload.request.LoginReq;
import com.game_diamond.payload.request.SignUpReq;
import com.game_diamond.entities.User;
import com.game_diamond.payload.response.LoginRes;
import com.game_diamond.repository.UserRepo;
import com.game_diamond.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepo userRepo;
    private JwtUtils jwtUtil;


    @Override
    public User getByUseNameOrEmail(String userName, String email) {
        Optional<User> userOp = userRepo.findByUserNameOrEmail(userName,email);
        return userOp.orElse(null);
    }

    @Override
    public User register(SignUpReq singup) {
        if(userRepo.existsByUserNameOrEmail(singup.getUsername(), singup.getEmail())){
            return null;
        }
        User user = new User();
        user.setUserName(singup.getUsername());
        user.setEmail(singup.getEmail());
        user.setPassword(MD5(singup.getPassword()));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatesAt(new Timestamp(System.currentTimeMillis()));
        return userRepo.save(user);
    }

    @Override
    public LoginRes login(LoginReq login) {
        String dbPassword;
        String passwordReq;
        Optional<User> userOp = userRepo.findByUserName(login.getUserName());
        if (userOp.isPresent()){
           dbPassword= userOp.get().getPassword();
            passwordReq=MD5(login.getPassword());
            if (dbPassword.equals(passwordReq)){
                String token = jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(userOp.get().getUserName(), "", new ArrayList<>()));
                return new LoginRes(token, userOp.get().getId());
            }
            return null;
        }
        return null;
    }

    @Override
    public User findByUserId(Long userId) {
        Optional<User> userOp = userRepo.findById(userId);
        return userOp.orElse(null);
    }

    private String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
