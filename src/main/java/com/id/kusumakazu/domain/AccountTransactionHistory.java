package com.id.kusumakazu.domain;


import javax.persistence.*;

import java.io.Serializable;

import com.id.kusumakazu.domain.enumeration.TransactionType;

/**
 * A AccountTransactionHistory.
 */
@Entity
@Table(name = "account_transaction_history")
public class AccountTransactionHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_code")
    private String transactionCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "transaction_detail")
    private String transactionDetail;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public AccountTransactionHistory transactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
        return this;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public AccountTransactionHistory transactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDetail() {
        return transactionDetail;
    }

    public AccountTransactionHistory transactionDetail(String transactionDetail) {
        this.transactionDetail = transactionDetail;
        return this;
    }

    public void setTransactionDetail(String transactionDetail) {
        this.transactionDetail = transactionDetail;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountTransactionHistory)) {
            return false;
        }
        return id != null && id.equals(((AccountTransactionHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountTransactionHistory{" +
            "id=" + getId() +
            ", transactionCode='" + getTransactionCode() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            ", transactionDetail='" + getTransactionDetail() + "'" +
            "}";
    }
}
