package com.zhuravishkin.springbootoracledbplsqlprocedure.repository;

import com.zhuravishkin.springbootoracledbplsqlprocedure.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByPhoneNumber(String phoneNumber);
}
