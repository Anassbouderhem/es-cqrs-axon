package net.bouderhem.anass.escqrsaxon.query.repository;

import net.bouderhem.anass.escqrsaxon.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
