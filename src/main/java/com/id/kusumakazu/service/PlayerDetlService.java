package com.id.kusumakazu.service;

import com.id.kusumakazu.service.dto.PlayerDetlDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.PlayerDetl}.
 */
public interface PlayerDetlService {

    /**
     * Save a playerDetl.
     *
     * @param playerDetlDTO the entity to save.
     * @return the persisted entity.
     */
    PlayerDetlDTO save(PlayerDetlDTO playerDetlDTO);

    /**
     * Get all the playerDetls.
     *
     * @return the list of entities.
     */
    List<PlayerDetlDTO> findAll();
    /**
     * Get all the PlayerDetlDTO where Player is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<PlayerDetlDTO> findAllWherePlayerIsNull();


    /**
     * Get the "id" playerDetl.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PlayerDetlDTO> findOne(Long id);

    /**
     * Delete the "id" playerDetl.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
