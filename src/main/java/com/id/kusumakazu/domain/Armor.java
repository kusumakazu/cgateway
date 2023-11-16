package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

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

    @ManyToOne
    @JsonIgnoreProperties(value = "armors", allowSetters = true)
    private ItemObject itemObject;

    @ManyToOne
    @JsonIgnoreProperties(value = "armors", allowSetters = true)
    private ArmorDetl armorDetl;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ItemObject getItemObject() {
        return itemObject;
    }

    public Armor itemObject(ItemObject itemObject) {
        this.itemObject = itemObject;
        return this;
    }

    public void setItemObject(ItemObject itemObject) {
        this.itemObject = itemObject;
    }

    public ArmorDetl getArmorDetl() {
        return armorDetl;
    }

    public Armor armorDetl(ArmorDetl armorDetl) {
        this.armorDetl = armorDetl;
        return this;
    }

    public void setArmorDetl(ArmorDetl armorDetl) {
        this.armorDetl = armorDetl;
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
            ", baseDEF=" + getBaseDEF() +
            ", baseHP=" + getBaseHP() +
            ", armorType='" + getArmorType() + "'" +
            ", armorsize='" + getArmorsize() + "'" +
            ", rarity='" + getRarity() + "'" +
            "}";
    }
}
