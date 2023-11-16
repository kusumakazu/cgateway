package com.id.kusumakazu.service;

import com.id.kusumakazu.service.dto.PlayerCurrencyDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.PlayerCurrency}.
 */
public interface PlayerCurrencyService {

    /**
     * Save a playerCurrency.
     *
     * @param playerCurrencyDTO the entity to save.
     * @return the persisted entity.
     */
    PlayerCurrencyDTO save(PlayerCurrencyDTO playerCurrencyDTO);

    /**
     * Get all the playerCurrencies.
     *
     * @return the list of entities.
     */
    List<PlayerCurrencyDTO> findAll();
    /**
     * Get all the PlayerCurrencyDTO where Player is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<PlayerCurrencyDTO> findAllWherePlayerIsNull();


    /**
     * Get the "id" playerCurrency.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PlayerCurrencyDTO> findOne(Long id);

    /**
     * Delete the "id" playerCurrency.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
