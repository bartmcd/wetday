package com.bmd.connecty.connectstart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMoveDTO {
    @NotNull
    private String userState;

    @Min(1)
    @Max(9)
    @NotNull
    private Integer column;
}
