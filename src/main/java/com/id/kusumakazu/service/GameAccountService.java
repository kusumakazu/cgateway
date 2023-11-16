package com.id.kusumakazu.service;

import com.id.kusumakazu.service.dto.GameAccountDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.GameAccount}.
 */
public interface GameAccountService {

    /**
     * Save a gameAccount.
     *
     * @param gameAccountDTO the entity to save.
     * @return the persisted entity.
     */
    GameAccountDTO save(GameAccountDTO gameAccountDTO);

    /**
     * Get all the gameAccounts.
     *
     * @return the list of entities.
     */
    List<GameAccountDTO> findAll();


    /**
     * Get the "id" gameAccount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GameAccountDTO> findOne(Long id);

    /**
     * Delete the "id" gameAccount.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
