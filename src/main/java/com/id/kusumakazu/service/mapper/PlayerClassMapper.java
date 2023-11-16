package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.PlayerClassDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlayerClass} and its DTO {@link PlayerClassDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlayerClassMapper extends EntityMapper<PlayerClassDTO, PlayerClass> {


    @Mapping(target = "player", ignore = true)
    PlayerClass toEntity(PlayerClassDTO playerClassDTO);

    default PlayerClass fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlayerClass playerClass = new PlayerClass();
        playerClass.setId(id);
        return playerClass;
    }
}
