package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A PlayerInventory.
 */
@Entity
@Table(name = "player_inventory")
public class PlayerInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slot")
    private Integer slot;

    @Column(name = "amount")
    private Integer amount;

    @OneToMany(mappedBy = "playerInventory")
    private Set<ItemObject> itemObjects = new HashSet<>();

    @OneToOne(mappedBy = "playerInventory")
    @JsonIgnore
    private PlayerDetl playerDetl;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSlot() {
        return slot;
    }

    public PlayerInventory slot(Integer slot) {
        this.slot = slot;
        return this;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Integer getAmount() {
        return amount;
    }

    public PlayerInventory amount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Set<ItemObject> getItemObjects() {
        return itemObjects;
    }

    public PlayerInventory itemObjects(Set<ItemObject> itemObjects) {
        this.itemObjects = itemObjects;
        return this;
    }

    public PlayerInventory addItemObject(ItemObject itemObject) {
        this.itemObjects.add(itemObject);
        itemObject.setPlayerInventory(this);
        return this;
    }

    public PlayerInventory removeItemObject(ItemObject itemObject) {
        this.itemObjects.remove(itemObject);
        itemObject.setPlayerInventory(null);
        return this;
    }

    public void setItemObjects(Set<ItemObject> itemObjects) {
        this.itemObjects = itemObjects;
    }

    public PlayerDetl getPlayerDetl() {
        return playerDetl;
    }

    public PlayerInventory playerDetl(PlayerDetl playerDetl) {
        this.playerDetl = playerDetl;
        return this;
    }

    public void setPlayerDetl(PlayerDetl playerDetl) {
        this.playerDetl = playerDetl;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerInventory)) {
            return false;
        }
        return id != null && id.equals(((PlayerInventory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlayerInventory{" +
            "id=" + getId() +
            ", slot=" + getSlot() +
            ", amount=" + getAmount() +
            "}";
    }
}
