package net.bouderhem.anass.escqrsaxon.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bouderhem.anass.escqrsaxon.enums.AccountStatus;

@Getter @AllArgsConstructor
public class AccountStatusUpdatedEvent {
    private String accountId;
    private AccountStatus status;
}
