package net.bouderhem.anass.escqrsaxon.commands.aggregates;

import lombok.extern.slf4j.Slf4j;
import net.bouderhem.anass.escqrsaxon.commands.commands.AddAccountCommand;
import net.bouderhem.anass.escqrsaxon.commands.commands.CreditAccountCommand;
import net.bouderhem.anass.escqrsaxon.enums.AccountStatus;
import net.bouderhem.anass.escqrsaxon.events.AccountActivatedEvent;
import net.bouderhem.anass.escqrsaxon.events.AccountCreatedEvent;
import net.bouderhem.anass.escqrsaxon.events.AccountCreditedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
     private String accountId;
     private double balance;
     private AccountStatus status;
     // AXON
     public AccountAggregate(){}

    @CommandHandler
    public AccountAggregate(AddAccountCommand command){
         // la fonction de décision
        log.info("************ AddAccountCommand Received ***********");
         if(command.getInitialBalance()<=0) throw new IllegalArgumentException("Balance must be positive");
        AggregateLifecycle.apply(new AccountCreatedEvent(
           command.getId(),
                command.getInitialBalance(),
                AccountStatus.CREATED,
                command.getCurrency()
        ));
        AggregateLifecycle.apply(new AccountActivatedEvent(
                command.getId(),
                AccountStatus.ACTIVATED
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        log.info("************ AddAccountEvent accured ***********");
        this.accountId = event.getAccountId();
         this.balance = event.getInitialBalance();
         this.status = event.getStatus();
    }

    @CommandHandler
    public void handle(CreditAccountCommand command){
        // la fonction de décision
        log.info("************ CreditAccountCommand Received ***********");
        if(!status.equals(AccountStatus.ACTIVATED)) throw new RuntimeException("Account "+command.getId()+"Is not activated");
        if(command.getAmount()<=0) throw new IllegalArgumentException("Amount must be positive");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        log.info("************ AccountCreditedEvent accured ***********");
        this.accountId = event.getAccountId();
        this.balance = this.balance + event.getAmount();
    }
}
