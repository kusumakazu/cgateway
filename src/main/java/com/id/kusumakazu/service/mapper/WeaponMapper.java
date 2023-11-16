package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.WeaponDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Weapon} and its DTO {@link WeaponDTO}.
 */
@Mapper(componentModel = "spring", uses = {ItemObjectMapper.class, WeaponDetlMapper.class})
public interface WeaponMapper extends EntityMapper<WeaponDTO, Weapon> {

    @Mapping(source = "itemObject.id", target = "itemObjectId")
    @Mapping(source = "weaponDetl.id", target = "weaponDetlId")
    WeaponDTO toDto(Weapon weapon);

    @Mapping(source = "itemObjectId", target = "itemObject")
    @Mapping(source = "weaponDetlId", target = "weaponDetl")
    Weapon toEntity(WeaponDTO weaponDTO);

    default Weapon fromId(Long id) {
        if (id == null) {
            return null;
        }
        Weapon weapon = new Weapon();
        weapon.setId(id);
        return weapon;
    }
}
