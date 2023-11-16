package com.id.kusumakazu.service;

import com.id.kusumakazu.domain.Armor;
import com.id.kusumakazu.repository.ArmorRepository;
import com.id.kusumakazu.service.dto.ArmorDTO;
import com.id.kusumakazu.service.mapper.ArmorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Armor}.
 */
@Service
@Transactional
public class ArmorService {

    private final Logger log = LoggerFactory.getLogger(ArmorService.class);

    private final ArmorRepository armorRepository;

    private final ArmorMapper armorMapper;

    public ArmorService(ArmorRepository armorRepository, ArmorMapper armorMapper) {
        this.armorRepository = armorRepository;
        this.armorMapper = armorMapper;
    }

    /**
     * Save a armor.
     *
     * @param armorDTO the entity to save.
     * @return the persisted entity.
     */
    public ArmorDTO save(ArmorDTO armorDTO) {
        log.debug("Request to save Armor : {}", armorDTO);
        Armor armor = armorMapper.toEntity(armorDTO);
        armor = armorRepository.save(armor);
        return armorMapper.toDto(armor);
    }

    /**
     * Get all the armors.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ArmorDTO> findAll() {
        log.debug("Request to get all Armors");
        return armorRepository.findAll().stream()
            .map(armorMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one armor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ArmorDTO> findOne(Long id) {
        log.debug("Request to get Armor : {}", id);
        return armorRepository.findById(id)
            .map(armorMapper::toDto);
    }

    /**
     * Delete the armor by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Armor : {}", id);
        armorRepository.deleteById(id);
    }
}
