package com.casino.balance;

import com.casino.balance.domain.BalanceFacade;
import com.casino.balance.dto.*;
import com.casino.user.domain.UserFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceFacade balanceFacade;
    private final UserFacade userFacade;

    @GetMapping
    ResponseEntity<BalanceDto> getBalance(Principal principal){
        final Long userId = userFacade.findUserByUsername(principal.getName()).id();
        BalanceDto balanceDto = balanceFacade.findBalanceById(userId);
        return ResponseEntity.ok(balanceDto);
    }

    @PostMapping("/deposit")
    ResponseEntity<BalanceDto> deposit(@RequestBody Deposit deposit){
        BalanceDto balanceDto = balanceFacade.deposit(deposit);
        return ResponseEntity.ok(balanceDto);
    }

    @PostMapping("/withdraw")
    ResponseEntity<BalanceDto> withdraw(@RequestBody Withdraw withdraw){
        BalanceDto balanceDto = balanceFacade.withdraw(withdraw);
        return ResponseEntity.ok(balanceDto);
    }

    @PostMapping("/transfer")
    ResponseEntity<TransferResult> transfer(@RequestBody Transfer transfer){
        TransferResult transferResult = balanceFacade.transfer(transfer);
        return ResponseEntity.ok(transferResult);
    }
}
