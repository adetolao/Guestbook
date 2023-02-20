package com.adetola.guestbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guestbook_user")
public class GuestbookUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "guestbook_user_role",
            joinColumns = @JoinColumn(
                    name = "guestbook_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "guestbook_role_id", referencedColumnName = "id"))
    private Collection<GuestbookRole> roles;
 }
