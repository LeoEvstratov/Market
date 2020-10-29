package com.evstratov.market.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //todo add user validation
    @NotBlank(message = "username cannot be empty")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "password cannot be empty")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "password confirmation cannot be empty")
    @Transient
    private String passwordConfirmation;

    @Column(name = "enabled")
    private boolean enabled;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "enter correct email")
    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

}
