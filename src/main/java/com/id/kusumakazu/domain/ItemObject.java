package com.id.kusumakazu.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.id.kusumakazu.domain.enumeration.ObtainFrom;

import com.id.kusumakazu.domain.enumeration.ItemObjectType;

/**
 * A ItemObject.
 */
@Entity
@Table(name = "item_object")
public class ItemObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_description")
    private String itemDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "obtainedfrom")
    private ObtainFrom obtainedfrom;

    @Column(name = "is_enchant")
    private Boolean isEnchant;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_object_type")
    private ItemObjectType itemObjectType;

    @OneToMany(mappedBy = "itemObject")
    private Set<StorageInventory> storageInventories = new HashSet<>();

    @OneToMany(mappedBy = "itemObject")
    private Set<PlayerInventory> playerInventories = new HashSet<>();

    @OneToMany(mappedBy = "itemObject")
    private Set<Weapon> weapons = new HashSet<>();

    @OneToMany(mappedBy = "itemObject")
    private Set<Armor> armors = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public ItemObject itemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public ItemObject itemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public ObtainFrom getObtainedfrom() {
        return obtainedfrom;
    }

    public ItemObject obtainedfrom(ObtainFrom obtainedfrom) {
        this.obtainedfrom = obtainedfrom;
        return this;
    }

    public void setObtainedfrom(ObtainFrom obtainedfrom) {
        this.obtainedfrom = obtainedfrom;
    }

    public Boolean isIsEnchant() {
        return isEnchant;
    }

    public ItemObject isEnchant(Boolean isEnchant) {
        this.isEnchant = isEnchant;
        return this;
    }

    public void setIsEnchant(Boolean isEnchant) {
        this.isEnchant = isEnchant;
    }

    public ItemObjectType getItemObjectType() {
        return itemObjectType;
    }

    public ItemObject itemObjectType(ItemObjectType itemObjectType) {
        this.itemObjectType = itemObjectType;
        return this;
    }

    public void setItemObjectType(ItemObjectType itemObjectType) {
        this.itemObjectType = itemObjectType;
    }

    public Set<StorageInventory> getStorageInventories() {
        return storageInventories;
    }

    public ItemObject storageInventories(Set<StorageInventory> storageInventories) {
        this.storageInventories = storageInventories;
        return this;
    }

    public ItemObject addStorageInventory(StorageInventory storageInventory) {
        this.storageInventories.add(storageInventory);
        storageInventory.setItemObject(this);
        return this;
    }

    public ItemObject removeStorageInventory(StorageInventory storageInventory) {
        this.storageInventories.remove(storageInventory);
        storageInventory.setItemObject(null);
        return this;
    }

    public void setStorageInventories(Set<StorageInventory> storageInventories) {
        this.storageInventories = storageInventories;
    }

    public Set<PlayerInventory> getPlayerInventories() {
        return playerInventories;
    }

    public ItemObject playerInventories(Set<PlayerInventory> playerInventories) {
        this.playerInventories = playerInventories;
        return this;
    }

    public ItemObject addPlayerInventory(PlayerInventory playerInventory) {
        this.playerInventories.add(playerInventory);
        playerInventory.setItemObject(this);
        return this;
    }

    public ItemObject removePlayerInventory(PlayerInventory playerInventory) {
        this.playerInventories.remove(playerInventory);
        playerInventory.setItemObject(null);
        return this;
    }

    public void setPlayerInventories(Set<PlayerInventory> playerInventories) {
        this.playerInventories = playerInventories;
    }

    public Set<Weapon> getWeapons() {
        return weapons;
    }

    public ItemObject weapons(Set<Weapon> weapons) {
        this.weapons = weapons;
        return this;
    }

    public ItemObject addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
        weapon.setItemObject(this);
        return this;
    }

    public ItemObject removeWeapon(Weapon weapon) {
        this.weapons.remove(weapon);
        weapon.setItemObject(null);
        return this;
    }

    public void setWeapons(Set<Weapon> weapons) {
        this.weapons = weapons;
    }

    public Set<Armor> getArmors() {
        return armors;
    }

    public ItemObject armors(Set<Armor> armors) {
        this.armors = armors;
        return this;
    }

    public ItemObject addArmor(Armor armor) {
        this.armors.add(armor);
        armor.setItemObject(this);
        return this;
    }

    public ItemObject removeArmor(Armor armor) {
        this.armors.remove(armor);
        armor.setItemObject(null);
        return this;
    }

    public void setArmors(Set<Armor> armors) {
        this.armors = armors;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemObject)) {
            return false;
        }
        return id != null && id.equals(((ItemObject) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItemObject{" +
            "id=" + getId() +
            ", itemName='" + getItemName() + "'" +
            ", itemDescription='" + getItemDescription() + "'" +
            ", obtainedfrom='" + getObtainedfrom() + "'" +
            ", isEnchant='" + isIsEnchant() + "'" +
            ", itemObjectType='" + getItemObjectType() + "'" +
            "}";
    }
}
