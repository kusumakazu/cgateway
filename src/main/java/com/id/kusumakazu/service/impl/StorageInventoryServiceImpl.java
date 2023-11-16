package com.id.kusumakazu.service.impl;

import com.id.kusumakazu.service.StorageInventoryService;
import com.id.kusumakazu.domain.StorageInventory;
import com.id.kusumakazu.repository.StorageInventoryRepository;
import com.id.kusumakazu.service.dto.StorageInventoryDTO;
import com.id.kusumakazu.service.mapper.StorageInventoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link StorageInventory}.
 */
@Service
@Transactional
public class StorageInventoryServiceImpl implements StorageInventoryService {

    private final Logger log = LoggerFactory.getLogger(StorageInventoryServiceImpl.class);

    private final StorageInventoryRepository storageInventoryRepository;

    private final StorageInventoryMapper storageInventoryMapper;

    public StorageInventoryServiceImpl(StorageInventoryRepository storageInventoryRepository, StorageInventoryMapper storageInventoryMapper) {
        this.storageInventoryRepository = storageInventoryRepository;
        this.storageInventoryMapper = storageInventoryMapper;
    }

    @Override
    public StorageInventoryDTO save(StorageInventoryDTO storageInventoryDTO) {
        log.debug("Request to save StorageInventory : {}", storageInventoryDTO);
        StorageInventory storageInventory = storageInventoryMapper.toEntity(storageInventoryDTO);
        storageInventory = storageInventoryRepository.save(storageInventory);
        return storageInventoryMapper.toDto(storageInventory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StorageInventoryDTO> findAll() {
        log.debug("Request to get all StorageInventories");
        return storageInventoryRepository.findAll().stream()
            .map(storageInventoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<StorageInventoryDTO> findOne(Long id) {
        log.debug("Request to get StorageInventory : {}", id);
        return storageInventoryRepository.findById(id)
            .map(storageInventoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StorageInventory : {}", id);
        storageInventoryRepository.deleteById(id);
    }
}
