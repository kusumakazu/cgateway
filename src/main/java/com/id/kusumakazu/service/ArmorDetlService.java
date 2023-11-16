package com.id.kusumakazu.service;

import com.id.kusumakazu.service.dto.ArmorDetlDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.ArmorDetl}.
 */
public interface ArmorDetlService {

    /**
     * Save a armorDetl.
     *
     * @param armorDetlDTO the entity to save.
     * @return the persisted entity.
     */
    ArmorDetlDTO save(ArmorDetlDTO armorDetlDTO);

    /**
     * Get all the armorDetls.
     *
     * @return the list of entities.
     */
    List<ArmorDetlDTO> findAll();


    /**
     * Get the "id" armorDetl.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ArmorDetlDTO> findOne(Long id);

    /**
     * Delete the "id" armorDetl.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
