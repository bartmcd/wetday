package com.bmd.connecty.connectstart.service;

import com.bmd.connecty.connectstart.exceptions.MatrixException;
import com.bmd.connecty.connectstart.game.Game;
import com.bmd.connecty.connectstart.model.State;
import com.bmd.connecty.connectstart.model.StateDTO;
import com.bmd.connecty.connectstart.model.UserMoveDTO;
import org.springframework.stereotype.Service;
import com.bmd.connecty.connectstart.game.State.GameState;

@Service
public class GameServiceImpl implements GameService {

    private int currentUser = 1;


    GameState state = GameState.WaitingForAllUsers;



    private final Game game;
    private final UserService userService;

    public GameServiceImpl(Game game, UserService userService) {

        this.game = game;
        this.userService = userService;
    }

    @Override
    public StateDTO makeMove(UserMoveDTO move) {

        String gameState = game.getState().getGameState();
        if (!game.getState().getGameState().equals(move.getUserState()))
        {
            throw new MatrixException(String.format("Move made in incorrect state, expected %s, received %s",
                    gameState, move.getUserState()));
        }

        State moveResult  = game.makeMove(move.getColumn()-1, userService.getUserSymbol(move.getUserState()));

        return new StateDTO(moveResult.getMatrix(),moveResult.getGameState().toString(),moveResult.getDetails());

    }

    @Override
    public StateDTO getState() {
        State state = game.getState();
        return new StateDTO(state.getMatrix(),state.getGameState().toString(),state.getDetails());
    }


}
