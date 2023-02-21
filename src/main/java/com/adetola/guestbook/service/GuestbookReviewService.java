package com.adetola.guestbook.service;

import com.adetola.guestbook.entity.GuestbookReview;

import java.util.List;

public interface GuestbookReviewService {
    public boolean checkReviewExists(GuestbookReview review);

    public GuestbookReview saveReview(GuestbookReview review);

    public List<GuestbookReview> getAllReviews();

    public GuestbookReview getReviewById(String userId);

    public GuestbookReview approveReview(GuestbookReview review);
}
