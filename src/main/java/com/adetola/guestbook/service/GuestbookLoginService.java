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

    public Iterable<GuestbookUser> findAll() ;

    public Optional<GuestbookUser> findById(Integer id) ;

    public boolean checkUserExist(GuestbookUser User);

    public GuestbookUser saveUser(GuestbookUser user);

    public List<GuestbookUser> getAllUsers();

    public GuestbookUser getUserById(Integer id);

    public GuestbookUser updateUser(GuestbookUser user, Integer id);

    public void deleteUser(Integer id);

    public GuestbookUser changeUserPrivilege(GuestbookUser user, Integer adminId, Integer id);

    public GuestbookUser authenticateUser(GuestbookUser user);
}
