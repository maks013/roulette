package com.casino.game;

import com.casino.game.domain.GameFacade;
import com.casino.game.dto.GameDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games")
@AllArgsConstructor
public class GameController {

    private final GameFacade gameFacade;

    @GetMapping
    ResponseEntity<List<GameDto>> findAllGames(){
        return ResponseEntity.ok(gameFacade.findAllGames());
    }

}
