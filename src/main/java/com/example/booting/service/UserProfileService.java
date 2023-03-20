package com.example.booting.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.booting.repository.UserProfileRepository;

@Service
public class UserProfileService implements UserDetailsService {
    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userProfileRepository.findByName(username)
                .map(userProfile -> new User(userProfile.getName(), userProfile.getPassword(), List.of()))
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
