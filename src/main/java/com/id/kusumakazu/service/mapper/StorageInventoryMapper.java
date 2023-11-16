package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.StorageInventoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link StorageInventory} and its DTO {@link StorageInventoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StorageInventoryMapper extends EntityMapper<StorageInventoryDTO, StorageInventory> {


    @Mapping(target = "itemObjects", ignore = true)
    @Mapping(target = "removeItemObject", ignore = true)
    StorageInventory toEntity(StorageInventoryDTO storageInventoryDTO);

    default StorageInventory fromId(Long id) {
        if (id == null) {
            return null;
        }
        StorageInventory storageInventory = new StorageInventory();
        storageInventory.setId(id);
        return storageInventory;
    }
}
