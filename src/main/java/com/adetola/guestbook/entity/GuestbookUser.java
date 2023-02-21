package com.adetola.guestbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Collection;


@Component
@Scope("session")
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "guestbook_user_role",
            joinColumns = @JoinColumn(
                    name = "guestbook_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "guestbook_role_id", referencedColumnName = "id"))
    private Collection<GuestbookRole> roles;

    public GuestbookUser(String firstName, String lastName, String email, String password, Collection<GuestbookRole> roles) {
            super();
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.roles = roles;
        }
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getFirstName() {
            return firstName;
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public void setRoles(Collection<GuestbookRole> roles){this.roles = roles;}
        public Collection<GuestbookRole> getRoles() {return roles;}
 }
