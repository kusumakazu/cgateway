package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.GameAccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GameAccount} and its DTO {@link GameAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {MasterAccountMapper.class})
public interface GameAccountMapper extends EntityMapper<GameAccountDTO, GameAccount> {

    @Mapping(source = "masterAccount.id", target = "masterAccountId")
    GameAccountDTO toDto(GameAccount gameAccount);

    @Mapping(target = "players", ignore = true)
    @Mapping(target = "removePlayer", ignore = true)
    @Mapping(source = "masterAccountId", target = "masterAccount")
    GameAccount toEntity(GameAccountDTO gameAccountDTO);

    default GameAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        GameAccount gameAccount = new GameAccount();
        gameAccount.setId(id);
        return gameAccount;
    }
}
