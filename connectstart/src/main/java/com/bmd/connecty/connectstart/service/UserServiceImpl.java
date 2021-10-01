package com.bmd.connecty.connectstart.service;

import com.bmd.connecty.connectstart.exceptions.MatrixException;
import com.bmd.connecty.connectstart.exceptions.UserException;
import com.bmd.connecty.connectstart.game.Game;
import com.bmd.connecty.connectstart.model.User;
import com.bmd.connecty.connectstart.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private AtomicInteger counter;

    private Map<String, User> users;

    private final char tokens[] = new char[] {'x', 'o', };
    private final String userStates[] = new String[] { "User1", "User2", };

    private final Game game;

    public UserServiceImpl(Game game) {
        counter = new AtomicInteger();
        users = new HashMap();
        this.game = game;
    }

    @Override
    public UserDTO connect(UserDTO user) {

       int count = counter.incrementAndGet();
        if (count > 2) {
            throw new UserException("2 users have already been registered");
        }

        users.put(userStates[count-1], new User(userStates[count-1],user.getUserName(),tokens[count-1]));
        user.setUserState(userStates[count-1]);

        if (count == 2) {
            game.setAllUsersConnected();
        }
        return user;
    }


    @Override
    public void disConnect(String userState) {

        if ((null != userState) && (users.containsKey(userState)) ) {
            game.setUserDisconnected();
        } else {
            throw new UserException(String.format("User state %s does not exist" , userState));
        }

    }

    @Override
    public Integer getUserCount() {
        return counter.get();
    }

    @Override
    public char getUserSymbol(String userState) {
        if (!users.containsKey(userState)) {
            throw new MatrixException(String.format("User state %s is not the list of user states", userState));
        }
        return users.get(userState).getToken();
    }

}
