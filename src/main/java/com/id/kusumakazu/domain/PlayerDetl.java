package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PlayerDetl.
 */
@Entity
@Table(name = "player_detl")
public class PlayerDetl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chara_attribute_points")
    private Integer charaAttributePoints;

    @Column(name = "chara_stat_a")
    private Double charaStatA;

    @Column(name = "chara_stat_b")
    private Double charaStatB;

    @Column(name = "chara_stat_c")
    private Integer charaStatC;

    @Column(name = "chara_stat_d")
    private Integer charaStatD;

    @Column(name = "chara_stat_e")
    private Integer charaStatE;

    @Column(name = "chara_stat_f")
    private Integer charaStatF;

    @Column(name = "chara_stat_g")
    private Integer charaStatG;

    @Column(name = "chara_stat_h")
    private Integer charaStatH;

    @Column(name = "chara_behaviour_a")
    private Double charaBehaviourA;

    @Column(name = "chara_behaviour_b")
    private Double charaBehaviourB;

    @Column(name = "chara_stat_hp")
    private Double charaStatHP;

    @Column(name = "chara_stat_mp")
    private Double charaStatMP;

    @OneToOne
    @JoinColumn(unique = true)
    private PlayerInventory playerInventory;

    @OneToOne
    @JoinColumn(unique = true)
    private PlayerCurrency playerCurrency;

    @OneToOne(mappedBy = "playerDetl")
    @JsonIgnore
    private Player player;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCharaAttributePoints() {
        return charaAttributePoints;
    }

    public PlayerDetl charaAttributePoints(Integer charaAttributePoints) {
        this.charaAttributePoints = charaAttributePoints;
        return this;
    }

    public void setCharaAttributePoints(Integer charaAttributePoints) {
        this.charaAttributePoints = charaAttributePoints;
    }

    public Double getCharaStatA() {
        return charaStatA;
    }

    public PlayerDetl charaStatA(Double charaStatA) {
        this.charaStatA = charaStatA;
        return this;
    }

    public void setCharaStatA(Double charaStatA) {
        this.charaStatA = charaStatA;
    }

    public Double getCharaStatB() {
        return charaStatB;
    }

    public PlayerDetl charaStatB(Double charaStatB) {
        this.charaStatB = charaStatB;
        return this;
    }

    public void setCharaStatB(Double charaStatB) {
        this.charaStatB = charaStatB;
    }

    public Integer getCharaStatC() {
        return charaStatC;
    }

    public PlayerDetl charaStatC(Integer charaStatC) {
        this.charaStatC = charaStatC;
        return this;
    }

    public void setCharaStatC(Integer charaStatC) {
        this.charaStatC = charaStatC;
    }

    public Integer getCharaStatD() {
        return charaStatD;
    }

    public PlayerDetl charaStatD(Integer charaStatD) {
        this.charaStatD = charaStatD;
        return this;
    }

    public void setCharaStatD(Integer charaStatD) {
        this.charaStatD = charaStatD;
    }

    public Integer getCharaStatE() {
        return charaStatE;
    }

    public PlayerDetl charaStatE(Integer charaStatE) {
        this.charaStatE = charaStatE;
        return this;
    }

    public void setCharaStatE(Integer charaStatE) {
        this.charaStatE = charaStatE;
    }

    public Integer getCharaStatF() {
        return charaStatF;
    }

    public PlayerDetl charaStatF(Integer charaStatF) {
        this.charaStatF = charaStatF;
        return this;
    }

    public void setCharaStatF(Integer charaStatF) {
        this.charaStatF = charaStatF;
    }

    public Integer getCharaStatG() {
        return charaStatG;
    }

    public PlayerDetl charaStatG(Integer charaStatG) {
        this.charaStatG = charaStatG;
        return this;
    }

    public void setCharaStatG(Integer charaStatG) {
        this.charaStatG = charaStatG;
    }

    public Integer getCharaStatH() {
        return charaStatH;
    }

    public PlayerDetl charaStatH(Integer charaStatH) {
        this.charaStatH = charaStatH;
        return this;
    }

    public void setCharaStatH(Integer charaStatH) {
        this.charaStatH = charaStatH;
    }

    public Double getCharaBehaviourA() {
        return charaBehaviourA;
    }

    public PlayerDetl charaBehaviourA(Double charaBehaviourA) {
        this.charaBehaviourA = charaBehaviourA;
        return this;
    }

    public void setCharaBehaviourA(Double charaBehaviourA) {
        this.charaBehaviourA = charaBehaviourA;
    }

    public Double getCharaBehaviourB() {
        return charaBehaviourB;
    }

    public PlayerDetl charaBehaviourB(Double charaBehaviourB) {
        this.charaBehaviourB = charaBehaviourB;
        return this;
    }

    public void setCharaBehaviourB(Double charaBehaviourB) {
        this.charaBehaviourB = charaBehaviourB;
    }

    public Double getCharaStatHP() {
        return charaStatHP;
    }

    public PlayerDetl charaStatHP(Double charaStatHP) {
        this.charaStatHP = charaStatHP;
        return this;
    }

    public void setCharaStatHP(Double charaStatHP) {
        this.charaStatHP = charaStatHP;
    }

    public Double getCharaStatMP() {
        return charaStatMP;
    }

    public PlayerDetl charaStatMP(Double charaStatMP) {
        this.charaStatMP = charaStatMP;
        return this;
    }

    public void setCharaStatMP(Double charaStatMP) {
        this.charaStatMP = charaStatMP;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    public PlayerDetl playerInventory(PlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
        return this;
    }

    public void setPlayerInventory(PlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    public PlayerCurrency getPlayerCurrency() {
        return playerCurrency;
    }

    public PlayerDetl playerCurrency(PlayerCurrency playerCurrency) {
        this.playerCurrency = playerCurrency;
        return this;
    }

    public void setPlayerCurrency(PlayerCurrency playerCurrency) {
        this.playerCurrency = playerCurrency;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerDetl player(Player player) {
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
        if (!(o instanceof PlayerDetl)) {
            return false;
        }
        return id != null && id.equals(((PlayerDetl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlayerDetl{" +
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
            "}";
    }
}
