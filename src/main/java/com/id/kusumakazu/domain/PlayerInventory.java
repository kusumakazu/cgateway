package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

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

    @ManyToOne
    @JsonIgnoreProperties(value = "playerInventories", allowSetters = true)
    private ItemObject itemObject;

    @ManyToOne
    @JsonIgnoreProperties(value = "playerInventories", allowSetters = true)
    private Player player;

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

    public ItemObject getItemObject() {
        return itemObject;
    }

    public PlayerInventory itemObject(ItemObject itemObject) {
        this.itemObject = itemObject;
        return this;
    }

    public void setItemObject(ItemObject itemObject) {
        this.itemObject = itemObject;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerInventory player(Player player) {
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
