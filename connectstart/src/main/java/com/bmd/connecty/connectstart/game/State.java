package com.bmd.connecty.connectstart.game;

public class State {

    //todo  report errors for unexpected state transitions
    public static enum GameState {
        WaitingForAllUsers {
            @Override
            public GameState allUsersConnected() { return User1;}
            @Override
            public GameState userPlayedMove() { return WaitingForAllUsers;};
            @Override
            public GameState gameWon() { return WaitingForAllUsers;};
            @Override
            public GameState userDisconnected() { return OverUserDisconnected;};
            @Override
            public GameState gameFull() { return OverGameFull;};
            },
        User1 {
            @Override
            public GameState allUsersConnected() { return User1;}
            @Override
            public GameState userPlayedMove() { return User2;};
            @Override
            public GameState gameWon() { return OverUser1Win;};
            @Override
            public GameState userDisconnected() { return OverUserDisconnected;};
            @Override
            public GameState gameFull() { return OverGameFull;};
        },
        User2 {
            @Override
            public GameState allUsersConnected() { return User2;}
            @Override
            public GameState userPlayedMove() { return User1;};
            @Override
            public GameState gameWon() { return OverUser2Win;};
            @Override
            public GameState userDisconnected() { return OverUserDisconnected;};
            @Override
            public GameState gameFull() { return OverGameFull;};
        },
        OverUser1Win {
            @Override
            public GameState allUsersConnected() { return OverUser1Win;}
            @Override
            public GameState userPlayedMove() { return OverUser1Win;};
            @Override
            public GameState gameWon() { return OverUser1Win;};
            @Override
            public GameState userDisconnected() { return OverUser1Win;};
            @Override
            public GameState gameFull() { return OverUser1Win;};
        },
        OverUser2Win {
            @Override
            public GameState allUsersConnected() { return OverUser2Win;}
            @Override
            public GameState userPlayedMove() { return OverUser2Win;};
            @Override
            public GameState gameWon() { return OverUser2Win;};
            @Override
            public GameState userDisconnected() { return OverUser2Win;};
            @Override
            public GameState gameFull() { return OverUser2Win;};
        },
        OverUserDisconnected {
            @Override
            public GameState allUsersConnected() { return OverUserDisconnected;}
            @Override
            public GameState userPlayedMove() { return OverUserDisconnected;};
            @Override
            public GameState gameWon() { return OverUserDisconnected;};
            @Override
            public GameState userDisconnected() { return OverUserDisconnected;};
            @Override
            public GameState gameFull() { return OverUserDisconnected;};
        },
        OverGameFull {
            @Override
            public GameState allUsersConnected() { return OverGameFull;}
            @Override
            public GameState userPlayedMove() { return OverGameFull;};
            @Override
            public GameState gameWon() { return OverGameFull;};
            @Override
            public GameState userDisconnected() { return OverGameFull;};
            @Override
            public GameState gameFull() { return OverGameFull;};
        };

        public abstract GameState allUsersConnected();
        public abstract GameState userPlayedMove();
        public abstract GameState gameWon();
        public abstract GameState userDisconnected();
        public abstract GameState gameFull();

        }

}
