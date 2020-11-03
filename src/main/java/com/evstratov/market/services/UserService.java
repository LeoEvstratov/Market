package com.evstratov.market.services;


import com.evstratov.market.entities.Role;
import com.evstratov.market.entities.User;
import com.evstratov.market.repositories.RoleRepository;
import com.evstratov.market.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User userFromDB = userRepository.findUserByUsername(s);
        if (userRepository.findUserByUsername(s)==null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userFromDB;
    }

    public boolean saveUser(User user) {
        if (isUserInDB(user)) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Optional<Role> default_role = roleRepository.findByName("ROLE_CUSTOMER");
        user.setAuthorities(Arrays.asList(default_role.get()));
        userRepository.save(user);
        return true;
    }

    private boolean isUserInDB(User user) {
        return userRepository.findUserByUsername(user.getUsername())!=null; //todo make this better way
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
