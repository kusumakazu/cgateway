package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "armorDetl")
    private Set<Armor> armors = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "armorDetls", allowSetters = true)
    private Player player;

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

    public Set<Armor> getArmors() {
        return armors;
    }

    public ArmorDetl armors(Set<Armor> armors) {
        this.armors = armors;
        return this;
    }

    public ArmorDetl addArmor(Armor armor) {
        this.armors.add(armor);
        armor.setArmorDetl(this);
        return this;
    }

    public ArmorDetl removeArmor(Armor armor) {
        this.armors.remove(armor);
        armor.setArmorDetl(null);
        return this;
    }

    public void setArmors(Set<Armor> armors) {
        this.armors = armors;
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
