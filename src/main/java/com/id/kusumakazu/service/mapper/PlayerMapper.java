package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.PlayerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Player} and its DTO {@link PlayerDTO}.
 */
@Mapper(componentModel = "spring", uses = {PlayerDetlMapper.class, PlayerClassMapper.class, PlayerCurrencyMapper.class, GameAccountMapper.class})
public interface PlayerMapper extends EntityMapper<PlayerDTO, Player> {

    @Mapping(source = "playerDetl.id", target = "playerDetlId")
    @Mapping(source = "playerClass.id", target = "playerClassId")
    @Mapping(source = "playerCurrency.id", target = "playerCurrencyId")
    @Mapping(source = "gameAccount.id", target = "gameAccountId")
    PlayerDTO toDto(Player player);

    @Mapping(source = "playerDetlId", target = "playerDetl")
    @Mapping(source = "playerClassId", target = "playerClass")
    @Mapping(source = "playerCurrencyId", target = "playerCurrency")
    @Mapping(target = "playerInventories", ignore = true)
    @Mapping(target = "removePlayerInventory", ignore = true)
    @Mapping(target = "storageInventories", ignore = true)
    @Mapping(target = "removeStorageInventory", ignore = true)
    @Mapping(target = "weaponDetls", ignore = true)
    @Mapping(target = "removeWeaponDetl", ignore = true)
    @Mapping(target = "armorDetls", ignore = true)
    @Mapping(target = "removeArmorDetl", ignore = true)
    @Mapping(source = "gameAccountId", target = "gameAccount")
    Player toEntity(PlayerDTO playerDTO);

    default Player fromId(Long id) {
        if (id == null) {
            return null;
        }
        Player player = new Player();
        player.setId(id);
        return player;
    }
}
