package net.bouderhem.anass.escqrsaxon.query.controllers;

import net.bouderhem.anass.escqrsaxon.query.dtos.AccountStatementResponseDTO;
import net.bouderhem.anass.escqrsaxon.query.entities.Account;
import net.bouderhem.anass.escqrsaxon.query.queries.GetAccountStatementQuery;
import net.bouderhem.anass.escqrsaxon.query.queries.GetAllAccountsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/accounts")
public class AccountQueryController {
    private QueryGateway queryGateway;

    public AccountQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    @GetMapping("/all")
    public CompletableFuture<List<Account>> getAllAccount(){
        CompletableFuture<List<Account>> response = queryGateway.query(
                new GetAllAccountsQuery(),
                ResponseTypes.multipleInstancesOf(Account.class));
        return response;
    }

    @GetMapping("/accountStatement/{accountId}")
    public CompletableFuture<AccountStatementResponseDTO> getAccountStatement(@PathVariable String accountId){
        return queryGateway.query(new GetAccountStatementQuery(accountId),
                ResponseTypes.instanceOf(AccountStatementResponseDTO.class));
    }
}
