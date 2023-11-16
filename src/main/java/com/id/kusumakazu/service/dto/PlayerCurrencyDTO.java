package com.id.kusumakazu.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.PlayerCurrency} entity.
 */
public class PlayerCurrencyDTO implements Serializable {
    
    private Long id;

    private Long gold;

    private Long silver;

    private Long copper;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Long getSilver() {
        return silver;
    }

    public void setSilver(Long silver) {
        this.silver = silver;
    }

    public Long getCopper() {
        return copper;
    }

    public void setCopper(Long copper) {
        this.copper = copper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerCurrencyDTO)) {
            return false;
        }

        return id != null && id.equals(((PlayerCurrencyDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlayerCurrencyDTO{" +
            "id=" + getId() +
            ", gold=" + getGold() +
            ", silver=" + getSilver() +
            ", copper=" + getCopper() +
            "}";
    }
}
