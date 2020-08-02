package com.zhuravishkin.springbootoracledbplsqlprocedure.service;

import com.zhuravishkin.springbootoracledbplsqlprocedure.model.User;
import com.zhuravishkin.springbootoracledbplsqlprocedure.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllByPhoneNumber(String phoneNumber) {
        return userRepository.findAllByPhoneNumber(phoneNumber);
    }
}
