package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A StorageInventory.
 */
@Entity
@Table(name = "storage_inventory")
public class StorageInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "storage_inventory_name")
    private String storageInventoryName;

    @Column(name = "slot")
    private Integer slot;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "location_coor_a")
    private Integer locationCoorA;

    @Column(name = "location_coor_b")
    private Integer locationCoorB;

    @ManyToOne
    @JsonIgnoreProperties(value = "storageInventories", allowSetters = true)
    private ItemObject itemObject;

    @ManyToOne
    @JsonIgnoreProperties(value = "storageInventories", allowSetters = true)
    private Player player;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStorageInventoryName() {
        return storageInventoryName;
    }

    public StorageInventory storageInventoryName(String storageInventoryName) {
        this.storageInventoryName = storageInventoryName;
        return this;
    }

    public void setStorageInventoryName(String storageInventoryName) {
        this.storageInventoryName = storageInventoryName;
    }

    public Integer getSlot() {
        return slot;
    }

    public StorageInventory slot(Integer slot) {
        this.slot = slot;
        return this;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Integer getAmount() {
        return amount;
    }

    public StorageInventory amount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getLocationCoorA() {
        return locationCoorA;
    }

    public StorageInventory locationCoorA(Integer locationCoorA) {
        this.locationCoorA = locationCoorA;
        return this;
    }

    public void setLocationCoorA(Integer locationCoorA) {
        this.locationCoorA = locationCoorA;
    }

    public Integer getLocationCoorB() {
        return locationCoorB;
    }

    public StorageInventory locationCoorB(Integer locationCoorB) {
        this.locationCoorB = locationCoorB;
        return this;
    }

    public void setLocationCoorB(Integer locationCoorB) {
        this.locationCoorB = locationCoorB;
    }

    public ItemObject getItemObject() {
        return itemObject;
    }

    public StorageInventory itemObject(ItemObject itemObject) {
        this.itemObject = itemObject;
        return this;
    }

    public void setItemObject(ItemObject itemObject) {
        this.itemObject = itemObject;
    }

    public Player getPlayer() {
        return player;
    }

    public StorageInventory player(Player player) {
        this.player = player;
        return this;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StorageInventory)) {
            return false;
        }
        return id != null && id.equals(((StorageInventory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StorageInventory{" +
            "id=" + getId() +
            ", storageInventoryName='" + getStorageInventoryName() + "'" +
            ", slot=" + getSlot() +
            ", amount=" + getAmount() +
            ", locationCoorA=" + getLocationCoorA() +
            ", locationCoorB=" + getLocationCoorB() +
            "}";
    }
}
