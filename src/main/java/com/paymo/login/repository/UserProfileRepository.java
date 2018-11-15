package com.paymo.login.repository;

import com.paymo.login.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Asus on 11/15/2018.
 */
@Repository("userProfileRepository")
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
    UserProfile findByEmail(String email);
}
