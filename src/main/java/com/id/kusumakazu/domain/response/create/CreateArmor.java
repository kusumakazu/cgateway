package com.id.kusumakazu.domain.response.create;


import com.id.kusumakazu.service.dto.ArmorDTO;
import com.id.kusumakazu.service.dto.ArmorDetlDTO;
import com.id.kusumakazu.service.dto.ItemObjectDTO;



import java.io.Serializable;

public class CreateArmor implements Serializable {

    private ItemObjectDTO itemObjectDTO;
    private ArmorDTO armorDTO;
    private ArmorDetlDTO armorDetl;

    @Override
    public String toString() {
        return "CreateArmor{" +
            "itemObjectDTO=" + itemObjectDTO +
            ", armorDTO=" + armorDTO +
            ", armorDetl=" + armorDetl +
            '}';
    }

    public ItemObjectDTO getItemObjectDTO() {
        return itemObjectDTO;
    }

    public void setItemObjectDTO(ItemObjectDTO itemObjectDTO) {
        this.itemObjectDTO = itemObjectDTO;
    }

    public ArmorDTO getArmorDTO() {
        return armorDTO;
    }

    public void setArmorDTO(ArmorDTO armorDTO) {
        this.armorDTO = armorDTO;
    }

    public ArmorDetlDTO getArmorDetl() {
        return armorDetl;
    }

    public void setArmorDetl(ArmorDetlDTO armorDetl) {
        this.armorDetl = armorDetl;
    }
}
