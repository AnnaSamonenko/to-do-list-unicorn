package com.spring.as.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@PropertySource("classpath:config.properties")
public class User implements UserDetails {

    @Id
    @Getter
    @Setter
    @NotNull
    @Size(min = 4, max = 15, message = "${username.invalid_range}")
    @NotBlank(message = "${username.not_empty}")
    @Column(name = "username", unique = true)
    private String username;

    @Getter
    @Setter
    @NotBlank
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Getter
    @Setter
    @Column(name = "password")
    @NotBlank
    private String password;

    @Getter
    @Setter
    @Column(name = "role")
    private String role;

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
        return true;
    }
}
