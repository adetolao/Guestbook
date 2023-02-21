package com.adetola.guestbook.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="guestbook_review")
public class GuestbookReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "review_text", nullable = true)
    private String reviewText;
    @Column(name = "review_image", nullable = true)
    private String reviewImage;
    @Column(name = "approval_flag", nullable = true)
    private Boolean approvalFlag;

    public GuestbookReview(String userId, String reviewText, String reviewImage, Boolean approvalFlag) {
        super();
        this.userId = userId;
        this.reviewText = reviewText;
        this.reviewImage = reviewImage;
        this.approvalFlag = approvalFlag;
    }
}
