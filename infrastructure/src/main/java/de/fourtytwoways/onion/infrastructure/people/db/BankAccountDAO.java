package de.fourtytwoways.onion.infrastructure.people.db;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.entities.person.BankAccount;

import javax.persistence.*;

@Entity
@Table(name = "BANK_ACCOUNT")
public class BankAccountDAO {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;
    @JoinColumn(name="person_id")
    @ManyToOne
    private PersonDAO personDAO;
    boolean isPrimary;
    String accountHolderName;
    String bankName;
    String iban;
    String bic;

    protected BankAccountDAO() {
    }

    BankAccountDAO(PersonDAO personDAO, BankAccount bankAccount) {
        this.id = bankAccount.getId();
        this.personDAO = personDAO;
        this.isPrimary = bankAccount.isPrimary();
        this.accountHolderName = bankAccount.getAccountHolderName();
        this.bankName = bankAccount.getBankName();
        this.iban = bankAccount.getIban();
        this.bic = bankAccount.getBic();
    }

    BankAccount toBankAccount() {
        return new BankAccount(id, isPrimary,
                               accountHolderName, bankName,
                               iban, bic);
    }
}
