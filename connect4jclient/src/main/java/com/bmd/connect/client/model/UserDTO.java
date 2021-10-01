package com.bmd.connect.client.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {

    private String userState;

    @NonNull
    private String userName;
}

