package com.id.kusumakazu.service;

import com.id.kusumakazu.domain.PlayerDetl;
import com.id.kusumakazu.repository.PlayerDetlRepository;
import com.id.kusumakazu.service.dto.PlayerDetlDTO;
import com.id.kusumakazu.service.mapper.PlayerDetlMapper;
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
 * Service Implementation for managing {@link PlayerDetl}.
 */
@Service
@Transactional
public class PlayerDetlService {

    private final Logger log = LoggerFactory.getLogger(PlayerDetlService.class);

    private final PlayerDetlRepository playerDetlRepository;

    private final PlayerDetlMapper playerDetlMapper;

    public PlayerDetlService(PlayerDetlRepository playerDetlRepository, PlayerDetlMapper playerDetlMapper) {
        this.playerDetlRepository = playerDetlRepository;
        this.playerDetlMapper = playerDetlMapper;
    }

    /**
     * Save a playerDetl.
     *
     * @param playerDetlDTO the entity to save.
     * @return the persisted entity.
     */
    public PlayerDetlDTO save(PlayerDetlDTO playerDetlDTO) {
        log.debug("Request to save PlayerDetl : {}", playerDetlDTO);
        PlayerDetl playerDetl = playerDetlMapper.toEntity(playerDetlDTO);
        playerDetl = playerDetlRepository.save(playerDetl);
        return playerDetlMapper.toDto(playerDetl);
    }

    /**
     * Get all the playerDetls.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PlayerDetlDTO> findAll() {
        log.debug("Request to get all PlayerDetls");
        return playerDetlRepository.findAll().stream()
            .map(playerDetlMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the playerDetls where Player is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PlayerDetlDTO> findAllWherePlayerIsNull() {
        log.debug("Request to get all playerDetls where Player is null");
        return StreamSupport
            .stream(playerDetlRepository.findAll().spliterator(), false)
            .filter(playerDetl -> playerDetl.getPlayer() == null)
            .map(playerDetlMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one playerDetl by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PlayerDetlDTO> findOne(Long id) {
        log.debug("Request to get PlayerDetl : {}", id);
        return playerDetlRepository.findById(id)
            .map(playerDetlMapper::toDto);
    }

    /**
     * Delete the playerDetl by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PlayerDetl : {}", id);
        playerDetlRepository.deleteById(id);
    }
}
