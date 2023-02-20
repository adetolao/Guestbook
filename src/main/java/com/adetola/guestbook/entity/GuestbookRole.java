package com.adetola.guestbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guestbook_role")
public class GuestbookRole  implements GrantedAuthority {

    @Id
    private Long id;
    @NotEmpty
    private String name;
    @ManyToMany(mappedBy = "roles")
    List<GuestbookUser> users;

    public GuestbookRole(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}