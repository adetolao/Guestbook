package com.adetola.guestbook.service;

import com.adetola.guestbook.entity.GuestbookRole;
import com.adetola.guestbook.entity.GuestbookUser;
import com.adetola.guestbook.exception.ResourceNotFoundException;
import com.adetola.guestbook.model.GuestbookUserDetail;
import com.adetola.guestbook.repository.GuestbookUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestbookLoginServiceImpl implements GuestbookLoginService {
    @Autowired
    private GuestbookUserRepository userRepository;

    public GuestbookLoginServiceImpl(GuestbookUserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean checkUserExists(GuestbookUser user) {
        GuestbookUser existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            return true;
        }

        return false;
    }

    @Override
    public List<GuestbookUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public GuestbookUser getUserById(Long id) {
        GuestbookUser existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("GuestbookUser", "Id", id));

        return existingUser;
    }

    @Override
    public GuestbookUser updateUser(GuestbookUser user, Long id) {
        // we need to check whether GuestbookUser with given username exists in DB or not
        GuestbookUser existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("GuestbookUser", "Id", id));

        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());

        // save existing user to DB
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUser(Long id) {

        // check whether a GuestbookUser exist in a DB or not
        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("GuestbookUser", "Id", id));
        userRepository.deleteById(id);
    }

    @Override
    public GuestbookUser changeUserPrivilege(GuestbookUser user, Long adminId, Long id) {
        GuestbookUser adminUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("AdminUser", "adminId", adminId));

        GuestbookUser existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("GuestbookUser", "Id", id));

        // save existing user to DB
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public GuestbookUser authenticateUser(GuestbookUser user) {
        List<GuestbookUser> allUsers = userRepository.findAll();

        for (GuestbookUser existingUser : allUsers) {
            if (existingUser.getEmail().equals(user.getEmail())) {
                if (passwordEncoder.matches(existingUser.getPassword(), user.getPassword())) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public GuestbookUser saveUser(GuestbookUserDetail guestbookUserDetail) {
        GuestbookUser existingUser = userRepository.findByEmail(guestbookUserDetail.getEmail());
        if (null != existingUser) {
            throw new UsernameNotFoundException("Username already used.");
        }
        GuestbookUser user = createUserFromDetail(guestbookUserDetail);
        return userRepository.save(user);
    }

    @Override
    public GuestbookUser createUserFromDetail(GuestbookUserDetail guestbookUserDetail) {
        return new GuestbookUser(guestbookUserDetail.getFirstName(),
                guestbookUserDetail.getLastName(), guestbookUserDetail.getEmail(),
                passwordEncoder.encode(guestbookUserDetail.getPassword()),
                Arrays.asList(new GuestbookRole(1L, "ROLE_USER")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        GuestbookUser user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToUsers(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToUsers(Collection<GuestbookRole> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}