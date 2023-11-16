package com.id.kusumakazu.domain.response;

import com.id.kusumakazu.domain.response.create.CreateArmor;
import com.id.kusumakazu.domain.response.create.CreateWeapon;


import java.io.Serializable;

public class CreateItemObjectResponse implements Serializable {

    private CreateWeapon createWeapon;
    private CreateArmor createArmor;
    private String error;

    @Override
    public String toString() {
        return "CreateItemObjectResponse{" +
            "createWeapon=" + createWeapon +
            ", createArmor=" + createArmor +
            ", error='" + error + '\'' +
            '}';
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
