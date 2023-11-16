package com.id.kusumakazu.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import com.id.kusumakazu.domain.enumeration.ObtainFrom;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.ItemObject} entity.
 */
public class ItemObjectDTO implements Serializable {
    
    private Long id;

    private String itemName;

    private String itemDescription;

    private ObtainFrom obtainedfrom;

    private Boolean isEnchant;

    private Set<WeaponDTO> weapons = new HashSet<>();
    private Set<ArmorDTO> armors = new HashSet<>();

    private Long playerInventoryId;

    private Long storageInventoryId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public ObtainFrom getObtainedfrom() {
        return obtainedfrom;
    }

    public void setObtainedfrom(ObtainFrom obtainedfrom) {
        this.obtainedfrom = obtainedfrom;
    }

    public Boolean isIsEnchant() {
        return isEnchant;
    }

    public void setIsEnchant(Boolean isEnchant) {
        this.isEnchant = isEnchant;
    }

    public Set<WeaponDTO> getWeapons() {
        return weapons;
    }

    public void setWeapons(Set<WeaponDTO> weapons) {
        this.weapons = weapons;
    }

    public Set<ArmorDTO> getArmors() {
        return armors;
    }

    public void setArmors(Set<ArmorDTO> armors) {
        this.armors = armors;
    }

    public Long getPlayerInventoryId() {
        return playerInventoryId;
    }

    public void setPlayerInventoryId(Long playerInventoryId) {
        this.playerInventoryId = playerInventoryId;
    }

    public Long getStorageInventoryId() {
        return storageInventoryId;
    }

    public void setStorageInventoryId(Long storageInventoryId) {
        this.storageInventoryId = storageInventoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemObjectDTO)) {
            return false;
        }

        return id != null && id.equals(((ItemObjectDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemObjectDTO{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", itemDescription='" + getItemDescription() + "'" +
            ", obtainedfrom='" + getObtainedfrom() + "'" +
            ", isEnchant='" + isIsEnchant() + "'" +
            ", weapons='" + getWeapons() + "'" +
            ", armors='" + getArmors() + "'" +
            ", playerInventoryId=" + getPlayerInventoryId() +
            ", storageInventoryId=" + getStorageInventoryId() +
            "}";
    }
}
