package com.id.kusumakazu.service.dto;

import java.io.Serializable;
import com.id.kusumakazu.domain.enumeration.WeaponType;
import com.id.kusumakazu.domain.enumeration.WeaponSize;
import com.id.kusumakazu.domain.enumeration.Rarity;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.Weapon} entity.
 */
public class WeaponDTO implements Serializable {
    
    private Long id;

    private String weaponName;

    private String weaponDescription;

    private Double baseATK;

    private WeaponType weaponType;

    private WeaponSize weaponSize;

    private Rarity rarity;


    private Long itemObjectId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getWeaponDescription() {
        return weaponDescription;
    }

    public void setWeaponDescription(String weaponDescription) {
        this.weaponDescription = weaponDescription;
    }

    public Double getBaseATK() {
        return baseATK;
    }

    public void setBaseATK(Double baseATK) {
        this.baseATK = baseATK;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public WeaponSize getWeaponSize() {
        return weaponSize;
    }

    public void setWeaponSize(WeaponSize weaponSize) {
        this.weaponSize = weaponSize;
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
        if (!(o instanceof WeaponDTO)) {
            return false;
        }

        return id != null && id.equals(((WeaponDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WeaponDTO{" +
            "id=" + getId() +
            ", weaponName='" + getWeaponName() + "'" +
            ", weaponDescription='" + getWeaponDescription() + "'" +
            ", baseATK=" + getBaseATK() +
            ", weaponType='" + getWeaponType() + "'" +
            ", weaponSize='" + getWeaponSize() + "'" +
            ", rarity='" + getRarity() + "'" +
            ", itemObjectId=" + getItemObjectId() +
            "}";
    }
}
