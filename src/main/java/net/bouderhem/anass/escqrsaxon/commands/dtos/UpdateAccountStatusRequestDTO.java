package net.bouderhem.anass.escqrsaxon.commands.dtos;

import net.bouderhem.anass.escqrsaxon.enums.AccountStatus;

public record UpdateAccountStatusRequestDTO(String accountId, AccountStatus status) {
}
