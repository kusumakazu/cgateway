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

    private Double baseATK;

    private WeaponType weaponType;

    private WeaponSize weaponSize;

    private Rarity rarity;


    private Long itemObjectId;

    private Long weaponDetlId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getWeaponDetlId() {
        return weaponDetlId;
    }

    public void setWeaponDetlId(Long weaponDetlId) {
        this.weaponDetlId = weaponDetlId;
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
            ", baseATK=" + getBaseATK() +
            ", weaponType='" + getWeaponType() + "'" +
            ", weaponSize='" + getWeaponSize() + "'" +
            ", rarity='" + getRarity() + "'" +
            ", itemObjectId=" + getItemObjectId() +
            ", weaponDetlId=" + getWeaponDetlId() +
            "}";
    }
}
