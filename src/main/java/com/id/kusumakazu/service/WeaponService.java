package com.id.kusumakazu.service;

import com.id.kusumakazu.service.dto.WeaponDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.Weapon}.
 */
public interface WeaponService {

    /**
     * Save a weapon.
     *
     * @param weaponDTO the entity to save.
     * @return the persisted entity.
     */
    WeaponDTO save(WeaponDTO weaponDTO);

    /**
     * Get all the weapons.
     *
     * @return the list of entities.
     */
    List<WeaponDTO> findAll();


    /**
     * Get the "id" weapon.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WeaponDTO> findOne(Long id);

    /**
     * Delete the "id" weapon.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
