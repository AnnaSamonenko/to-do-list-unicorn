package com.spring.as.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @Getter
    @Setter
    @Column(name = "username", unique = true)
    @NotBlank(message = "{username.not_empty}")
    @Size(min = 4, max = 15, message = "{username.invalid_range}")
    private String username;

    @Getter
    @Setter
    @Email(message = "{email.incorrect_value}")
    @Column(name = "email", unique = true)
    private String email;

    @Getter
    @Setter
    @Column(name = "password")
    @NotBlank(message = "{password.not_empty}")
    private String password;

    @Getter
    @Setter
    @Column(name = "role")
    private String role;

    @Setter
    @Getter
    @Column(name = "enabled")
    @Type(type="true_false")
    private Boolean enabled = false;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Project> projects = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
