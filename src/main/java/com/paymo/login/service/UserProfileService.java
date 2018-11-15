package com.paymo.login.service;

import com.paymo.login.model.Role;
import com.paymo.login.model.UserProfile;
import com.paymo.login.repository.RoleRepository;
import com.paymo.login.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Asus on 11/15/2018.
 */
@Service("userProfileService")
public class UserProfileService {

    private UserProfileRepository userProfileRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository, RoleRepository roleRepository,
                              BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userProfileRepository = userProfileRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserProfile findByEmail(String email){
        return userProfileRepository.findByEmail(email);
    }

    public UserProfile save(UserProfile userProfile){
        userProfile.setPassword(bCryptPasswordEncoder.encode(userProfile.getPassword()));
        userProfile.setActive(1);

        Role role = roleRepository.findByRole("ADMIN");
        userProfile.setRoles(new HashSet<Role>(Arrays.asList(role)));
        userProfileRepository.save(userProfile);
        return userProfile;
    }

}
