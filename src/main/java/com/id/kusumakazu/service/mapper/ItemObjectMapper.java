package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.ItemObjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemObject} and its DTO {@link ItemObjectDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ItemObjectMapper extends EntityMapper<ItemObjectDTO, ItemObject> {


    @Mapping(target = "storageInventories", ignore = true)
    @Mapping(target = "removeStorageInventory", ignore = true)
    @Mapping(target = "playerInventories", ignore = true)
    @Mapping(target = "removePlayerInventory", ignore = true)
    @Mapping(target = "weapons", ignore = true)
    @Mapping(target = "removeWeapon", ignore = true)
    @Mapping(target = "armors", ignore = true)
    @Mapping(target = "removeArmor", ignore = true)
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
