package com.zhuravishkin.springbootoracledbplsqlprocedure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuravishkin.springbootoracledbplsqlprocedure.model.RespondingServerException;
import com.zhuravishkin.springbootoracledbplsqlprocedure.model.User;
import com.zhuravishkin.springbootoracledbplsqlprocedure.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("user")
public class Controller {
    private final UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/email/{phoneNumber}")
    public ResponseEntity<String> getEmail(@PathVariable("phoneNumber") String phoneNumber) {
        try {
            Long.valueOf(phoneNumber);
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
            throw RespondingServerException.builder()
                    .serviceName("user-email-info")
                    .errorCode(HttpStatus.BAD_REQUEST.value())
                    .userMessage(e.getMessage())
                    .errorDetail(e.getClass().getCanonicalName())
                    .build();
        }
        log.info("Controller start...");
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = userService.findAllByPhoneNumber(phoneNumber);
        log.info("Upload objects completed successfully");
        String string = null;
        try {
            string = objectMapper.writeValueAsString(users);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(string, HttpStatus.OK);
    }
}
