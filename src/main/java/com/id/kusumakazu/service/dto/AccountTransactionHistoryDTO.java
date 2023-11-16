package com.id.kusumakazu.service.dto;

import java.io.Serializable;
import com.id.kusumakazu.domain.enumeration.TransactionType;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.AccountTransactionHistory} entity.
 */
public class AccountTransactionHistoryDTO implements Serializable {
    
    private Long id;

    private String transactionCode;

    private TransactionType transactionType;

    private String transactionDetail;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(String transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountTransactionHistoryDTO)) {
            return false;
        }

        return id != null && id.equals(((AccountTransactionHistoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountTransactionHistoryDTO{" +
            "id=" + getId() +
            ", transactionCode='" + getTransactionCode() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            ", transactionDetail='" + getTransactionDetail() + "'" +
            "}";
    }
}
