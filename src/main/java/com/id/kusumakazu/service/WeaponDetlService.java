package com.id.kusumakazu.service;

import com.id.kusumakazu.service.dto.WeaponDetlDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.WeaponDetl}.
 */
public interface WeaponDetlService {

    /**
     * Save a weaponDetl.
     *
     * @param weaponDetlDTO the entity to save.
     * @return the persisted entity.
     */
    WeaponDetlDTO save(WeaponDetlDTO weaponDetlDTO);

    /**
     * Get all the weaponDetls.
     *
     * @return the list of entities.
     */
    List<WeaponDetlDTO> findAll();


    /**
     * Get the "id" weaponDetl.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WeaponDetlDTO> findOne(Long id);

    /**
     * Delete the "id" weaponDetl.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
