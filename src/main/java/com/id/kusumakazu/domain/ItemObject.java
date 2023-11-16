package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.id.kusumakazu.domain.enumeration.ObtainFrom;

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

    @ManyToMany
    @JoinTable(name = "item_object_weapon",
               joinColumns = @JoinColumn(name = "item_object_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "weapon_id", referencedColumnName = "id"))
    private Set<Weapon> weapons = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "item_object_armor",
               joinColumns = @JoinColumn(name = "item_object_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "armor_id", referencedColumnName = "id"))
    private Set<Armor> armors = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "itemObjects", allowSetters = true)
    private PlayerInventory playerInventory;

    @ManyToOne
    @JsonIgnoreProperties(value = "itemObjects", allowSetters = true)
    private StorageInventory storageInventory;

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

    public Set<Weapon> getWeapons() {
        return weapons;
    }

    public ItemObject weapons(Set<Weapon> weapons) {
        this.weapons = weapons;
        return this;
    }

    public ItemObject addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
        weapon.getItemObjects().add(this);
        return this;
    }

    public ItemObject removeWeapon(Weapon weapon) {
        this.weapons.remove(weapon);
        weapon.getItemObjects().remove(this);
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
        armor.getItemObjects().add(this);
        return this;
    }

    public ItemObject removeArmor(Armor armor) {
        this.armors.remove(armor);
        armor.getItemObjects().remove(this);
        return this;
    }

    public void setArmors(Set<Armor> armors) {
        this.armors = armors;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    public ItemObject playerInventory(PlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
        return this;
    }

    public void setPlayerInventory(PlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    public StorageInventory getStorageInventory() {
        return storageInventory;
    }

    public ItemObject storageInventory(StorageInventory storageInventory) {
        this.storageInventory = storageInventory;
        return this;
    }

    public void setStorageInventory(StorageInventory storageInventory) {
        this.storageInventory = storageInventory;
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
            "}";
    }
}
