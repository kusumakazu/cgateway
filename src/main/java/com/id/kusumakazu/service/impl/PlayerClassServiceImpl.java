package com.id.kusumakazu.service.impl;

import com.id.kusumakazu.service.PlayerClassService;
import com.id.kusumakazu.domain.PlayerClass;
import com.id.kusumakazu.repository.PlayerClassRepository;
import com.id.kusumakazu.service.dto.PlayerClassDTO;
import com.id.kusumakazu.service.mapper.PlayerClassMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link PlayerClass}.
 */
@Service
@Transactional
public class PlayerClassServiceImpl implements PlayerClassService {

    private final Logger log = LoggerFactory.getLogger(PlayerClassServiceImpl.class);

    private final PlayerClassRepository playerClassRepository;

    private final PlayerClassMapper playerClassMapper;

    public PlayerClassServiceImpl(PlayerClassRepository playerClassRepository, PlayerClassMapper playerClassMapper) {
        this.playerClassRepository = playerClassRepository;
        this.playerClassMapper = playerClassMapper;
    }

    @Override
    public PlayerClassDTO save(PlayerClassDTO playerClassDTO) {
        log.debug("Request to save PlayerClass : {}", playerClassDTO);
        PlayerClass playerClass = playerClassMapper.toEntity(playerClassDTO);
        playerClass = playerClassRepository.save(playerClass);
        return playerClassMapper.toDto(playerClass);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerClassDTO> findAll() {
        log.debug("Request to get all PlayerClasses");
        return playerClassRepository.findAll().stream()
            .map(playerClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the playerClasses where Player is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PlayerClassDTO> findAllWherePlayerIsNull() {
        log.debug("Request to get all playerClasses where Player is null");
        return StreamSupport
            .stream(playerClassRepository.findAll().spliterator(), false)
            .filter(playerClass -> playerClass.getPlayer() == null)
            .map(playerClassMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PlayerClassDTO> findOne(Long id) {
        log.debug("Request to get PlayerClass : {}", id);
        return playerClassRepository.findById(id)
            .map(playerClassMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlayerClass : {}", id);
        playerClassRepository.deleteById(id);
    }
}
