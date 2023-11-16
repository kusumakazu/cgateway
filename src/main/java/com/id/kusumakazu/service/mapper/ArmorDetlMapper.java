package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.ArmorDetlDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ArmorDetl} and its DTO {@link ArmorDetlDTO}.
 */
@Mapper(componentModel = "spring", uses = {PlayerMapper.class, ArmorMapper.class, EnchantmentMapper.class})
public interface ArmorDetlMapper extends EntityMapper<ArmorDetlDTO, ArmorDetl> {

    @Mapping(source = "player.id", target = "playerId")
    @Mapping(source = "armor.id", target = "armorId")
    @Mapping(source = "enchantment.id", target = "enchantmentId")
    ArmorDetlDTO toDto(ArmorDetl armorDetl);

    @Mapping(source = "playerId", target = "player")
    @Mapping(source = "armorId", target = "armor")
    @Mapping(source = "enchantmentId", target = "enchantment")
    ArmorDetl toEntity(ArmorDetlDTO armorDetlDTO);

    default ArmorDetl fromId(Long id) {
        if (id == null) {
            return null;
        }
        ArmorDetl armorDetl = new ArmorDetl();
        armorDetl.setId(id);
        return armorDetl;
    }
}
