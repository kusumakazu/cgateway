package com.id.kusumakazu.service.dto;

import java.io.Serializable;
import com.id.kusumakazu.domain.enumeration.ArmorType;
import com.id.kusumakazu.domain.enumeration.ArmorSize;
import com.id.kusumakazu.domain.enumeration.Rarity;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.Armor} entity.
 */
public class ArmorDTO implements Serializable {
    
    private Long id;

    private String armorName;

    private String armorDescription;

    private Double baseDEF;

    private Double baseHP;

    private ArmorType armorType;

    private ArmorSize armorsize;

    private Rarity rarity;


    private Long itemObjectId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArmorName() {
        return armorName;
    }

    public void setArmorName(String armorName) {
        this.armorName = armorName;
    }

    public String getArmorDescription() {
        return armorDescription;
    }

    public void setArmorDescription(String armorDescription) {
        this.armorDescription = armorDescription;
    }

    public Double getBaseDEF() {
        return baseDEF;
    }

    public void setBaseDEF(Double baseDEF) {
        this.baseDEF = baseDEF;
    }

    public Double getBaseHP() {
        return baseHP;
    }

    public void setBaseHP(Double baseHP) {
        this.baseHP = baseHP;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    public ArmorSize getArmorsize() {
        return armorsize;
    }

    public void setArmorsize(ArmorSize armorsize) {
        this.armorsize = armorsize;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public Long getItemObjectId() {
        return itemObjectId;
    }

    public void setItemObjectId(Long itemObjectId) {
        this.itemObjectId = itemObjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArmorDTO)) {
            return false;
        }

        return id != null && id.equals(((ArmorDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArmorDTO{" +
            "id=" + getId() +
            ", armorName='" + getArmorName() + "'" +
            ", armorDescription='" + getArmorDescription() + "'" +
            ", baseDEF=" + getBaseDEF() +
            ", baseHP=" + getBaseHP() +
            ", armorType='" + getArmorType() + "'" +
            ", armorsize='" + getArmorsize() + "'" +
            ", rarity='" + getRarity() + "'" +
            ", itemObjectId=" + getItemObjectId() +
            "}";
    }
}
