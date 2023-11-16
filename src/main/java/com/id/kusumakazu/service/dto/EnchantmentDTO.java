package com.id.kusumakazu.service.dto;

import java.io.Serializable;
import com.id.kusumakazu.domain.enumeration.EnchantModifers;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.Enchantment} entity.
 */
public class EnchantmentDTO implements Serializable {
    
    private Long id;

    private String enchantName;

    private EnchantModifers enchantModiferType;

    private Integer valueModifer;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnchantName() {
        return enchantName;
    }

    public void setEnchantName(String enchantName) {
        this.enchantName = enchantName;
    }

    public EnchantModifers getEnchantModiferType() {
        return enchantModiferType;
    }

    public void setEnchantModiferType(EnchantModifers enchantModiferType) {
        this.enchantModiferType = enchantModiferType;
    }

    public Integer getValueModifer() {
        return valueModifer;
    }

    public void setValueModifer(Integer valueModifer) {
        this.valueModifer = valueModifer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EnchantmentDTO)) {
            return false;
        }

        return id != null && id.equals(((EnchantmentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EnchantmentDTO{" +
            "id=" + getId() +
            ", enchantName='" + getEnchantName() + "'" +
            ", enchantModiferType='" + getEnchantModiferType() + "'" +
            ", valueModifer=" + getValueModifer() +
            "}";
    }
}
