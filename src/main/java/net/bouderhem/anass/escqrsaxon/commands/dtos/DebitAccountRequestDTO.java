package net.bouderhem.anass.escqrsaxon.commands.dtos;

public record DebitAccountRequestDTO (String accountId, double amount, String currency) {
}
