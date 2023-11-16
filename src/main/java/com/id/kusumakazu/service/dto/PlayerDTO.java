package com.id.kusumakazu.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.id.kusumakazu.domain.enumeration.PlayerType;
import com.id.kusumakazu.domain.enumeration.Faction;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.Player} entity.
 */
public class PlayerDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String characterName;

    private PlayerType characterType;

    private Faction characterFaction;

    private Long experience;

    private Integer characterLevel;

    private String lastLocation;

    private String lastLocationCoordinate;

    private Instant lastLogin;


    private Long playerDetlId;

    private Long playerClassId;

    private Long playerCurrencyId;

    private Long gameAccountId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public PlayerType getCharacterType() {
        return characterType;
    }

    public void setCharacterType(PlayerType characterType) {
        this.characterType = characterType;
    }

    public Faction getCharacterFaction() {
        return characterFaction;
    }

    public void setCharacterFaction(Faction characterFaction) {
        this.characterFaction = characterFaction;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public Integer getCharacterLevel() {
        return characterLevel;
    }

    public void setCharacterLevel(Integer characterLevel) {
        this.characterLevel = characterLevel;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }

    public String getLastLocationCoordinate() {
        return lastLocationCoordinate;
    }

    public void setLastLocationCoordinate(String lastLocationCoordinate) {
        this.lastLocationCoordinate = lastLocationCoordinate;
    }

    public Instant getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getPlayerDetlId() {
        return playerDetlId;
    }

    public void setPlayerDetlId(Long playerDetlId) {
        this.playerDetlId = playerDetlId;
    }

    public Long getPlayerClassId() {
        return playerClassId;
    }

    public void setPlayerClassId(Long playerClassId) {
        this.playerClassId = playerClassId;
    }

    public Long getPlayerCurrencyId() {
        return playerCurrencyId;
    }

    public void setPlayerCurrencyId(Long playerCurrencyId) {
        this.playerCurrencyId = playerCurrencyId;
    }

    public Long getGameAccountId() {
        return gameAccountId;
    }

    public void setGameAccountId(Long gameAccountId) {
        this.gameAccountId = gameAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerDTO)) {
            return false;
        }

        return id != null && id.equals(((PlayerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlayerDTO{" +
            "id=" + getId() +
            ", characterName='" + getCharacterName() + "'" +
            ", characterType='" + getCharacterType() + "'" +
            ", characterFaction='" + getCharacterFaction() + "'" +
            ", experience=" + getExperience() +
            ", characterLevel=" + getCharacterLevel() +
            ", lastLocation='" + getLastLocation() + "'" +
            ", lastLocationCoordinate='" + getLastLocationCoordinate() + "'" +
            ", lastLogin='" + getLastLogin() + "'" +
            ", playerDetlId=" + getPlayerDetlId() +
            ", playerClassId=" + getPlayerClassId() +
            ", playerCurrencyId=" + getPlayerCurrencyId() +
            ", gameAccountId=" + getGameAccountId() +
            "}";
    }
}
