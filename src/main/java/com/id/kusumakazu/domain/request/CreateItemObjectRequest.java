package com.id.kusumakazu.domain.request;

import com.id.kusumakazu.domain.response.create.CreateArmor;
import com.id.kusumakazu.domain.response.create.CreateWeapon;
import com.id.kusumakazu.service.dto.PlayerDTO;

import java.io.Serializable;

public class CreateItemObjectRequest implements Serializable {

    private CreateWeapon createWeapon;
    private CreateArmor createArmor;

    @Override
    public String toString() {
        return "CreateItemObjectRequest{" +
            "createWeapon=" + createWeapon +
            ", createArmor=" + createArmor +
            '}';
    }

    public CreateWeapon getCreateWeapon() {
        return createWeapon;
    }

    public void setCreateWeapon(CreateWeapon createWeapon) {
        this.createWeapon = createWeapon;
    }

    public CreateArmor getCreateArmor() {
        return createArmor;
    }

    public void setCreateArmor(CreateArmor createArmor) {
        this.createArmor = createArmor;
    }
}
