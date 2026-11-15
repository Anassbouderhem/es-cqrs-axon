package net.bouderhem.anass.escqrsaxon.query.handlers;

import net.bouderhem.anass.escqrsaxon.query.dtos.AccountStatementResponseDTO;
import net.bouderhem.anass.escqrsaxon.query.entities.Account;
import net.bouderhem.anass.escqrsaxon.query.entities.AccountOperation;
import net.bouderhem.anass.escqrsaxon.query.queries.GetAccountStatementQuery;
import net.bouderhem.anass.escqrsaxon.query.queries.GetAllAccountsQuery;
import net.bouderhem.anass.escqrsaxon.query.queries.WatchEventQuery;
import net.bouderhem.anass.escqrsaxon.query.repository.AccountRepository;
import net.bouderhem.anass.escqrsaxon.query.repository.OperationRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountQueryHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;


    public AccountQueryHandler(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }
    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query){
        return accountRepository.findAll();
    }
    @QueryHandler
    public AccountStatementResponseDTO on(GetAccountStatementQuery query){
        Account account = accountRepository.findById(query.getAccountId()).get();
        List<AccountOperation> operations = operationRepository.findByAccountId(query.getAccountId());
        return new AccountStatementResponseDTO(account,operations);
    }

    @QueryHandler
    public AccountOperation on (WatchEventQuery query){
        return AccountOperation.builder().build();
    }

}
