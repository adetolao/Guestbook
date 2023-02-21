package com.adetola.guestbook.service;

import com.adetola.guestbook.entity.GuestbookReview;
import com.adetola.guestbook.entity.GuestbookUser;
import com.adetola.guestbook.exception.ResourceNotFoundException;
import com.adetola.guestbook.repository.GuestbookReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestbookReviewServiceImpl implements GuestbookReviewService {
    @Autowired
    private GuestbookReviewRepository reviewRepository;

    public GuestbookReviewServiceImpl(GuestbookReviewRepository userRepository) {
        super();
        this.reviewRepository = reviewRepository;
    }

    @Override
    public boolean checkReviewExists(GuestbookReview review) {
        List<GuestbookReview> allReviews = reviewRepository.findAll();

        for (GuestbookReview existReview : allReviews) {
            if (existReview.getUserId().equals(review.getUserId())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<GuestbookReview> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public GuestbookReview saveReview(GuestbookReview guestbookReview) {
        GuestbookReview existingReview = reviewRepository.findByUserId(guestbookReview.getUserId());
        if (null != existingReview) {
            throw new UsernameNotFoundException("Username already used.");
        }
        return reviewRepository.save(guestbookReview);
    }


    @Override
    public GuestbookReview getReviewById(String userId) {
        return reviewRepository.findByUserId(userId);
    }

    @Override
    public GuestbookReview approveReview(GuestbookReview review) {
        GuestbookReview existingReview = reviewRepository.findByUserId(review.getUserId());
        if (existingReview.getApprovalFlag().equals(review.getApprovalFlag())) {
            throw new UsernameNotFoundException("Username already used.");
        }
        return reviewRepository.save(review);
    }
}
