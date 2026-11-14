package net.bouderhem.anass.escqrsaxon.commands.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bouderhem.anass.escqrsaxon.enums.AccountStatus;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter @AllArgsConstructor
public class UpdateAccountStatusCommand {
    @TargetAggregateIdentifier
    private String id;
    private AccountStatus status;
}
