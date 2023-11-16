package com.id.kusumakazu.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.StorageInventory} entity.
 */
public class StorageInventoryDTO implements Serializable {
    
    private Long id;

    private String storageInventoryName;

    private Integer slot;

    private Integer amount;

    private Integer locationCoorA;

    private Integer locationCoorB;


    private Long itemObjectId;

    private Long playerId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStorageInventoryName() {
        return storageInventoryName;
    }

    public void setStorageInventoryName(String storageInventoryName) {
        this.storageInventoryName = storageInventoryName;
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

    public Integer getLocationCoorA() {
        return locationCoorA;
    }

    public void setLocationCoorA(Integer locationCoorA) {
        this.locationCoorA = locationCoorA;
    }

    public Integer getLocationCoorB() {
        return locationCoorB;
    }

    public void setLocationCoorB(Integer locationCoorB) {
        this.locationCoorB = locationCoorB;
    }

    public Long getItemObjectId() {
        return itemObjectId;
    }

    public void setItemObjectId(Long itemObjectId) {
        this.itemObjectId = itemObjectId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StorageInventoryDTO)) {
            return false;
        }

        return id != null && id.equals(((StorageInventoryDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StorageInventoryDTO{" +
            "id=" + getId() +
            ", storageInventoryName='" + getStorageInventoryName() + "'" +
            ", slot=" + getSlot() +
            ", amount=" + getAmount() +
            ", locationCoorA=" + getLocationCoorA() +
            ", locationCoorB=" + getLocationCoorB() +
            ", itemObjectId=" + getItemObjectId() +
            ", playerId=" + getPlayerId() +
            "}";
    }
}
