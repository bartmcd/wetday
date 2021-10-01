package com.bmd.connecty.connectstart.game;

import com.bmd.connecty.connectstart.model.State;

public interface Game {

    public State makeMove(Integer column, char token);

    public State getState();

    public void setAllUsersConnected();

    public void setUserDisconnected();
}
