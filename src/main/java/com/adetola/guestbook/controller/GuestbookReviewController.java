package com.adetola.guestbook.controller;

import com.adetola.guestbook.entity.GuestbookReview;
import com.adetola.guestbook.model.GuestbookUserDetail;
import com.adetola.guestbook.repository.GuestbookReviewRepository;
import com.adetola.guestbook.service.GuestbookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GuestbookReviewController {
    private GuestbookReviewRepository userRepository;

    @Autowired
    private GuestbookReviewService reviewService;

    public GuestbookReviewController(GuestbookReviewService reviewService) {
        super();
        this.reviewService = reviewService;
    }

    @ModelAttribute("guestbook_review")
    public GuestbookReview userRegistrationDetail() {
        return new GuestbookReview();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "review";
    }

    @PostMapping(value = "/guestbook/review/")
    public String addUserReview(@ModelAttribute("guestbook_review") GuestbookReview guestbookReview) {
 //       reviewService.saveReview(guestbookReview);
        return "redirect:/review?success";
    }
}
