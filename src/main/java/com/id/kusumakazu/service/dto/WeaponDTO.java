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

    private Double baseATK;

    private WeaponType weaponType;

    private WeaponSize weaponSize;

    private Rarity rarity;

    
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
            ", baseATK=" + getBaseATK() +
            ", weaponType='" + getWeaponType() + "'" +
            ", weaponSize='" + getWeaponSize() + "'" +
            ", rarity='" + getRarity() + "'" +
            "}";
    }
}
