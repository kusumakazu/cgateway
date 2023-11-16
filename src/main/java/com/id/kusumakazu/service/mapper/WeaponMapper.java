package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.WeaponDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Weapon} and its DTO {@link WeaponDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WeaponMapper extends EntityMapper<WeaponDTO, Weapon> {


    @Mapping(target = "itemObjects", ignore = true)
    @Mapping(target = "removeItemObject", ignore = true)
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
