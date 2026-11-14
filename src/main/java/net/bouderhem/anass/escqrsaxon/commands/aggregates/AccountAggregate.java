package net.bouderhem.anass.escqrsaxon.commands.aggregates;

import net.bouderhem.anass.escqrsaxon.commands.commands.AddAccountCommand;
import net.bouderhem.anass.escqrsaxon.enums.AccountStatus;
import net.bouderhem.anass.escqrsaxon.events.AccountCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {
     private String accountId;
     private double balance;
     private AccountStatus status;
     // AXON
     public AccountAggregate(){}

    @CommandHandler
    public AccountAggregate(AddAccountCommand command){
         // la fonction de décision
         if(command.getInitialBalance()<=0) throw new IllegalArgumentException("Balance must be positive");
        AggregateLifecycle.apply(new AccountCreatedEvent(
           command.getId(),
                command.getInitialBalance(),
                AccountStatus.CREATED,
                command.getCurrency()
        ));
    }


}
