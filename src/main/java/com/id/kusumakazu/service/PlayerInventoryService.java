package com.id.kusumakazu.service;

import com.id.kusumakazu.service.dto.PlayerInventoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.PlayerInventory}.
 */
public interface PlayerInventoryService {

    /**
     * Save a playerInventory.
     *
     * @param playerInventoryDTO the entity to save.
     * @return the persisted entity.
     */
    PlayerInventoryDTO save(PlayerInventoryDTO playerInventoryDTO);

    /**
     * Get all the playerInventories.
     *
     * @return the list of entities.
     */
    List<PlayerInventoryDTO> findAll();


    /**
     * Get the "id" playerInventory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PlayerInventoryDTO> findOne(Long id);

    /**
     * Delete the "id" playerInventory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
