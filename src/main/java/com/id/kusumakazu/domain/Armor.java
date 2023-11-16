package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.id.kusumakazu.domain.enumeration.ArmorType;

import com.id.kusumakazu.domain.enumeration.ArmorSize;

import com.id.kusumakazu.domain.enumeration.Rarity;

/**
 * A Armor.
 */
@Entity
@Table(name = "armor")
public class Armor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "armor_name")
    private String armorName;

    @Column(name = "base_def")
    private Double baseDEF;

    @Column(name = "base_hp")
    private Double baseHP;

    @Enumerated(EnumType.STRING)
    @Column(name = "armor_type")
    private ArmorType armorType;

    @Enumerated(EnumType.STRING)
    @Column(name = "armorsize")
    private ArmorSize armorsize;

    @Enumerated(EnumType.STRING)
    @Column(name = "rarity")
    private Rarity rarity;

    @ManyToMany(mappedBy = "armors")
    @JsonIgnore
    private Set<ItemObject> itemObjects = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArmorName() {
        return armorName;
    }

    public Armor armorName(String armorName) {
        this.armorName = armorName;
        return this;
    }

    public void setArmorName(String armorName) {
        this.armorName = armorName;
    }

    public Double getBaseDEF() {
        return baseDEF;
    }

    public Armor baseDEF(Double baseDEF) {
        this.baseDEF = baseDEF;
        return this;
    }

    public void setBaseDEF(Double baseDEF) {
        this.baseDEF = baseDEF;
    }

    public Double getBaseHP() {
        return baseHP;
    }

    public Armor baseHP(Double baseHP) {
        this.baseHP = baseHP;
        return this;
    }

    public void setBaseHP(Double baseHP) {
        this.baseHP = baseHP;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public Armor armorType(ArmorType armorType) {
        this.armorType = armorType;
        return this;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    public ArmorSize getArmorsize() {
        return armorsize;
    }

    public Armor armorsize(ArmorSize armorsize) {
        this.armorsize = armorsize;
        return this;
    }

    public void setArmorsize(ArmorSize armorsize) {
        this.armorsize = armorsize;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public Armor rarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public Set<ItemObject> getItemObjects() {
        return itemObjects;
    }

    public Armor itemObjects(Set<ItemObject> itemObjects) {
        this.itemObjects = itemObjects;
        return this;
    }

    public Armor addItemObject(ItemObject itemObject) {
        this.itemObjects.add(itemObject);
        itemObject.getArmors().add(this);
        return this;
    }

    public Armor removeItemObject(ItemObject itemObject) {
        this.itemObjects.remove(itemObject);
        itemObject.getArmors().remove(this);
        return this;
    }

    public void setItemObjects(Set<ItemObject> itemObjects) {
        this.itemObjects = itemObjects;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Armor)) {
            return false;
        }
        return id != null && id.equals(((Armor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Armor{" +
            "id=" + getId() +
            ", armorName='" + getArmorName() + "'" +
            ", baseDEF=" + getBaseDEF() +
            ", baseHP=" + getBaseHP() +
            ", armorType='" + getArmorType() + "'" +
            ", armorsize='" + getArmorsize() + "'" +
            ", rarity='" + getRarity() + "'" +
            "}";
    }
}
