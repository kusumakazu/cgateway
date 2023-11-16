package com.id.kusumakazu.service.dto;

import java.io.Serializable;
import com.id.kusumakazu.domain.enumeration.ClassAtkType;

/**
 * A DTO for the {@link com.id.kusumakazu.domain.PlayerClass} entity.
 */
public class PlayerClassDTO implements Serializable {
    
    private Long id;

    private String className;

    private String classDetailInfo;

    private ClassAtkType classAtkType;

    private Double classBonusStatA;

    private Double classBonusStatB;

    private Integer classBonusStatC;

    private Integer classBonusStatD;

    private Integer classBonusStatE;

    private Integer classBonusStatF;

    private Integer classBonusStatG;

    private Integer classBonusStatH;

    private Double classBonusHP;

    private Double classBonusSP;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDetailInfo() {
        return classDetailInfo;
    }

    public void setClassDetailInfo(String classDetailInfo) {
        this.classDetailInfo = classDetailInfo;
    }

    public ClassAtkType getClassAtkType() {
        return classAtkType;
    }

    public void setClassAtkType(ClassAtkType classAtkType) {
        this.classAtkType = classAtkType;
    }

    public Double getClassBonusStatA() {
        return classBonusStatA;
    }

    public void setClassBonusStatA(Double classBonusStatA) {
        this.classBonusStatA = classBonusStatA;
    }

    public Double getClassBonusStatB() {
        return classBonusStatB;
    }

    public void setClassBonusStatB(Double classBonusStatB) {
        this.classBonusStatB = classBonusStatB;
    }

    public Integer getClassBonusStatC() {
        return classBonusStatC;
    }

    public void setClassBonusStatC(Integer classBonusStatC) {
        this.classBonusStatC = classBonusStatC;
    }

    public Integer getClassBonusStatD() {
        return classBonusStatD;
    }

    public void setClassBonusStatD(Integer classBonusStatD) {
        this.classBonusStatD = classBonusStatD;
    }

    public Integer getClassBonusStatE() {
        return classBonusStatE;
    }

    public void setClassBonusStatE(Integer classBonusStatE) {
        this.classBonusStatE = classBonusStatE;
    }

    public Integer getClassBonusStatF() {
        return classBonusStatF;
    }

    public void setClassBonusStatF(Integer classBonusStatF) {
        this.classBonusStatF = classBonusStatF;
    }

    public Integer getClassBonusStatG() {
        return classBonusStatG;
    }

    public void setClassBonusStatG(Integer classBonusStatG) {
        this.classBonusStatG = classBonusStatG;
    }

    public Integer getClassBonusStatH() {
        return classBonusStatH;
    }

    public void setClassBonusStatH(Integer classBonusStatH) {
        this.classBonusStatH = classBonusStatH;
    }

    public Double getClassBonusHP() {
        return classBonusHP;
    }

    public void setClassBonusHP(Double classBonusHP) {
        this.classBonusHP = classBonusHP;
    }

    public Double getClassBonusSP() {
        return classBonusSP;
    }

    public void setClassBonusSP(Double classBonusSP) {
        this.classBonusSP = classBonusSP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerClassDTO)) {
            return false;
        }

        return id != null && id.equals(((PlayerClassDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlayerClassDTO{" +
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
