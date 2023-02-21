package com.adetola.guestbook.controller;

import com.adetola.guestbook.entity.GuestbookUser;
import com.adetola.guestbook.model.GuestbookUserDetail;
import com.adetola.guestbook.repository.GuestbookUserRepository;
import com.adetola.guestbook.service.GuestbookLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/guestbook")
public class GuestbookLoginController {
    private GuestbookUserRepository userRepository;

    @Autowired
    private GuestbookLoginService userService;

    private GuestbookUser guestbookUser;

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

    @PostMapping(value = "/login")
    @ResponseBody
    public String loginUserAccount(@ModelAttribute("guestbook_user") GuestbookUser guestbookUser)
            throws UsernameNotFoundException {
        if (userService.checkUserExists(guestbookUser)) {
            return "redirect:/guestbook/review?userId=" + guestbookUser.getEmail();
        }
        else {
            return "login";
        }
    }

    @PostMapping(value = "/registration")
    @ResponseBody
    public String registerUserAccount(@ModelAttribute("guestbook_user") GuestbookUserDetail guestbookUserDetail)
            throws UsernameNotFoundException {
        guestbookUser = userService.saveUser(guestbookUserDetail);
        return "redirect:/guestbook/registration?success";
    }
}
