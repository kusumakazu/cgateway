package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PlayerCurrency.
 */
@Entity
@Table(name = "player_currency")
public class PlayerCurrency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gold")
    private Long gold;

    @Column(name = "silver")
    private Long silver;

    @Column(name = "copper")
    private Long copper;

    @OneToOne(mappedBy = "playerCurrency")
    @JsonIgnore
    private Player player;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGold() {
        return gold;
    }

    public PlayerCurrency gold(Long gold) {
        this.gold = gold;
        return this;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Long getSilver() {
        return silver;
    }

    public PlayerCurrency silver(Long silver) {
        this.silver = silver;
        return this;
    }

    public void setSilver(Long silver) {
        this.silver = silver;
    }

    public Long getCopper() {
        return copper;
    }

    public PlayerCurrency copper(Long copper) {
        this.copper = copper;
        return this;
    }

    public void setCopper(Long copper) {
        this.copper = copper;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerCurrency player(Player player) {
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
        if (!(o instanceof PlayerCurrency)) {
            return false;
        }
        return id != null && id.equals(((PlayerCurrency) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlayerCurrency{" +
            "id=" + getId() +
            ", gold=" + getGold() +
            ", silver=" + getSilver() +
            ", copper=" + getCopper() +
            "}";
    }
}
