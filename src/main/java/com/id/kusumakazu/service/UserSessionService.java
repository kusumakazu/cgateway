package com.id.kusumakazu.service;

import com.id.kusumakazu.service.dto.UserSessionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.UserSession}.
 */
public interface UserSessionService {

    /**
     * Save a userSession.
     *
     * @param userSessionDTO the entity to save.
     * @return the persisted entity.
     */
    UserSessionDTO save(UserSessionDTO userSessionDTO);

    /**
     * Get all the userSessions.
     *
     * @return the list of entities.
     */
    List<UserSessionDTO> findAll();


    /**
     * Get the "id" userSession.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserSessionDTO> findOne(Long id);

    /**
     * Delete the "id" userSession.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
