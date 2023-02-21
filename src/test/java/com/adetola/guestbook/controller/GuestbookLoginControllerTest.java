package com.adetola.guestbook.controller;

import com.adetola.guestbook.model.GuestbookUserDetail;
import com.adetola.guestbook.repository.GuestbookUserRepository;
import com.adetola.guestbook.service.GuestbookLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class GuestbookLoginControllerTest {
    @InjectMocks private GuestbookLoginController guestbookLoginController;

    @Mock
    GuestbookUserRepository userRepository;

    @Mock
    GuestbookLoginService guestbookLoginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

}
