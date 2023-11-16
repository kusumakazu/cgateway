package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.EnchantmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Enchantment} and its DTO {@link EnchantmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnchantmentMapper extends EntityMapper<EnchantmentDTO, Enchantment> {


    @Mapping(target = "weaponDetls", ignore = true)
    @Mapping(target = "removeWeaponDetl", ignore = true)
    @Mapping(target = "armorDetls", ignore = true)
    @Mapping(target = "removeArmorDetl", ignore = true)
    Enchantment toEntity(EnchantmentDTO enchantmentDTO);

    default Enchantment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Enchantment enchantment = new Enchantment();
        enchantment.setId(id);
        return enchantment;
    }
}
