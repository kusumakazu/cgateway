package com.id.kusumakazu.service;

import com.id.kusumakazu.domain.PlayerInventory;
import com.id.kusumakazu.repository.PlayerInventoryRepository;
import com.id.kusumakazu.service.dto.PlayerInventoryDTO;
import com.id.kusumakazu.service.mapper.PlayerInventoryMapper;
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
 * Service Implementation for managing {@link PlayerInventory}.
 */
@Service
@Transactional
public class PlayerInventoryService {

    private final Logger log = LoggerFactory.getLogger(PlayerInventoryService.class);

    private final PlayerInventoryRepository playerInventoryRepository;

    private final PlayerInventoryMapper playerInventoryMapper;

    public PlayerInventoryService(PlayerInventoryRepository playerInventoryRepository, PlayerInventoryMapper playerInventoryMapper) {
        this.playerInventoryRepository = playerInventoryRepository;
        this.playerInventoryMapper = playerInventoryMapper;
    }

    /**
     * Save a playerInventory.
     *
     * @param playerInventoryDTO the entity to save.
     * @return the persisted entity.
     */
    public PlayerInventoryDTO save(PlayerInventoryDTO playerInventoryDTO) {
        log.debug("Request to save PlayerInventory : {}", playerInventoryDTO);
        PlayerInventory playerInventory = playerInventoryMapper.toEntity(playerInventoryDTO);
        playerInventory = playerInventoryRepository.save(playerInventory);
        return playerInventoryMapper.toDto(playerInventory);
    }

    /**
     * Get all the playerInventories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PlayerInventoryDTO> findAll() {
        log.debug("Request to get all PlayerInventories");
        return playerInventoryRepository.findAll().stream()
            .map(playerInventoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the playerInventories where PlayerDetl is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PlayerInventoryDTO> findAllWherePlayerDetlIsNull() {
        log.debug("Request to get all playerInventories where PlayerDetl is null");
        return StreamSupport
            .stream(playerInventoryRepository.findAll().spliterator(), false)
            .filter(playerInventory -> playerInventory.getPlayerDetl() == null)
            .map(playerInventoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one playerInventory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PlayerInventoryDTO> findOne(Long id) {
        log.debug("Request to get PlayerInventory : {}", id);
        return playerInventoryRepository.findById(id)
            .map(playerInventoryMapper::toDto);
    }

    /**
     * Delete the playerInventory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PlayerInventory : {}", id);
        playerInventoryRepository.deleteById(id);
    }
}
