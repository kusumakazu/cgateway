package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.PlayerCurrencyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlayerCurrency} and its DTO {@link PlayerCurrencyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlayerCurrencyMapper extends EntityMapper<PlayerCurrencyDTO, PlayerCurrency> {


    @Mapping(target = "player", ignore = true)
    PlayerCurrency toEntity(PlayerCurrencyDTO playerCurrencyDTO);

    default PlayerCurrency fromId(Long id) {
        if (id == null) {
            return null;
        }
        PlayerCurrency playerCurrency = new PlayerCurrency();
        playerCurrency.setId(id);
        return playerCurrency;
    }
}
