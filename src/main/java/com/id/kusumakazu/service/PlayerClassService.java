package com.id.kusumakazu.service;

import com.id.kusumakazu.service.dto.PlayerClassDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.PlayerClass}.
 */
public interface PlayerClassService {

    /**
     * Save a playerClass.
     *
     * @param playerClassDTO the entity to save.
     * @return the persisted entity.
     */
    PlayerClassDTO save(PlayerClassDTO playerClassDTO);

    /**
     * Get all the playerClasses.
     *
     * @return the list of entities.
     */
    List<PlayerClassDTO> findAll();
    /**
     * Get all the PlayerClassDTO where Player is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<PlayerClassDTO> findAllWherePlayerIsNull();


    /**
     * Get the "id" playerClass.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PlayerClassDTO> findOne(Long id);

    /**
     * Delete the "id" playerClass.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
