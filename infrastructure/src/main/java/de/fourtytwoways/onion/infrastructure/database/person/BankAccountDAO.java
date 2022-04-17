package de.fourtytwoways.onion.infrastructure.database.person;
// (c) 2022 Thomas Herrmann, 42ways GmbH

import de.fourtytwoways.onion.domain.model.person.BankAccount;

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
        this.id = bankAccount.id();
        this.personDAO = personDAO;
        this.isPrimary = bankAccount.primary();
        this.accountHolderName = bankAccount.accountHolderName();
        this.bankName = bankAccount.bankName();
        this.iban = bankAccount.iban();
        this.bic = bankAccount.bic();
    }

    BankAccount toBankAccount() {
        return BankAccount.builder()
                .id(id)
                .primary(isPrimary)
                .accountHolderName(accountHolderName)
                .bankName(bankName)
                .iban(iban)
                .bic(bic)
                .build();
    }
}
