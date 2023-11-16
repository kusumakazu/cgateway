package com.id.kusumakazu.domain.response.create;

import com.id.kusumakazu.service.dto.ItemObjectDTO;
import com.id.kusumakazu.service.dto.WeaponDTO;


import java.io.Serializable;

public class CreateWeapon implements Serializable {

    private ItemObjectDTO itemObjectDTO;
    private WeaponDTO weaponDTO;

    public ItemObjectDTO getItemObjectDTO() {
        return itemObjectDTO;
    }

    public void setItemObjectDTO(ItemObjectDTO itemObjectDTO) {
        this.itemObjectDTO = itemObjectDTO;
    }

    public WeaponDTO getWeaponDTO() {
        return weaponDTO;
    }

    public void setWeaponDTO(WeaponDTO weaponDTO) {
        this.weaponDTO = weaponDTO;
    }

    @Override
    public String toString() {
        return "CreateWeapon{" +
            "itemObjectDTO=" + itemObjectDTO +
            ", weaponDTO=" + weaponDTO +
            '}';
    }
}
