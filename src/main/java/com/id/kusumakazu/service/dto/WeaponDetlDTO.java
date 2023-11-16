package com.id.kusumakazu.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.WeaponDetl} entity.
 */
public class WeaponDetlDTO implements Serializable {
    
    private Long id;

    private Integer randomStatBonus;


    private Long playerId;

    private Long weaponId;

    private Long enchantmentId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRandomStatBonus() {
        return randomStatBonus;
    }

    public void setRandomStatBonus(Integer randomStatBonus) {
        this.randomStatBonus = randomStatBonus;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(Long weaponId) {
        this.weaponId = weaponId;
    }

    public Long getEnchantmentId() {
        return enchantmentId;
    }

    public void setEnchantmentId(Long enchantmentId) {
        this.enchantmentId = enchantmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WeaponDetlDTO)) {
            return false;
        }

        return id != null && id.equals(((WeaponDetlDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WeaponDetlDTO{" +
            "id=" + getId() +
            ", randomStatBonus=" + getRandomStatBonus() +
            ", playerId=" + getPlayerId() +
            ", weaponId=" + getWeaponId() +
            ", enchantmentId=" + getEnchantmentId() +
            "}";
    }
}
