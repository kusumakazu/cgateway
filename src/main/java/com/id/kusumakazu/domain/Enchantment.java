package com.id.kusumakazu.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.id.kusumakazu.domain.enumeration.EnchantModifers;

/**
 * A Enchantment.
 */
@Entity
@Table(name = "enchantment")
public class Enchantment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enchant_name")
    private String enchantName;

    @Enumerated(EnumType.STRING)
    @Column(name = "enchant_modifer_type")
    private EnchantModifers enchantModiferType;

    @Column(name = "value_modifer")
    private Integer valueModifer;

    @OneToMany(mappedBy = "enchantment")
    private Set<WeaponDetl> weaponDetls = new HashSet<>();

    @OneToMany(mappedBy = "enchantment")
    private Set<ArmorDetl> armorDetls = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnchantName() {
        return enchantName;
    }

    public Enchantment enchantName(String enchantName) {
        this.enchantName = enchantName;
        return this;
    }

    public void setEnchantName(String enchantName) {
        this.enchantName = enchantName;
    }

    public EnchantModifers getEnchantModiferType() {
        return enchantModiferType;
    }

    public Enchantment enchantModiferType(EnchantModifers enchantModiferType) {
        this.enchantModiferType = enchantModiferType;
        return this;
    }

    public void setEnchantModiferType(EnchantModifers enchantModiferType) {
        this.enchantModiferType = enchantModiferType;
    }

    public Integer getValueModifer() {
        return valueModifer;
    }

    public Enchantment valueModifer(Integer valueModifer) {
        this.valueModifer = valueModifer;
        return this;
    }

    public void setValueModifer(Integer valueModifer) {
        this.valueModifer = valueModifer;
    }

    public Set<WeaponDetl> getWeaponDetls() {
        return weaponDetls;
    }

    public Enchantment weaponDetls(Set<WeaponDetl> weaponDetls) {
        this.weaponDetls = weaponDetls;
        return this;
    }

    public Enchantment addWeaponDetl(WeaponDetl weaponDetl) {
        this.weaponDetls.add(weaponDetl);
        weaponDetl.setEnchantment(this);
        return this;
    }

    public Enchantment removeWeaponDetl(WeaponDetl weaponDetl) {
        this.weaponDetls.remove(weaponDetl);
        weaponDetl.setEnchantment(null);
        return this;
    }

    public void setWeaponDetls(Set<WeaponDetl> weaponDetls) {
        this.weaponDetls = weaponDetls;
    }

    public Set<ArmorDetl> getArmorDetls() {
        return armorDetls;
    }

    public Enchantment armorDetls(Set<ArmorDetl> armorDetls) {
        this.armorDetls = armorDetls;
        return this;
    }

    public Enchantment addArmorDetl(ArmorDetl armorDetl) {
        this.armorDetls.add(armorDetl);
        armorDetl.setEnchantment(this);
        return this;
    }

    public Enchantment removeArmorDetl(ArmorDetl armorDetl) {
        this.armorDetls.remove(armorDetl);
        armorDetl.setEnchantment(null);
        return this;
    }

    public void setArmorDetls(Set<ArmorDetl> armorDetls) {
        this.armorDetls = armorDetls;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Enchantment)) {
            return false;
        }
        return id != null && id.equals(((Enchantment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Enchantment{" +
            "id=" + getId() +
            ", enchantName='" + getEnchantName() + "'" +
            ", enchantModiferType='" + getEnchantModiferType() + "'" +
            ", valueModifer=" + getValueModifer() +
            "}";
    }
}
