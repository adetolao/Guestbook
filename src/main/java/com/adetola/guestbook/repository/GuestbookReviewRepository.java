package com.adetola.guestbook.repository;

import com.adetola.guestbook.entity.GuestbookReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestbookReviewRepository extends JpaRepository<GuestbookReview, String> {
    GuestbookReview findByUserId(String userId);
}