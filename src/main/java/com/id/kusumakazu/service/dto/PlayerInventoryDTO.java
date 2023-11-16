package com.id.kusumakazu.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.PlayerInventory} entity.
 */
public class PlayerInventoryDTO implements Serializable {
    
    private Long id;

    private Integer slot;

    private Integer amount;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerInventoryDTO)) {
            return false;
        }

        return id != null && id.equals(((PlayerInventoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlayerInventoryDTO{" +
            "id=" + getId() +
            ", slot=" + getSlot() +
            ", amount=" + getAmount() +
            "}";
    }
}
