package com.casino.bet;


import com.casino.bet.domain.BetFacade;
import com.casino.bet.dto.BetDto;
import com.casino.bet.dto.BetPlacedResult;
import com.casino.bet.dto.PlaceBet;
import com.casino.user.domain.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/bets")
@AllArgsConstructor
public class BetController {

    private final BetFacade betFacade;
    private final UserFacade userFacade;

    @GetMapping
    ResponseEntity<List<BetDto>> findAllBets() {
        return ResponseEntity.ok(betFacade.findAllBets());
    }

    @PostMapping("/place")
    ResponseEntity<BetPlacedResult> placeBet(@RequestBody PlaceBet placeBet) {
        BetPlacedResult betPlacedResult = betFacade.placeBet(placeBet);
        return ResponseEntity.ok(betPlacedResult);
    }

    @GetMapping("/all-user-bets")
    ResponseEntity<List<BetDto>> findAllByUserId(Principal principal){
        final Long userId = userFacade.findUserByUsername(principal.getName()).id();
        List<BetDto> listOfBets = betFacade.findAllBetsByUserId(userId);
        return ResponseEntity.ok(listOfBets);
    }
}
