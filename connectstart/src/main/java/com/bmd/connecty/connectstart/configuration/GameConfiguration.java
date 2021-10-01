package com.bmd.connecty.connectstart.configuration;

import com.bmd.connecty.connectstart.game.Game;
import com.bmd.connecty.connectstart.game.GameImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@org.springframework.context.annotation.Configuration
public class GameConfiguration {

    private int rows;

    private int columns;


    public GameConfiguration(@Value("${connect4.height}") int rows, @Value("${connect4.width}") int columns) {
        this.rows = rows;
        this.columns =  columns;
    }
    @Bean
    @Scope("singleton")
    public Game gameSingleton() {
        return new GameImpl(rows,columns);
    }
}
