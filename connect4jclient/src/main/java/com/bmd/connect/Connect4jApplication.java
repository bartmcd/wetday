package com.bmd.connect;

import com.bmd.connect.client.Connect4jClient;
import com.bmd.connect.client.model.UserDTO;
import com.bmd.connect.client.constants.Constants;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class Connect4jApplication {

    private static String deleteUrl;


    public static void main(String[] args) {

        deleteUrl = args.length == 0 ? String.format(Constants.USER_URL,Constants.HOST_AND_PORT) : String.format(Constants.USER_URL,args[0]);

        Connect4jClient client = new Connect4jClient();
        // create thread object
        ShutDownTask shutDownTask = new ShutDownTask(client);
        // add shutdown hook
        Runtime.getRuntime().addShutdownHook(shutDownTask);
        client.run(args);
    }

    /**
     * Class having shutdown steps
     *
     */
    private static class ShutDownTask extends Thread {

        private Connect4jClient client;

        public ShutDownTask(Connect4jClient client) {
            this.client = client;
        }

        @Override
        public void run() {
            System.out.println("Performing shutdown");

            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<UserDTO> request = new HttpEntity<UserDTO>(client.getUser());
            String url = deleteUrl+client.getUser().getUserState();
            restTemplate.delete(url);

            System.out.println("shutdown complete");
        }

    }

}
