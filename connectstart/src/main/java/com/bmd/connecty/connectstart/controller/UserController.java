package com.bmd.connecty.connectstart.controller;

import com.bmd.connecty.connectstart.model.UserDTO;
import com.bmd.connecty.connectstart.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/connect4/user")

public class UserController {

    private final UserService userService;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> connect(@Valid @RequestBody UserDTO user) {

        log.info("Received  connect request for user name {}", user.getUserName());
        UserDTO result = userService.connect(user);
        log.info("Processed  connect request for user name {} and user state {}", user.getUserName(), user.getUserState());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value ="/{userState}")
    public ResponseEntity<String> disConnect(@PathVariable String userState) {

        log.info("Received  disconnect request for user state {}", userState);
        userService.disConnect(userState);
        log.info("Processed  disconnect request for user state {}", userState);
        return new ResponseEntity<>(userState, HttpStatus.OK);
    }
}
