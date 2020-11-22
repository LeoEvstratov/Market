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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User userFromDB = userRepository.findUserByUsername(s);
        if (userFromDB==null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userFromDB;
    }

    public Optional<User> getUserById(Long id){
       return userRepository.findById(id);
    }

    public boolean saveUser(User user) {
        if (userRepository.existsUserByUsername(user.getUsername())) {
            return false; //todo make exception
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Optional<Role> default_role = roleRepository.findByName("ROLE_CUSTOMER");
        user.setAuthorities(Arrays.asList(default_role.get()));
        userRepository.save(user);
        return true;
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
       return userRepository.findAll(); //todo make pageable and user search
    }

    public Set<Role> getAllRoles() {
        return roleRepository.findAll().stream().collect(Collectors.toSet());
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
