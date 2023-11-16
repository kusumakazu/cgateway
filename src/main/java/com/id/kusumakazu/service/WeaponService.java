package com.id.kusumakazu.service;

import com.id.kusumakazu.domain.Weapon;
import com.id.kusumakazu.repository.WeaponRepository;
import com.id.kusumakazu.service.dto.WeaponDTO;
import com.id.kusumakazu.service.mapper.WeaponMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Weapon}.
 */
@Service
@Transactional
public class WeaponService {

    private final Logger log = LoggerFactory.getLogger(WeaponService.class);

    private final WeaponRepository weaponRepository;

    private final WeaponMapper weaponMapper;

    public WeaponService(WeaponRepository weaponRepository, WeaponMapper weaponMapper) {
        this.weaponRepository = weaponRepository;
        this.weaponMapper = weaponMapper;
    }

    /**
     * Save a weapon.
     *
     * @param weaponDTO the entity to save.
     * @return the persisted entity.
     */
    public WeaponDTO save(WeaponDTO weaponDTO) {
        log.debug("Request to save Weapon : {}", weaponDTO);
        Weapon weapon = weaponMapper.toEntity(weaponDTO);
        weapon = weaponRepository.save(weapon);
        return weaponMapper.toDto(weapon);
    }

    /**
     * Get all the weapons.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<WeaponDTO> findAll() {
        log.debug("Request to get all Weapons");
        return weaponRepository.findAll().stream()
            .map(weaponMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one weapon by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WeaponDTO> findOne(Long id) {
        log.debug("Request to get Weapon : {}", id);
        return weaponRepository.findById(id)
            .map(weaponMapper::toDto);
    }

    /**
     * Delete the weapon by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Weapon : {}", id);
        weaponRepository.deleteById(id);
    }
}
