package net.bouderhem.anass.escqrsaxon.query.dtos;

import net.bouderhem.anass.escqrsaxon.query.entities.Account;
import net.bouderhem.anass.escqrsaxon.query.entities.AccountOperation;

import java.util.List;

public record AccountStatementResponseDTO(Account account, List<AccountOperation> operations) {
}
