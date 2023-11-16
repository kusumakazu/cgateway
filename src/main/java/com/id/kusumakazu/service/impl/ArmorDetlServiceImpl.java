package com.id.kusumakazu.service.impl;

import com.id.kusumakazu.service.ArmorDetlService;
import com.id.kusumakazu.domain.ArmorDetl;
import com.id.kusumakazu.repository.ArmorDetlRepository;
import com.id.kusumakazu.service.dto.ArmorDetlDTO;
import com.id.kusumakazu.service.mapper.ArmorDetlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ArmorDetl}.
 */
@Service
@Transactional
public class ArmorDetlServiceImpl implements ArmorDetlService {

    private final Logger log = LoggerFactory.getLogger(ArmorDetlServiceImpl.class);

    private final ArmorDetlRepository armorDetlRepository;

    private final ArmorDetlMapper armorDetlMapper;

    public ArmorDetlServiceImpl(ArmorDetlRepository armorDetlRepository, ArmorDetlMapper armorDetlMapper) {
        this.armorDetlRepository = armorDetlRepository;
        this.armorDetlMapper = armorDetlMapper;
    }

    @Override
    public ArmorDetlDTO save(ArmorDetlDTO armorDetlDTO) {
        log.debug("Request to save ArmorDetl : {}", armorDetlDTO);
        ArmorDetl armorDetl = armorDetlMapper.toEntity(armorDetlDTO);
        armorDetl = armorDetlRepository.save(armorDetl);
        return armorDetlMapper.toDto(armorDetl);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArmorDetlDTO> findAll() {
        log.debug("Request to get all ArmorDetls");
        return armorDetlRepository.findAll().stream()
            .map(armorDetlMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ArmorDetlDTO> findOne(Long id) {
        log.debug("Request to get ArmorDetl : {}", id);
        return armorDetlRepository.findById(id)
            .map(armorDetlMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ArmorDetl : {}", id);
        armorDetlRepository.deleteById(id);
    }
}
