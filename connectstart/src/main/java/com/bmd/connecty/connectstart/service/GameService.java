package com.bmd.connecty.connectstart.service;

import com.bmd.connecty.connectstart.model.StateDTO;
import com.bmd.connecty.connectstart.model.UserMoveDTO;

public interface GameService {

    public StateDTO makeMove(UserMoveDTO move);

    public StateDTO getState();
}
