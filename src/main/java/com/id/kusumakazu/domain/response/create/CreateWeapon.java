package com.id.kusumakazu.domain.response.create;

import com.id.kusumakazu.service.dto.ItemObjectDTO;
import com.id.kusumakazu.service.dto.WeaponDTO;
import com.id.kusumakazu.service.dto.WeaponDetlDTO;

import java.io.Serializable;

public class CreateWeapon implements Serializable {

    private ItemObjectDTO itemObjectDTO;
    private WeaponDTO weaponDTO;
    private WeaponDetlDTO weaponDetlDTO;

    @Override
    public String toString() {
        return "CreateWeapon{" +
            "itemObjectDTO=" + itemObjectDTO +
            ", weaponDTO=" + weaponDTO +
            ", weaponDetlDTO=" + weaponDetlDTO +
            '}';
    }

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

    public WeaponDetlDTO getWeaponDetlDTO() {
        return weaponDetlDTO;
    }

    public void setWeaponDetlDTO(WeaponDetlDTO weaponDetlDTO) {
        this.weaponDetlDTO = weaponDetlDTO;
    }
}
