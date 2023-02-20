package com.adetola.guestbook.service;

import com.adetola.guestbook.entity.GuestbookUser;
import com.adetola.guestbook.model.GuestbookUserDetail;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface GuestbookLoginService extends UserDetailsService {
    GuestbookUser save(GuestbookUserDetail guestbookUserDetail);

    public boolean checkUserExist(GuestbookUser User);

    public GuestbookUser saveUser(GuestbookUser user);

    public List<GuestbookUser> getAllUsers();

    public GuestbookUser getUserById(Long id);

    public GuestbookUser updateUser(GuestbookUser user, Long id);

    public void deleteUser(Long id);

    public GuestbookUser changeUserPrivilege(GuestbookUser user, Long adminId, Long id);

    public GuestbookUser authenticateUser(GuestbookUser user);
}
