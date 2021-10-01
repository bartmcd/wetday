package com.bmd.connecty.connectstart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userState; //state associated with this user

    @NotBlank
    private String userName;

    private char token;
}
