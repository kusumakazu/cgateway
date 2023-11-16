package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A WeaponDetl.
 */
@Entity
@Table(name = "weapon_detl")
public class WeaponDetl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "random_stat_bonus")
    private Integer randomStatBonus;

    @ManyToOne
    @JsonIgnoreProperties(value = "weaponDetls", allowSetters = true)
    private Player player;

    @ManyToOne
    @JsonIgnoreProperties(value = "weaponDetls", allowSetters = true)
    private Weapon weapon;

    @ManyToOne
    @JsonIgnoreProperties(value = "weaponDetls", allowSetters = true)
    private Enchantment enchantment;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRandomStatBonus() {
        return randomStatBonus;
    }

    public WeaponDetl randomStatBonus(Integer randomStatBonus) {
        this.randomStatBonus = randomStatBonus;
        return this;
    }

    public void setRandomStatBonus(Integer randomStatBonus) {
        this.randomStatBonus = randomStatBonus;
    }

    public Player getPlayer() {
        return player;
    }

    public WeaponDetl player(Player player) {
        this.player = player;
        return this;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public WeaponDetl weapon(Weapon weapon) {
        this.weapon = weapon;
        return this;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public WeaponDetl enchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
        return this;
    }

    public void setEnchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WeaponDetl)) {
            return false;
        }
        return id != null && id.equals(((WeaponDetl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WeaponDetl{" +
            "id=" + getId() +
            ", randomStatBonus=" + getRandomStatBonus() +
            "}";
    }
}
