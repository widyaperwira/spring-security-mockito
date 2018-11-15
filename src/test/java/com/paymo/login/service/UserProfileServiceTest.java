package com.paymo.login.service;

import com.paymo.login.model.UserProfile;
import com.paymo.login.repository.RoleRepository;
import com.paymo.login.repository.UserProfileRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Asus on 11/15/2018.
 */
public class UserProfileServiceTest {

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserProfileService userProfileService;
    private UserProfile userProfile;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userProfileService = new UserProfileService(userProfileRepository, roleRepository, bCryptPasswordEncoder);

        userProfile = UserProfile.builder()
                        .id(1)
                        .name("Widya")
                        .lastName("Perwira")
                        .email("widya@email.com")
                        .build();

        Mockito.when(userProfileRepository.save(userProfile)).thenReturn(userProfile);
        Mockito.when(userProfileRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(userProfile);
    }

    @Test
    public void testFindUserByEmail(){

        final String email = "widya@email.com";

        final UserProfile userProfile = userProfileService.findByEmail(email);
        Assert.assertEquals(email, userProfile.getEmail());
    }

    @Test
    public void testSaveUserProfile(){

        final String email = "widya@email.com";

        UserProfile result = userProfileService.save(userProfile);
        Assert.assertEquals(email, result.getEmail());
    }

}
