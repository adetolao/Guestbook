package com.adetola.guestbook.repository;

import com.adetola.guestbook.entity.GuestbookUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<GuestbookUser, Integer> {
    GuestbookUser findByEmail(String email);
}
