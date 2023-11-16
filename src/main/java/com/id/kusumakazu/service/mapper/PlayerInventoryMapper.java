package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.PlayerInventoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlayerInventory} and its DTO {@link PlayerInventoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlayerInventoryMapper extends EntityMapper<PlayerInventoryDTO, PlayerInventory> {


    @Mapping(target = "itemObjects", ignore = true)
    @Mapping(target = "removeItemObject", ignore = true)
    @Mapping(target = "playerDetl", ignore = true)
    PlayerInventory toEntity(PlayerInventoryDTO playerInventoryDTO);

    default PlayerInventory fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlayerInventory playerInventory = new PlayerInventory();
        playerInventory.setId(id);
        return playerInventory;
    }
}
