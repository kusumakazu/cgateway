package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ArmorDetl.
 */
@Entity
@Table(name = "armor_detl")
public class ArmorDetl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "random_stat_bonus")
    private Integer randomStatBonus;

    @ManyToOne
    @JsonIgnoreProperties(value = "armorDetls", allowSetters = true)
    private Player player;

    @ManyToOne
    @JsonIgnoreProperties(value = "armorDetls", allowSetters = true)
    private Armor armor;

    @ManyToOne
    @JsonIgnoreProperties(value = "armorDetls", allowSetters = true)
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

    public ArmorDetl randomStatBonus(Integer randomStatBonus) {
        this.randomStatBonus = randomStatBonus;
        return this;
    }

    public void setRandomStatBonus(Integer randomStatBonus) {
        this.randomStatBonus = randomStatBonus;
    }

    public Player getPlayer() {
        return player;
    }

    public ArmorDetl player(Player player) {
        this.player = player;
        return this;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Armor getArmor() {
        return armor;
    }

    public ArmorDetl armor(Armor armor) {
        this.armor = armor;
        return this;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public ArmorDetl enchantment(Enchantment enchantment) {
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
        if (!(o instanceof ArmorDetl)) {
            return false;
        }
        return id != null && id.equals(((ArmorDetl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ArmorDetl{" +
            "id=" + getId() +
            ", randomStatBonus=" + getRandomStatBonus() +
            "}";
    }
}
