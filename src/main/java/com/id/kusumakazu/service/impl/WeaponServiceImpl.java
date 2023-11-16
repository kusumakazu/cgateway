package com.id.kusumakazu.service.impl;

import com.id.kusumakazu.service.WeaponService;
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
public class WeaponServiceImpl implements WeaponService {

    private final Logger log = LoggerFactory.getLogger(WeaponServiceImpl.class);

    private final WeaponRepository weaponRepository;

    private final WeaponMapper weaponMapper;

    public WeaponServiceImpl(WeaponRepository weaponRepository, WeaponMapper weaponMapper) {
        this.weaponRepository = weaponRepository;
        this.weaponMapper = weaponMapper;
    }

    @Override
    public WeaponDTO save(WeaponDTO weaponDTO) {
        log.debug("Request to save Weapon : {}", weaponDTO);
        Weapon weapon = weaponMapper.toEntity(weaponDTO);
        weapon = weaponRepository.save(weapon);
        return weaponMapper.toDto(weapon);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WeaponDTO> findAll() {
        log.debug("Request to get all Weapons");
        return weaponRepository.findAll().stream()
            .map(weaponMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WeaponDTO> findOne(Long id) {
        log.debug("Request to get Weapon : {}", id);
        return weaponRepository.findById(id)
            .map(weaponMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Weapon : {}", id);
        weaponRepository.deleteById(id);
    }
}
