package com.id.kusumakazu.service.impl;

import com.id.kusumakazu.service.WeaponDetlService;
import com.id.kusumakazu.domain.WeaponDetl;
import com.id.kusumakazu.repository.WeaponDetlRepository;
import com.id.kusumakazu.service.dto.WeaponDetlDTO;
import com.id.kusumakazu.service.mapper.WeaponDetlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link WeaponDetl}.
 */
@Service
@Transactional
public class WeaponDetlServiceImpl implements WeaponDetlService {

    private final Logger log = LoggerFactory.getLogger(WeaponDetlServiceImpl.class);

    private final WeaponDetlRepository weaponDetlRepository;

    private final WeaponDetlMapper weaponDetlMapper;

    public WeaponDetlServiceImpl(WeaponDetlRepository weaponDetlRepository, WeaponDetlMapper weaponDetlMapper) {
        this.weaponDetlRepository = weaponDetlRepository;
        this.weaponDetlMapper = weaponDetlMapper;
    }

    @Override
    public WeaponDetlDTO save(WeaponDetlDTO weaponDetlDTO) {
        log.debug("Request to save WeaponDetl : {}", weaponDetlDTO);
        WeaponDetl weaponDetl = weaponDetlMapper.toEntity(weaponDetlDTO);
        weaponDetl = weaponDetlRepository.save(weaponDetl);
        return weaponDetlMapper.toDto(weaponDetl);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WeaponDetlDTO> findAll() {
        log.debug("Request to get all WeaponDetls");
        return weaponDetlRepository.findAll().stream()
            .map(weaponDetlMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WeaponDetlDTO> findOne(Long id) {
        log.debug("Request to get WeaponDetl : {}", id);
        return weaponDetlRepository.findById(id)
            .map(weaponDetlMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WeaponDetl : {}", id);
        weaponDetlRepository.deleteById(id);
    }
}
