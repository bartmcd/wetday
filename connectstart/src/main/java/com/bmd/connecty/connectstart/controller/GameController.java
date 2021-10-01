package com.bmd.connecty.connectstart.controller;

import com.bmd.connecty.connectstart.model.State;
import com.bmd.connecty.connectstart.model.StateDTO;
import com.bmd.connecty.connectstart.model.UserMoveDTO;
import com.bmd.connecty.connectstart.service.GameService;
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
@RequestMapping("v1/connect4/game")
public class GameController {


    private final GameService gameservice;

    @PostMapping(path = "/move/")
    public ResponseEntity<StateDTO> makeMove(@Valid @RequestBody UserMoveDTO move) {

        log.info("Received  move request for user id {} and column {}", move.getUserState(),  move.getColumn());
        StateDTO result = gameservice.makeMove(move);
        log.info("processed  move request for with state {} ",  result.getGameState());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/state/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StateDTO> makeMove() {

        log.info("Received request for game state");
        StateDTO result = gameservice.getState();
        log.info("processed  state request for with state {} ",  result.getGameState());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
