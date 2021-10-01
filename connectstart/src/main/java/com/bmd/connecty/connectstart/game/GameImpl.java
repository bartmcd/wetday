package com.bmd.connecty.connectstart.game;

import com.bmd.connecty.connectstart.exceptions.MatrixException;
import com.bmd.connecty.connectstart.model.State;
import com.bmd.connecty.connectstart.game.State.GameState;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GameImpl implements Game {

    private static enum MoveResult {
        GameWon,
        GameFull,
        NextMove;
    }

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    private int rows;

    private int columns;


    private char[][] matrix;

    private volatile GameState gameState = GameState.WaitingForAllUsers;

    private Map<String,String> stateDetails = new HashMap();

    public GameImpl(int rows,int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new char[rows][columns];

        //initialise the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = ' ';
            }
        }

        stateDetails.put(GameState.WaitingForAllUsers.toString(), "Waiting for all Users to connect");
        stateDetails.put(GameState.User1.toString(), "It is the other Users turn");
        stateDetails.put(GameState.User2.toString(), "It is the other Users turn");
        stateDetails.put(GameState.OverGameFull.toString(), "The game is over, the grid is full");
        stateDetails.put(GameState.OverUser1Win.toString(), "The game is over, the other User has won");
        stateDetails.put(GameState.OverUser2Win.toString(), "The game is over, the other User has won");
        stateDetails.put(GameState.OverUserDisconnected.toString(), "The game is over, the other User has disconnected");
    }

    public State makeMove(Integer column, char token) {

        if (column >= columns )
        {
            throw new MatrixException(String.format("The column value provided(%d) is greater that the max %d",
                    column, columns-1));
        }

        int currentRow = -1;
        int currentColumn = -1;

        char[][] tempMatrix = matrix.clone();

        if (tempMatrix[0][column] != ' ') {
            throw new MatrixException(String.format("Column %d is full, enter another column", column+1));
        }

        currentColumn = column;
        for (int i = (rows -1); i >= 0; i--) {
            if (tempMatrix[i][currentColumn] == ' ') {
                currentRow = i;
                tempMatrix[currentRow][currentColumn] = token;
                break;
            }
        }

        String win = String.format("%c%c%c%c%c", token, token, token, token, token);

        String horizontal = new String(tempMatrix[currentRow]);

        StringBuilder vertical = new StringBuilder(rows);
        for (int i = 0 ; i < rows ; i++) {
            vertical.append(tempMatrix[i][currentColumn]);
        }

        //diagonal from top left
        int startRow,startColumn;

        for(startRow = currentRow, startColumn = currentColumn; startRow < (rows-1)
                && startColumn > 0 ; startRow++, startColumn--) {
        }
        StringBuilder backDiag = new StringBuilder(rows);
        for(int r = startRow, c = startColumn; r >= 0 && c < columns ; r--, c++) {
            backDiag.append(tempMatrix[r][c]);
        }

        //diagonal from bottom left
        for(startRow = currentRow, startColumn = currentColumn; startRow > 0
                && startColumn > 0 ; startRow--, startColumn--) {
        }
        StringBuilder forwardDiag = new StringBuilder(rows);
        for(int r = startRow, c = startColumn; r < rows && c < columns ; r++, c++) {
            forwardDiag.append(tempMatrix[r][c]);
        }

        MoveResult moveResult;
        String toprow = new String(tempMatrix[rows -1]);
        //todo check after each string created to reduce unnecessary work
        if ( (horizontal.contains(win))
                || (vertical.toString().contains(win))
                || (backDiag.toString().contains(win))
                || (forwardDiag.toString().contains(win))
        ) {
            moveResult = MoveResult.GameWon;
        } else if(!toprow.contains(" ")) {
            moveResult = MoveResult.GameFull;
        } else {
            moveResult = MoveResult.NextMove;
        }

        String newState = setGameStatus(moveResult,tempMatrix);
        String details = "";
        return new State(tempMatrix,newState.toString(), stateDetails.get(newState));
    }

    @Override
    public State getState() {
        char[][] matrix;
        String gameState;
        State state;
        readLock.lock();
        try {
            matrix = this.matrix.clone();
            gameState = this.gameState.toString();
            state = new State(matrix, gameState, stateDetails.get(gameState));
        } finally {
            readLock.unlock();
        }
        return state;
    }

    @Override
    public void setAllUsersConnected() {
        gameState = gameState.allUsersConnected();
    }

    @Override
    public void setUserDisconnected() {
        writeLock.lock();
        try {
            gameState = gameState.userDisconnected();
        } finally {
            writeLock.unlock();
        }
    }

    private String setGameStatus (MoveResult moveResult, char[][] matrix) {
        String state;
        writeLock.lock();

        try {
            this.matrix = matrix.clone();
            if (moveResult.toString().equalsIgnoreCase(MoveResult.GameWon.toString())) {
                gameState = gameState.gameWon();
            } else if  (moveResult.toString().equalsIgnoreCase(MoveResult.GameFull.toString())){
                gameState = gameState.gameFull();
            } else {
                gameState = gameState.userPlayedMove();
            }
            state = gameState.toString();
        } finally {
            writeLock.unlock();
        }
        return state;
    }

}
