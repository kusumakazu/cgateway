package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.WeaponDetlDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WeaponDetl} and its DTO {@link WeaponDetlDTO}.
 */
@Mapper(componentModel = "spring", uses = {PlayerMapper.class, WeaponMapper.class, EnchantmentMapper.class})
public interface WeaponDetlMapper extends EntityMapper<WeaponDetlDTO, WeaponDetl> {

    @Mapping(source = "player.id", target = "playerId")
    @Mapping(source = "weapon.id", target = "weaponId")
    @Mapping(source = "enchantment.id", target = "enchantmentId")
    WeaponDetlDTO toDto(WeaponDetl weaponDetl);

    @Mapping(source = "playerId", target = "player")
    @Mapping(source = "weaponId", target = "weapon")
    @Mapping(source = "enchantmentId", target = "enchantment")
    WeaponDetl toEntity(WeaponDetlDTO weaponDetlDTO);

    default WeaponDetl fromId(Long id) {
        if (id == null) {
            return null;
        }
        WeaponDetl weaponDetl = new WeaponDetl();
        weaponDetl.setId(id);
        return weaponDetl;
    }
}
