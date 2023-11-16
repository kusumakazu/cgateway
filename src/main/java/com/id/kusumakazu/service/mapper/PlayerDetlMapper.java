package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.PlayerDetlDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlayerDetl} and its DTO {@link PlayerDetlDTO}.
 */
@Mapper(componentModel = "spring", uses = {PlayerInventoryMapper.class, PlayerCurrencyMapper.class})
public interface PlayerDetlMapper extends EntityMapper<PlayerDetlDTO, PlayerDetl> {

    @Mapping(source = "playerInventory.id", target = "playerInventoryId")
    @Mapping(source = "playerCurrency.id", target = "playerCurrencyId")
    PlayerDetlDTO toDto(PlayerDetl playerDetl);

    @Mapping(source = "playerInventoryId", target = "playerInventory")
    @Mapping(source = "playerCurrencyId", target = "playerCurrency")
    @Mapping(target = "player", ignore = true)
    PlayerDetl toEntity(PlayerDetlDTO playerDetlDTO);

    default PlayerDetl fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlayerDetl playerDetl = new PlayerDetl();
        playerDetl.setId(id);
        return playerDetl;
    }
}
