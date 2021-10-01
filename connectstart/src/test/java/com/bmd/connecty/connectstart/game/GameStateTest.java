package com.bmd.connecty.connectstart.game;

import com.bmd.connecty.connectstart.game.State.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameStateTest {

    private GameState game;

    @BeforeEach
    public void setup() {

        game = GameState.WaitingForAllUsers;

    }
    @Test
    void test_waitingForUsers() {
        assertEquals(GameState.WaitingForAllUsers,game);
    }

    @Test
    void test_User1() {
        game = game.allUsersConnected();
        assertEquals(GameState.User1, game);
    }

    @Test
    void test_User2() {
        game = game.allUsersConnected();
        game = game.userPlayedMove();
        assertEquals(GameState.User2,game);
    }

    @Test
    void test_User1Win() {
        game = game.allUsersConnected();
        game = game.userPlayedMove();
        game = game.userPlayedMove();
        game = game.gameWon();
        assertEquals(GameState.OverUser1Win, game);
    }

    @Test
    void test_User2Win() {
        game = game.allUsersConnected();
        game = game.userPlayedMove();
        game = game.gameWon();
        assertEquals(GameState.OverUser2Win,game);
    }

    @Test
    void test_GameFull() {
        game = game.allUsersConnected();
        game = game.userPlayedMove();
        game = game.gameFull();
        assertEquals(GameState.OverGameFull,game);
    }

    @Test
    void test_UserDisconnected() {
        game = game.allUsersConnected();
        game = game.userPlayedMove();
        game = game.userDisconnected();
        assertEquals(GameState.OverUserDisconnected, game);
    }
}
