package com.adetola.guestbook.repository;

import com.adetola.guestbook.entity.GuestbookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestbookUserRepository extends JpaRepository<GuestbookUser, Long> {
    GuestbookUser findByEmail(String email);
}
