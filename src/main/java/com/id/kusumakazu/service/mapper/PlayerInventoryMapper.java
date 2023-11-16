package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.PlayerInventoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlayerInventory} and its DTO {@link PlayerInventoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {ItemObjectMapper.class, PlayerMapper.class})
public interface PlayerInventoryMapper extends EntityMapper<PlayerInventoryDTO, PlayerInventory> {

    @Mapping(source = "itemObject.id", target = "itemObjectId")
    @Mapping(source = "player.id", target = "playerId")
    PlayerInventoryDTO toDto(PlayerInventory playerInventory);

    @Mapping(source = "itemObjectId", target = "itemObject")
    @Mapping(source = "playerId", target = "player")
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
