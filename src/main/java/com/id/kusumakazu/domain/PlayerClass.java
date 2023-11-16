package com.id.kusumakazu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;

import com.id.kusumakazu.domain.enumeration.ClassAtkType;

/**
 * A PlayerClass.
 */
@Entity
@Table(name = "player_class")
public class PlayerClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name")
    private String className;

    @Column(name = "class_detail_info")
    private String classDetailInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "class_atk_type")
    private ClassAtkType classAtkType;

    @Column(name = "class_bonus_stat_a")
    private Double classBonusStatA;

    @Column(name = "class_bonus_stat_b")
    private Double classBonusStatB;

    @Column(name = "class_bonus_stat_c")
    private Integer classBonusStatC;

    @Column(name = "class_bonus_stat_d")
    private Integer classBonusStatD;

    @Column(name = "class_bonus_stat_e")
    private Integer classBonusStatE;

    @Column(name = "class_bonus_stat_f")
    private Integer classBonusStatF;

    @Column(name = "class_bonus_stat_g")
    private Integer classBonusStatG;

    @Column(name = "class_bonus_stat_h")
    private Integer classBonusStatH;

    @Column(name = "class_bonus_hp")
    private Double classBonusHP;

    @Column(name = "class_bonus_sp")
    private Double classBonusSP;

    @OneToOne(mappedBy = "playerClass")
    @JsonIgnore
    private Player player;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public PlayerClass className(String className) {
        this.className = className;
        return this;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDetailInfo() {
        return classDetailInfo;
    }

    public PlayerClass classDetailInfo(String classDetailInfo) {
        this.classDetailInfo = classDetailInfo;
        return this;
    }

    public void setClassDetailInfo(String classDetailInfo) {
        this.classDetailInfo = classDetailInfo;
    }

    public ClassAtkType getClassAtkType() {
        return classAtkType;
    }

    public PlayerClass classAtkType(ClassAtkType classAtkType) {
        this.classAtkType = classAtkType;
        return this;
    }

    public void setClassAtkType(ClassAtkType classAtkType) {
        this.classAtkType = classAtkType;
    }

    public Double getClassBonusStatA() {
        return classBonusStatA;
    }

    public PlayerClass classBonusStatA(Double classBonusStatA) {
        this.classBonusStatA = classBonusStatA;
        return this;
    }

    public void setClassBonusStatA(Double classBonusStatA) {
        this.classBonusStatA = classBonusStatA;
    }

    public Double getClassBonusStatB() {
        return classBonusStatB;
    }

    public PlayerClass classBonusStatB(Double classBonusStatB) {
        this.classBonusStatB = classBonusStatB;
        return this;
    }

    public void setClassBonusStatB(Double classBonusStatB) {
        this.classBonusStatB = classBonusStatB;
    }

    public Integer getClassBonusStatC() {
        return classBonusStatC;
    }

    public PlayerClass classBonusStatC(Integer classBonusStatC) {
        this.classBonusStatC = classBonusStatC;
        return this;
    }

    public void setClassBonusStatC(Integer classBonusStatC) {
        this.classBonusStatC = classBonusStatC;
    }

    public Integer getClassBonusStatD() {
        return classBonusStatD;
    }

    public PlayerClass classBonusStatD(Integer classBonusStatD) {
        this.classBonusStatD = classBonusStatD;
        return this;
    }

    public void setClassBonusStatD(Integer classBonusStatD) {
        this.classBonusStatD = classBonusStatD;
    }

    public Integer getClassBonusStatE() {
        return classBonusStatE;
    }

    public PlayerClass classBonusStatE(Integer classBonusStatE) {
        this.classBonusStatE = classBonusStatE;
        return this;
    }

    public void setClassBonusStatE(Integer classBonusStatE) {
        this.classBonusStatE = classBonusStatE;
    }

    public Integer getClassBonusStatF() {
        return classBonusStatF;
    }

    public PlayerClass classBonusStatF(Integer classBonusStatF) {
        this.classBonusStatF = classBonusStatF;
        return this;
    }

    public void setClassBonusStatF(Integer classBonusStatF) {
        this.classBonusStatF = classBonusStatF;
    }

    public Integer getClassBonusStatG() {
        return classBonusStatG;
    }

    public PlayerClass classBonusStatG(Integer classBonusStatG) {
        this.classBonusStatG = classBonusStatG;
        return this;
    }

    public void setClassBonusStatG(Integer classBonusStatG) {
        this.classBonusStatG = classBonusStatG;
    }

    public Integer getClassBonusStatH() {
        return classBonusStatH;
    }

    public PlayerClass classBonusStatH(Integer classBonusStatH) {
        this.classBonusStatH = classBonusStatH;
        return this;
    }

    public void setClassBonusStatH(Integer classBonusStatH) {
        this.classBonusStatH = classBonusStatH;
    }

    public Double getClassBonusHP() {
        return classBonusHP;
    }

    public PlayerClass classBonusHP(Double classBonusHP) {
        this.classBonusHP = classBonusHP;
        return this;
    }

    public void setClassBonusHP(Double classBonusHP) {
        this.classBonusHP = classBonusHP;
    }

    public Double getClassBonusSP() {
        return classBonusSP;
    }

    public PlayerClass classBonusSP(Double classBonusSP) {
        this.classBonusSP = classBonusSP;
        return this;
    }

    public void setClassBonusSP(Double classBonusSP) {
        this.classBonusSP = classBonusSP;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerClass player(Player player) {
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
        if (!(o instanceof PlayerClass)) {
            return false;
        }
        return id != null && id.equals(((PlayerClass) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlayerClass{" +
            "id=" + getId() +
            ", className='" + getClassName() + "'" +
            ", classDetailInfo='" + getClassDetailInfo() + "'" +
            ", classAtkType='" + getClassAtkType() + "'" +
            ", classBonusStatA=" + getClassBonusStatA() +
            ", classBonusStatB=" + getClassBonusStatB() +
            ", classBonusStatC=" + getClassBonusStatC() +
            ", classBonusStatD=" + getClassBonusStatD() +
            ", classBonusStatE=" + getClassBonusStatE() +
            ", classBonusStatF=" + getClassBonusStatF() +
            ", classBonusStatG=" + getClassBonusStatG() +
            ", classBonusStatH=" + getClassBonusStatH() +
            ", classBonusHP=" + getClassBonusHP() +
            ", classBonusSP=" + getClassBonusSP() +
            "}";
    }
}
