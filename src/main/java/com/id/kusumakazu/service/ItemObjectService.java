package com.id.kusumakazu.service;

import com.id.kusumakazu.domain.request.CreateItemObjectRequest;
import com.id.kusumakazu.domain.response.CreateItemObjectResponse;
import com.id.kusumakazu.service.dto.ItemObjectDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.ItemObject}.
 */
public interface ItemObjectService {

    /**
     * Save a itemObject.
     *
     * @param itemObjectDTO the entity to save.
     * @return the persisted entity.
     */
    ItemObjectDTO save(ItemObjectDTO itemObjectDTO);

    /**
     * Get all the itemObjects.
     *
     * @return the list of entities.
     */
    List<ItemObjectDTO> findAll();


    /**
     * Get the "id" itemObject.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemObjectDTO> findOne(Long id);

    /**
     * Delete the "id" itemObject.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    // new api
    CreateItemObjectResponse createItemObject(CreateItemObjectRequest request);
}
