package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "weapon_name")
    private String weaponName;

    @Column(name = "weapon_description")
    private String weaponDescription;

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

    @OneToMany(mappedBy = "weapon")
    private Set<WeaponDetl> weaponDetls = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "weapons", allowSetters = true)
    private ItemObject itemObject;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public Weapon weaponName(String weaponName) {
        this.weaponName = weaponName;
        return this;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getWeaponDescription() {
        return weaponDescription;
    }

    public Weapon weaponDescription(String weaponDescription) {
        this.weaponDescription = weaponDescription;
        return this;
    }

    public void setWeaponDescription(String weaponDescription) {
        this.weaponDescription = weaponDescription;
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

    public Set<WeaponDetl> getWeaponDetls() {
        return weaponDetls;
    }

    public Weapon weaponDetls(Set<WeaponDetl> weaponDetls) {
        this.weaponDetls = weaponDetls;
        return this;
    }

    public Weapon addWeaponDetl(WeaponDetl weaponDetl) {
        this.weaponDetls.add(weaponDetl);
        weaponDetl.setWeapon(this);
        return this;
    }

    public Weapon removeWeaponDetl(WeaponDetl weaponDetl) {
        this.weaponDetls.remove(weaponDetl);
        weaponDetl.setWeapon(null);
        return this;
    }

    public void setWeaponDetls(Set<WeaponDetl> weaponDetls) {
        this.weaponDetls = weaponDetls;
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
            ", weaponName='" + getWeaponName() + "'" +
            ", weaponDescription='" + getWeaponDescription() + "'" +
            ", baseATK=" + getBaseATK() +
            ", weaponType='" + getWeaponType() + "'" +
            ", weaponSize='" + getWeaponSize() + "'" +
            ", rarity='" + getRarity() + "'" +
            "}";
    }
}
