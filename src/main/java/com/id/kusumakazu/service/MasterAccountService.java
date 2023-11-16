package com.id.kusumakazu.service;

import com.id.kusumakazu.service.dto.MasterAccountDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.MasterAccount}.
 */
public interface MasterAccountService {

    /**
     * Save a masterAccount.
     *
     * @param masterAccountDTO the entity to save.
     * @return the persisted entity.
     */
    MasterAccountDTO save(MasterAccountDTO masterAccountDTO);

    /**
     * Get all the masterAccounts.
     *
     * @return the list of entities.
     */
    List<MasterAccountDTO> findAll();


    /**
     * Get the "id" masterAccount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MasterAccountDTO> findOne(Long id);

    /**
     * Delete the "id" masterAccount.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
