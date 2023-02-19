package com.adetola.guestbook.controller;

import com.adetola.guestbook.entity.GuestbookUser;
import com.adetola.guestbook.service.GuestbookLoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class GuestbookLoginControllerTest {
    @InjectMocks private GuestbookLoginController guestbookLoginController;

    @MockBean
    GuestbookLoginService guestbookLoginService;

    @Test
    void testAddUserGuestbookLoginService () {
        GuestbookUser user = GuestbookUser.builder().firstName("adetola")
                .lastName("adesanya")
                .password("adesanya")
                .email("adetola@example.com").build();
    }
}
