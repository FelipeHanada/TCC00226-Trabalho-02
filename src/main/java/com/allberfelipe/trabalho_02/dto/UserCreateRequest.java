package com.allberfelipe.trabalho_02.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String aboutMe;
    private String phoneNumber;
}