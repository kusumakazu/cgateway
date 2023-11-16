package com.id.kusumakazu.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.id.kusumakazu.domain.enumeration.AccountStatus;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.MasterAccount} entity.
 */
public class MasterAccountDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 5, max = 256)
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    private String email;

    @NotNull
    private String hashPassword;

    private Long accountBalance;

    private AccountStatus accountStatus;

    private Boolean subscriptionStatus;

    private Instant subscriptionStartDate;

    private String subscriptionEndDate;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Boolean isSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(Boolean subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public Instant getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(Instant subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public String getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(String subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MasterAccountDTO)) {
            return false;
        }

        return id != null && id.equals(((MasterAccountDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MasterAccountDTO{" +
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
