package com.evstratov.market.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "username cannot be empty")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "password cannot be empty")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "password confirmation cannot be empty")
    @Transient
    private String passwordConfirmation;

    @NotBlank(message = "name cannot be empty")
    @Size(min = 1, max = 50, message = "name has to be from 1 to 50 letters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "surname cannot be empty")
    @Size(min = 1, max = 50, message = "surname has to be from 1 to 50 letters")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "enabled")
    private boolean enabled;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "enter correct email")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "phone cannot be empty")
    @Pattern(regexp = "\\d{11}", message = "enter 11 digits of phone number")
    @Column(name = "phone")
    private String phone;

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
