package com.bmd.connect.client.constants;

public class Constants {

    public static String HOST_AND_PORT = "localhost:9002";
    public static String USER_URL = "http://%s/v1/connect4/user/";
    public static String STATE_URL = "http://%s/v1/connect4/game/state/";
    public static String MOVE_URL = "http://%s/v1/connect4/game/move/";
    public static long SLEEP_MS = 2000;

    public static String OVER = "Over";
    public static String WIN = "The game is over, congratulations %s , you have won";
    public static String YOUR_TURN = "It's your turn %s, please enter column (1-9) :";
}
