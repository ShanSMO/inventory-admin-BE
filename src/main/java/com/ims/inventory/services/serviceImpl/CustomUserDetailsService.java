package com.ims.inventory.services.serviceImpl;

import com.ims.inventory.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.jaas.JaasGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier(value = "passwordEncoder")
    private PasswordEncoder passwordEncoderUser;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = null;
        try {
            com.ims.inventory.models.User systemUser = userRepository.findByUserName(userName);
            List<SimpleGrantedAuthority> authList = new ArrayList<>();
            authList.add(new SimpleGrantedAuthority("READ"));
            authList.add(new SimpleGrantedAuthority("WRITE"));
            user = new User(systemUser.getUserName(), passwordEncoderUser.encode(systemUser.getUserPassword()), authList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return user;
    }
}
