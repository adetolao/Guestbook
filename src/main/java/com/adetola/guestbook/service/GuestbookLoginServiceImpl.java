package com.adetola.guestbook.service;

import com.adetola.guestbook.entity.GuestbookRole;
import com.adetola.guestbook.entity.GuestbookUser;
import com.adetola.guestbook.exception.ResourceNotFoundException;
import com.adetola.guestbook.model.GuestbookUserDetail;
import com.adetola.guestbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GuestbookLoginServiceImpl implements GuestbookLoginService {
    @Autowired
    private UserRepository userRepository;

    public GuestbookLoginServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

/*
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
*/

    @Override
    public boolean checkUserExist(GuestbookUser user) {
        List<GuestbookUser> allUsers = userRepository.findAll();

        for (GuestbookUser existUser : allUsers) {
            if (existUser.getEmail().equals(user.getEmail())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public GuestbookUser saveUser(GuestbookUser user) {
/*        GuestbookPasswordEncoder passwordEncoder = new GuestbookPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());

        System.out.println("=======================["+hashedPassword+"]========================");
        user.setPassword(hashedPassword);
 */
        return userRepository.save(user);
    }

    @Override
    public List<GuestbookUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public GuestbookUser getUserById(Long id) {
        return userRepository.getById(id);
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
    public GuestbookUser save(GuestbookUserDetail guestbookUserDetail) {
        GuestbookUser user = new GuestbookUser(1L, guestbookUserDetail.getFirstName(),
                guestbookUserDetail.getLastName(), guestbookUserDetail.getEmail(),
                guestbookUserDetail.getPassword(), Arrays.asList(new GuestbookRole(1L, "ROLE_USER")));
        return userRepository.save(user);
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

/*
        if (adminUser.getRoles()..equalsIgnoreCase("admin")) {
            throw new UserNoPrivilegeException("AdminUser", "admin", adminId);
        }
*/

        GuestbookUser existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("GuestbookUser", "Id", id));

/*
        if (existingUser.getAccessLevel().equals(user.getAccessLevel())) {
            existingUser.setAccessLevel(user.getAccessLevel());
        }
*/

        // save existing user to DB
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public GuestbookUser authenticateUser(GuestbookUser user) {
        List<GuestbookUser> allUsers = userRepository.findAll();
//        GuestbookPasswordEncoder passwordEncoder = new GuestbookPasswordEncoder();

        for (GuestbookUser existingUser : allUsers) {
            if (existingUser.getEmail().equals(user.getEmail())) {
                if (existingUser.getPassword().equals(user.getPassword())) {
                    return user;
                }
            }
        }
        return null;
    }

/*
    public boolean matchPassword (String inputPassword, String storedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(inputPassword, storedPassword);
    }
*/

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