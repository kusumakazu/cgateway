package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.ItemObjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemObject} and its DTO {@link ItemObjectDTO}.
 */
@Mapper(componentModel = "spring", uses = {WeaponMapper.class, ArmorMapper.class, PlayerInventoryMapper.class, StorageInventoryMapper.class})
public interface ItemObjectMapper extends EntityMapper<ItemObjectDTO, ItemObject> {

    @Mapping(source = "playerInventory.id", target = "playerInventoryId")
    @Mapping(source = "storageInventory.id", target = "storageInventoryId")
    ItemObjectDTO toDto(ItemObject itemObject);

    @Mapping(target = "removeWeapon", ignore = true)
    @Mapping(target = "removeArmor", ignore = true)
    @Mapping(source = "playerInventoryId", target = "playerInventory")
    @Mapping(source = "storageInventoryId", target = "storageInventory")
    ItemObject toEntity(ItemObjectDTO itemObjectDTO);

    default ItemObject fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemObject itemObject = new ItemObject();
        itemObject.setId(id);
        return itemObject;
    }
}
