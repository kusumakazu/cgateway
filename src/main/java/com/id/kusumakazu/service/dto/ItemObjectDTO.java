package com.id.kusumakazu.service.dto;

import java.io.Serializable;
import com.id.kusumakazu.domain.enumeration.ObtainFrom;
import com.id.kusumakazu.domain.enumeration.ItemObjectType;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.ItemObject} entity.
 */
public class ItemObjectDTO implements Serializable {
    
    private Long id;

    private ObtainFrom obtainedfrom;

    private Boolean isEnchant;

    private ItemObjectType itemObjectType;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ItemObjectType getItemObjectType() {
        return itemObjectType;
    }

    public void setItemObjectType(ItemObjectType itemObjectType) {
        this.itemObjectType = itemObjectType;
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
            ", obtainedfrom='" + getObtainedfrom() + "'" +
            ", isEnchant='" + isIsEnchant() + "'" +
            ", itemObjectType='" + getItemObjectType() + "'" +
            "}";
    }
}
