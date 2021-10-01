package com.bmd.connecty.connectstart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateDTO {
    private char[][] matrix;

    private String gameState;

    private String details;
}
