package com.id.kusumakazu.service;

import com.id.kusumakazu.service.dto.StorageInventoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.StorageInventory}.
 */
public interface StorageInventoryService {

    /**
     * Save a storageInventory.
     *
     * @param storageInventoryDTO the entity to save.
     * @return the persisted entity.
     */
    StorageInventoryDTO save(StorageInventoryDTO storageInventoryDTO);

    /**
     * Get all the storageInventories.
     *
     * @return the list of entities.
     */
    List<StorageInventoryDTO> findAll();


    /**
     * Get the "id" storageInventory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StorageInventoryDTO> findOne(Long id);

    /**
     * Delete the "id" storageInventory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
