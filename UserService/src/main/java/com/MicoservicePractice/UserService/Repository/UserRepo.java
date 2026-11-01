package com.MicoservicePractice.UserService.Repository;

import com.MicoservicePractice.UserService.Pojo.UserPojo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserPojo,String> {

    boolean existsByEmailID(String emailID);
}
