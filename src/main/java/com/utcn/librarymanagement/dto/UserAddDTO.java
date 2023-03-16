package com.utcn.librarymanagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserAddDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String password;
}
