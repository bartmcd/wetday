package com.bmd.connecty.connectstart.service;

import com.bmd.connecty.connectstart.model.UserDTO;

public interface UserService {

    UserDTO connect(UserDTO user);

    void disConnect(String userState);

    Integer getUserCount();

    public char getUserSymbol(String userid);
}
