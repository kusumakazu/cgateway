package com.id.kusumakazu.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.id.kusumakazu.domain.enumeration.AccountStatus;

/**
 * A MasterAccount.
 */
@Entity
@Table(name = "master_account")
public class MasterAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 256)
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "email", length = 256, nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @Column(name = "account_balance")
    private Long accountBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    private AccountStatus accountStatus;

    @Column(name = "subscription_status")
    private Boolean subscriptionStatus;

    @Column(name = "subscription_start_date")
    private Instant subscriptionStartDate;

    @Column(name = "subscription_end_date")
    private String subscriptionEndDate;

    @OneToMany(mappedBy = "masterAccount")
    private Set<GameAccount> gameAccounts = new HashSet<>();

    @OneToMany(mappedBy = "masterAccount")
    private Set<AccountTransactionHistory> accountTransactionHistories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public MasterAccount email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public MasterAccount hashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
        return this;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Long getAccountBalance() {
        return accountBalance;
    }

    public MasterAccount accountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
        return this;
    }

    public void setAccountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public MasterAccount accountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
        return this;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Boolean isSubscriptionStatus() {
        return subscriptionStatus;
    }

    public MasterAccount subscriptionStatus(Boolean subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
        return this;
    }

    public void setSubscriptionStatus(Boolean subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public Instant getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public MasterAccount subscriptionStartDate(Instant subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
        return this;
    }

    public void setSubscriptionStartDate(Instant subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public String getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public MasterAccount subscriptionEndDate(String subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
        return this;
    }

    public void setSubscriptionEndDate(String subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public Set<GameAccount> getGameAccounts() {
        return gameAccounts;
    }

    public MasterAccount gameAccounts(Set<GameAccount> gameAccounts) {
        this.gameAccounts = gameAccounts;
        return this;
    }

    public MasterAccount addGameAccount(GameAccount gameAccount) {
        this.gameAccounts.add(gameAccount);
        gameAccount.setMasterAccount(this);
        return this;
    }

    public MasterAccount removeGameAccount(GameAccount gameAccount) {
        this.gameAccounts.remove(gameAccount);
        gameAccount.setMasterAccount(null);
        return this;
    }

    public void setGameAccounts(Set<GameAccount> gameAccounts) {
        this.gameAccounts = gameAccounts;
    }

    public Set<AccountTransactionHistory> getAccountTransactionHistories() {
        return accountTransactionHistories;
    }

    public MasterAccount accountTransactionHistories(Set<AccountTransactionHistory> accountTransactionHistories) {
        this.accountTransactionHistories = accountTransactionHistories;
        return this;
    }

    public MasterAccount addAccountTransactionHistory(AccountTransactionHistory accountTransactionHistory) {
        this.accountTransactionHistories.add(accountTransactionHistory);
        accountTransactionHistory.setMasterAccount(this);
        return this;
    }

    public MasterAccount removeAccountTransactionHistory(AccountTransactionHistory accountTransactionHistory) {
        this.accountTransactionHistories.remove(accountTransactionHistory);
        accountTransactionHistory.setMasterAccount(null);
        return this;
    }

    public void setAccountTransactionHistories(Set<AccountTransactionHistory> accountTransactionHistories) {
        this.accountTransactionHistories = accountTransactionHistories;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MasterAccount)) {
            return false;
        }
        return id != null && id.equals(((MasterAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MasterAccount{" +
            "id=" + getId() +
            ", email='" + getEmail() + "'" +
            ", hashPassword='" + getHashPassword() + "'" +
            ", accountBalance=" + getAccountBalance() +
            ", accountStatus='" + getAccountStatus() + "'" +
            ", subscriptionStatus='" + isSubscriptionStatus() + "'" +
            ", subscriptionStartDate='" + getSubscriptionStartDate() + "'" +
            ", subscriptionEndDate='" + getSubscriptionEndDate() + "'" +
            "}";
    }
}
