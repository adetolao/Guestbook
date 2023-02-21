package com.adetola.guestbook.service;

import com.adetola.guestbook.entity.GuestbookUser;
import com.adetola.guestbook.model.GuestbookUserDetail;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface GuestbookLoginService extends UserDetailsService {
    public boolean checkUserExists(GuestbookUser User);

    public GuestbookUser saveUser(GuestbookUserDetail guestbookUserDetail);

    public List<GuestbookUser> getAllUsers();

    public GuestbookUser getUserById(Long id);

    public GuestbookUser updateUser(GuestbookUser user, Long id);

    public void deleteUser(Long id);

    public GuestbookUser changeUserPrivilege(GuestbookUser user, Long adminId, Long id);

    public GuestbookUser createUserFromDetail (GuestbookUserDetail guestbookUserDetail);

    public GuestbookUser authenticateUser(GuestbookUser user);
}
