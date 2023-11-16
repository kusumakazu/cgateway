package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.PlayerDetlDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlayerDetl} and its DTO {@link PlayerDetlDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlayerDetlMapper extends EntityMapper<PlayerDetlDTO, PlayerDetl> {


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
