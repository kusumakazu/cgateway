package com.id.kusumakazu.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.PlayerDetl} entity.
 */
public class PlayerDetlDTO implements Serializable {
    
    private Long id;

    private Integer charaAttributePoints;

    private Double charaStatA;

    private Double charaStatB;

    private Integer charaStatC;

    private Integer charaStatD;

    private Integer charaStatE;

    private Integer charaStatF;

    private Integer charaStatG;

    private Integer charaStatH;

    private Double charaBehaviourA;

    private Double charaBehaviourB;

    private Double charaStatHP;

    private Double charaStatMP;


    private Long playerInventoryId;

    private Long playerCurrencyId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCharaAttributePoints() {
        return charaAttributePoints;
    }

    public void setCharaAttributePoints(Integer charaAttributePoints) {
        this.charaAttributePoints = charaAttributePoints;
    }

    public Double getCharaStatA() {
        return charaStatA;
    }

    public void setCharaStatA(Double charaStatA) {
        this.charaStatA = charaStatA;
    }

    public Double getCharaStatB() {
        return charaStatB;
    }

    public void setCharaStatB(Double charaStatB) {
        this.charaStatB = charaStatB;
    }

    public Integer getCharaStatC() {
        return charaStatC;
    }

    public void setCharaStatC(Integer charaStatC) {
        this.charaStatC = charaStatC;
    }

    public Integer getCharaStatD() {
        return charaStatD;
    }

    public void setCharaStatD(Integer charaStatD) {
        this.charaStatD = charaStatD;
    }

    public Integer getCharaStatE() {
        return charaStatE;
    }

    public void setCharaStatE(Integer charaStatE) {
        this.charaStatE = charaStatE;
    }

    public Integer getCharaStatF() {
        return charaStatF;
    }

    public void setCharaStatF(Integer charaStatF) {
        this.charaStatF = charaStatF;
    }

    public Integer getCharaStatG() {
        return charaStatG;
    }

    public void setCharaStatG(Integer charaStatG) {
        this.charaStatG = charaStatG;
    }

    public Integer getCharaStatH() {
        return charaStatH;
    }

    public void setCharaStatH(Integer charaStatH) {
        this.charaStatH = charaStatH;
    }

    public Double getCharaBehaviourA() {
        return charaBehaviourA;
    }

    public void setCharaBehaviourA(Double charaBehaviourA) {
        this.charaBehaviourA = charaBehaviourA;
    }

    public Double getCharaBehaviourB() {
        return charaBehaviourB;
    }

    public void setCharaBehaviourB(Double charaBehaviourB) {
        this.charaBehaviourB = charaBehaviourB;
    }

    public Double getCharaStatHP() {
        return charaStatHP;
    }

    public void setCharaStatHP(Double charaStatHP) {
        this.charaStatHP = charaStatHP;
    }

    public Double getCharaStatMP() {
        return charaStatMP;
    }

    public void setCharaStatMP(Double charaStatMP) {
        this.charaStatMP = charaStatMP;
    }

    public Long getPlayerInventoryId() {
        return playerInventoryId;
    }

    public void setPlayerInventoryId(Long playerInventoryId) {
        this.playerInventoryId = playerInventoryId;
    }

    public Long getPlayerCurrencyId() {
        return playerCurrencyId;
    }

    public void setPlayerCurrencyId(Long playerCurrencyId) {
        this.playerCurrencyId = playerCurrencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerDetlDTO)) {
            return false;
        }

        return id != null && id.equals(((PlayerDetlDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlayerDetlDTO{" +
            "id=" + getId() +
            ", charaAttributePoints=" + getCharaAttributePoints() +
            ", charaStatA=" + getCharaStatA() +
            ", charaStatB=" + getCharaStatB() +
            ", charaStatC=" + getCharaStatC() +
            ", charaStatD=" + getCharaStatD() +
            ", charaStatE=" + getCharaStatE() +
            ", charaStatF=" + getCharaStatF() +
            ", charaStatG=" + getCharaStatG() +
            ", charaStatH=" + getCharaStatH() +
            ", charaBehaviourA=" + getCharaBehaviourA() +
            ", charaBehaviourB=" + getCharaBehaviourB() +
            ", charaStatHP=" + getCharaStatHP() +
            ", charaStatMP=" + getCharaStatMP() +
            ", playerInventoryId=" + getPlayerInventoryId() +
            ", playerCurrencyId=" + getPlayerCurrencyId() +
            "}";
    }
}
