package com.zhuravishkin.springbootoracledbplsqlprocedure.service;

import com.zhuravishkin.springbootoracledbplsqlprocedure.model.User;
import com.zhuravishkin.springbootoracledbplsqlprocedure.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @SpyBean
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test
    void findAllByPhoneNumber() {
        when(repository.findAllByPhoneNumber(anyString())).thenReturn(new ArrayList<>());
        List<User> users = service.findAllByPhoneNumber("");
        assertNotNull(users);
        verify(repository, times(1)).findAllByPhoneNumber(anyString());
    }
}