package com.bmd.connecty.connectstart.game;

import com.bmd.connecty.connectstart.exceptions.MatrixException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.bmd.connecty.connectstart.game.State.GameState;


import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

//todo more tests needed to cover all scenrios(e.g backward diagonal, matches starting from innner cells etc)
public class GameTest {

    private Game game;

    @BeforeEach
    public void setup() {

        game = new GameImpl(6,9);

    }
    @Test
    void test_waitingForUsers() {
        assertEquals(GameState.WaitingForAllUsers.toString(), game.getState().getGameState());
    }

    @Test
    void test_User1() {
        game.setAllUsersConnected();
        assertEquals(GameState.User1.toString(), game.getState().getGameState());
    }

    @Test
    void test_User2() {
        game.setAllUsersConnected();
        game.makeMove(2,'x');
        assertEquals(GameState.User2.toString(), game.getState().getGameState());
    }

    @Test
    void test_User1WinVertical() {
        game.setAllUsersConnected();
        for (int i=1 ; i < 5 ; i++) {
            game.makeMove(2,'x');
            game.makeMove(3,'o');
        }
        assertEquals(GameState.User1.toString(), game.getState().getGameState());
        game.makeMove(2,'x');
        assertEquals(GameState.OverUser1Win.toString(), game.getState().getGameState());
    }

    @Test
    void test_User2WinVertical() {
        game.setAllUsersConnected();
        for (int i=1 ; i < 5 ; i++) {
            game.makeMove(2,'x');
            game.makeMove(3,'o');
        }
        game.makeMove(4,'x');
        assertEquals(GameState.User2.toString(), game.getState().getGameState());
        game.makeMove(3,'o');
        assertEquals(GameState.OverUser2Win.toString(), game.getState().getGameState());
    }

    @Test
    void test_User1WinHorizontal() {
        game.setAllUsersConnected();
        int i;
        for (i=1 ; i < 5 ; i++) {
            game.makeMove(i,'x');
            game.makeMove(i,'o');
        }
        assertEquals(GameState.User1.toString(), game.getState().getGameState());

        com.bmd.connecty.connectstart.model.State state = game.makeMove(i,'x');

        System.out.println(Arrays.stream(state.getMatrix()).map(String::new).collect(Collectors.joining("\n")));
        assertEquals(GameState.OverUser1Win.toString(), game.getState().getGameState());
    }

    @Test
    void test_User2WinHorizontal() {
        game.setAllUsersConnected();
        int i;
        for (i=1 ; i < 5 ; i++) {
            game.makeMove(i,'x');
            game.makeMove(i,'o');
        }

        game.makeMove(1,'x');
        game.makeMove(i,'o');
        game.makeMove(1,'x');
        assertEquals(GameState.User2.toString(), game.getState().getGameState());

        com.bmd.connecty.connectstart.model.State state = game.makeMove(i,'o');

        System.out.println(Arrays.stream(state.getMatrix()).map(String::new).collect(Collectors.joining("\n")));

        assertEquals(GameState.OverUser2Win.toString(), game.getState().getGameState());
    }

    @Test
    void test_UserWinForwardDiagonalBLFromCol0() {
        game.setAllUsersConnected();

        int startcol = 1;
        game.makeMove(startcol,'x');
        game.makeMove(startcol+1,'o');
        game.makeMove(startcol+1,'x');
        game.makeMove(startcol+1,'o');
        game.makeMove(startcol+2,'x');
        game.makeMove(startcol+2,'o');
        game.makeMove(startcol+2,'x');
        game.makeMove(startcol+3,'o');
        game.makeMove(startcol+3,'x');
        game.makeMove(startcol+3,'o');
        game.makeMove(startcol+3,'x');
        game.makeMove(startcol,'o');
        game.makeMove(startcol+4,'x');
        game.makeMove(startcol+4,'o');
        game.makeMove(startcol+4,'x');
        game.makeMove(startcol+4,'o');
        assertEquals(GameState.User1.toString(), game.getState().getGameState());

        com.bmd.connecty.connectstart.model.State state = game.makeMove(5,'x');

        System.out.println(Arrays.stream(state.getMatrix()).map(String::new).collect(Collectors.joining("\n")));

        assertEquals(GameState.OverUser1Win.toString(), game.getState().getGameState());
    }

    @Test
    void test_UserWinForwardDiagonalBLFromCol4() {
        game.setAllUsersConnected();

        int startcol = 4;
        game.makeMove(startcol,'x');
        game.makeMove(startcol+1,'o');
        game.makeMove(startcol+1,'x');
        game.makeMove(startcol+1,'o');
        game.makeMove(startcol+2,'x');
        game.makeMove(startcol+2,'o');
        game.makeMove(startcol+2,'x');
        game.makeMove(startcol+3,'o');
        game.makeMove(startcol+3,'x');
        game.makeMove(startcol+3,'o');
        game.makeMove(startcol+3,'x');
        game.makeMove(startcol,'o');
        game.makeMove(startcol+4,'x');
        game.makeMove(startcol+4,'o');
        game.makeMove(startcol+4,'x');
        game.makeMove(startcol+4,'o');
        assertEquals(GameState.User1.toString(), game.getState().getGameState());

        com.bmd.connecty.connectstart.model.State state = game.makeMove(startcol+4,'x');

        System.out.println(Arrays.stream(state.getMatrix()).map(String::new).collect(Collectors.joining("\n")));

        assertEquals(GameState.OverUser1Win.toString(), game.getState().getGameState());
    }

    @Test
    void test_ColumnValueTooLarge() {
        game.setAllUsersConnected();
        String expectedMessage = "The column value provided(9) is greater that the max 8";

        Exception exception = assertThrows(MatrixException.class, () -> {
            game.makeMove(9,'x');
        });

        assertTrue(exception.getMessage().contains(expectedMessage));

    }
}
