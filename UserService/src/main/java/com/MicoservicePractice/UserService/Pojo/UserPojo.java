package com.MicoservicePractice.UserService.Pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userID;
    @Column(nullable = false,unique = true)
    private String emailID;
    @Column(nullable = false)
    private String password;
    @CreationTimestamp
    private LocalDateTime accountCreatedDate;

}
