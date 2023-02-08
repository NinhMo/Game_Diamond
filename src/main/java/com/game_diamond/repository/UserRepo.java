package com.game_diamond.repository;

import com.game_diamond.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface UserRepo extends JpaRepository<User,Long> {

    List<User> findAll();

    Optional<User> findByUserNameOrEmail( String userName, String email);

    Optional<User> findByUserName(String userName);


    Boolean existsByUserNameOrEmail(String userName, String email);


}
