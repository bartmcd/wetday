package com.bmd.connect.client;

import com.bmd.connect.client.model.StateDTO;
import com.bmd.connect.client.model.UserDTO;
import com.bmd.connect.client.model.UserMoveDTO;
import com.bmd.connect.client.constants.Constants;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Connect4jClient {


    @Getter
    private UserDTO user;

    public void run(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Connect5, use CTRL-C to exit at any time");
        System.out.println("Please Enter your name :");
        String userName = scanner.nextLine();

        if ((userName.isEmpty())) {
            throw new IllegalArgumentException("No username was entered");
        }
        user = new UserDTO(userName);

        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        //todo  error handling
        //todo logging
        HttpEntity<UserDTO> request = new HttpEntity<UserDTO>(user);
        user = restTemplate.postForObject(Constants.USER_URL, request, UserDTO.class);


        boolean print = true;
        while (true) {
            StateDTO state = restTemplate.getForObject(Constants.STATE_URL, StateDTO.class);
            String gameState = state.getGameState();

            if (gameState.startsWith(Constants.OVER)) {
                if (gameState.startsWith(Constants.OVER+user.getUserState())) {
                    System.out.println(String.format(Constants.WIN,user.getUserName()));
                } else {
                    printMatrix(state.getMatrix());
                    System.out.println(state.getDetails());
                }
                System.exit(0);

            } else if (gameState.equals(user.getUserState())) {
                printMatrix(state.getMatrix());
                System.out.println(String.format(Constants.YOUR_TURN, userName));
                Integer column = scanner.nextInt();
                if ((column < 0) || (column > 9)) {
                    throw new IllegalArgumentException("the entered column in not in the range 0 - 9");
                }
                StateDTO moveState = makeMove(restTemplate, column, user);
                printMatrix(moveState.getMatrix());
                print = true;
            } else {
                if (print) {
                    System.out.println(state.getDetails());
                    print = false;
                }
            }
            try {
                Thread.sleep(Constants.SLEEP_MS);
            } catch (InterruptedException e) {
                System.out.println("Unexpected interrupt");
                System.exit(0);
            }
        }
    }

    private StateDTO makeMove(RestTemplate restTemplate, Integer column, UserDTO user) {
        HttpEntity<UserMoveDTO> move = new HttpEntity<UserMoveDTO>(new UserMoveDTO(user.getUserState(), column));
        StateDTO moveState = restTemplate.postForObject(Constants.MOVE_URL, move, StateDTO.class);
        return moveState;
    }

    private void printMatrix(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0 ) {
            throw new RuntimeException("Matrix has no rows");
        }
        int columns = matrix[0].length;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sb.append("[").append(matrix[i][j]).append("]");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
