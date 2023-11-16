package com.id.kusumakazu.service.impl;

import com.id.kusumakazu.domain.enumeration.ItemObjectType;
import com.id.kusumakazu.domain.request.CreateItemObjectRequest;
import com.id.kusumakazu.domain.response.CreateItemObjectResponse;
import com.id.kusumakazu.domain.response.create.CreateArmor;
import com.id.kusumakazu.domain.response.create.CreateWeapon;
import com.id.kusumakazu.service.*;
import com.id.kusumakazu.domain.ItemObject;
import com.id.kusumakazu.repository.ItemObjectRepository;
import com.id.kusumakazu.service.dto.*;
import com.id.kusumakazu.service.mapper.ItemObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ItemObject}.
 */
@Service
@Transactional
public class ItemObjectServiceImpl implements ItemObjectService {

    private final Logger log = LoggerFactory.getLogger(ItemObjectServiceImpl.class);

    private final ItemObjectRepository itemObjectRepository;

    private final ItemObjectMapper itemObjectMapper;
    @Autowired
    private WeaponService weaponService;
    @Autowired
    private WeaponDetlService weaponDetlService;
    @Autowired
    private ArmorService armorService;
    @Autowired
    private ArmorDetlService armorDetlService;

    public ItemObjectServiceImpl(ItemObjectRepository itemObjectRepository, ItemObjectMapper itemObjectMapper) {
        this.itemObjectRepository = itemObjectRepository;
        this.itemObjectMapper = itemObjectMapper;
    }

    @Override
    public ItemObjectDTO save(ItemObjectDTO itemObjectDTO) {
        log.debug("Request to save ItemObject : {}", itemObjectDTO);
        ItemObject itemObject = itemObjectMapper.toEntity(itemObjectDTO);
        itemObject = itemObjectRepository.save(itemObject);
        return itemObjectMapper.toDto(itemObject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemObjectDTO> findAll() {
        log.debug("Request to get all ItemObjects");
        return itemObjectRepository.findAll().stream()
            .map(itemObjectMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ItemObjectDTO> findOne(Long id) {
        log.debug("Request to get ItemObject : {}", id);
        return itemObjectRepository.findById(id)
            .map(itemObjectMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemObject : {}", id);
        itemObjectRepository.deleteById(id);
    }

    @Override
    public CreateItemObjectResponse createItemObject(CreateItemObjectRequest request) {
        log.info("Request to create a Item Object");
        CreateItemObjectResponse createItemObjectResponse = new CreateItemObjectResponse();

        if (request != null){
            if (request.getCreateWeapon() != null){
                if (request.getCreateWeapon().getItemObjectDTO().getItemObjectType() == ItemObjectType.WEAPON ){
                    CreateWeapon createWeapon = request.getCreateWeapon();

                    ItemObjectDTO itemObjectDTO = createWeapon.getItemObjectDTO();
                    itemObjectDTO = save(itemObjectDTO);

                    WeaponDTO weaponDTO = createWeapon.getWeaponDTO();
                    weaponDTO.setItemObjectId(itemObjectDTO.getId());
                    weaponDTO = weaponService.save(weaponDTO);

                    createWeapon.setItemObjectDTO(itemObjectDTO);
                    createWeapon.setWeaponDTO(weaponDTO);
                    createItemObjectResponse.setCreateWeapon(createWeapon);
                }
            } else if (request.getCreateArmor() != null) {
                if (request.getCreateArmor().getItemObjectDTO().getItemObjectType() == ItemObjectType.ARMOR) {
                    CreateArmor createArmor = request.getCreateArmor();
                    ItemObjectDTO itemObjectDTO = createArmor.getItemObjectDTO();
                    itemObjectDTO = save(itemObjectDTO);

                    ArmorDTO armorDTO = createArmor.getArmorDTO();
                    armorDTO.setItemObjectId(itemObjectDTO.getId());
                    armorDTO = armorService.save(armorDTO);

                    createArmor.setArmorDTO(armorDTO);
                    createArmor.setItemObjectDTO(itemObjectDTO);
                    createItemObjectResponse.setCreateArmor(createArmor);
                }
            }
        }else {
            createItemObjectResponse.setError("DATA NULL OR NOT FILLED");
        }
        return createItemObjectResponse;
    }
}
