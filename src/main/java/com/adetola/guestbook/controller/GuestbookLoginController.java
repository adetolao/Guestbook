package com.adetola.guestbook.controller;

import com.adetola.guestbook.model.GuestbookUserDetail;
import com.adetola.guestbook.repository.GuestbookUserRepository;
import com.adetola.guestbook.service.GuestbookLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/guestbook/registration")
public class GuestbookLoginController {
    private GuestbookUserRepository userRepository;

    @Autowired
    private GuestbookLoginService userService;

    public GuestbookLoginController(GuestbookLoginService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("guestbook_user")
    public GuestbookUserDetail userRegistrationDetail() {
        return new GuestbookUserDetail();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    @ResponseBody
    public String registerUserAccount(@ModelAttribute("guestbook_user") GuestbookUserDetail guestbookUserDetail)
            throws UsernameNotFoundException {
        userService.saveUser(guestbookUserDetail);
        return "redirect:/guestbook/registration?success";
    }
}
