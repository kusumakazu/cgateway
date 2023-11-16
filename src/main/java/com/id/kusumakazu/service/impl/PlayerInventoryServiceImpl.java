package com.id.kusumakazu.service.impl;

import com.id.kusumakazu.service.PlayerInventoryService;
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

/**
 * Service Implementation for managing {@link PlayerInventory}.
 */
@Service
@Transactional
public class PlayerInventoryServiceImpl implements PlayerInventoryService {

    private final Logger log = LoggerFactory.getLogger(PlayerInventoryServiceImpl.class);

    private final PlayerInventoryRepository playerInventoryRepository;

    private final PlayerInventoryMapper playerInventoryMapper;

    public PlayerInventoryServiceImpl(PlayerInventoryRepository playerInventoryRepository, PlayerInventoryMapper playerInventoryMapper) {
        this.playerInventoryRepository = playerInventoryRepository;
        this.playerInventoryMapper = playerInventoryMapper;
    }

    @Override
    public PlayerInventoryDTO save(PlayerInventoryDTO playerInventoryDTO) {
        log.debug("Request to save PlayerInventory : {}", playerInventoryDTO);
        PlayerInventory playerInventory = playerInventoryMapper.toEntity(playerInventoryDTO);
        playerInventory = playerInventoryRepository.save(playerInventory);
        return playerInventoryMapper.toDto(playerInventory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerInventoryDTO> findAll() {
        log.debug("Request to get all PlayerInventories");
        return playerInventoryRepository.findAll().stream()
            .map(playerInventoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PlayerInventoryDTO> findOne(Long id) {
        log.debug("Request to get PlayerInventory : {}", id);
        return playerInventoryRepository.findById(id)
            .map(playerInventoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlayerInventory : {}", id);
        playerInventoryRepository.deleteById(id);
    }
}
