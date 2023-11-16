package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

import com.id.kusumakazu.domain.enumeration.WeaponType;

import com.id.kusumakazu.domain.enumeration.WeaponSize;

import com.id.kusumakazu.domain.enumeration.Rarity;

/**
 * A Weapon.
 */
@Entity
@Table(name = "weapon")
public class Weapon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "base_atk")
    private Double baseATK;

    @Enumerated(EnumType.STRING)
    @Column(name = "weapon_type")
    private WeaponType weaponType;

    @Enumerated(EnumType.STRING)
    @Column(name = "weapon_size")
    private WeaponSize weaponSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "rarity")
    private Rarity rarity;

    @ManyToOne
    @JsonIgnoreProperties(value = "weapons", allowSetters = true)
    private ItemObject itemObject;

    @ManyToOne
    @JsonIgnoreProperties(value = "weapons", allowSetters = true)
    private WeaponDetl weaponDetl;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBaseATK() {
        return baseATK;
    }

    public Weapon baseATK(Double baseATK) {
        this.baseATK = baseATK;
        return this;
    }

    public void setBaseATK(Double baseATK) {
        this.baseATK = baseATK;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Weapon weaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
        return this;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public WeaponSize getWeaponSize() {
        return weaponSize;
    }

    public Weapon weaponSize(WeaponSize weaponSize) {
        this.weaponSize = weaponSize;
        return this;
    }

    public void setWeaponSize(WeaponSize weaponSize) {
        this.weaponSize = weaponSize;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public Weapon rarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public ItemObject getItemObject() {
        return itemObject;
    }

    public Weapon itemObject(ItemObject itemObject) {
        this.itemObject = itemObject;
        return this;
    }

    public void setItemObject(ItemObject itemObject) {
        this.itemObject = itemObject;
    }

    public WeaponDetl getWeaponDetl() {
        return weaponDetl;
    }

    public Weapon weaponDetl(WeaponDetl weaponDetl) {
        this.weaponDetl = weaponDetl;
        return this;
    }

    public void setWeaponDetl(WeaponDetl weaponDetl) {
        this.weaponDetl = weaponDetl;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Weapon)) {
            return false;
        }
        return id != null && id.equals(((Weapon) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Weapon{" +
            "id=" + getId() +
            ", baseATK=" + getBaseATK() +
            ", weaponType='" + getWeaponType() + "'" +
            ", weaponSize='" + getWeaponSize() + "'" +
            ", rarity='" + getRarity() + "'" +
            "}";
    }
}
