package com.id.kusumakazu.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.GameAccount} entity.
 */
public class GameAccountDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 5, max = 15)
    private String accountName;


    private Long masterAccountId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getMasterAccountId() {
        return masterAccountId;
    }

    public void setMasterAccountId(Long masterAccountId) {
        this.masterAccountId = masterAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GameAccountDTO)) {
            return false;
        }

        return id != null && id.equals(((GameAccountDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GameAccountDTO{" +
            "id=" + getId() +
            ", accountName='" + getAccountName() + "'" +
            ", masterAccountId=" + getMasterAccountId() +
            "}";
    }
}
