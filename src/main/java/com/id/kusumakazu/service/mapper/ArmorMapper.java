package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.ArmorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Armor} and its DTO {@link ArmorDTO}.
 */
@Mapper(componentModel = "spring", uses = {ItemObjectMapper.class})
public interface ArmorMapper extends EntityMapper<ArmorDTO, Armor> {

    @Mapping(source = "itemObject.id", target = "itemObjectId")
    ArmorDTO toDto(Armor armor);

    @Mapping(target = "armorDetls", ignore = true)
    @Mapping(target = "removeArmorDetl", ignore = true)
    @Mapping(source = "itemObjectId", target = "itemObject")
    Armor toEntity(ArmorDTO armorDTO);

    default Armor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Armor armor = new Armor();
        armor.setId(id);
        return armor;
    }
}
