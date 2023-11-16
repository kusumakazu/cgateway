package com.id.kusumakazu.service;

import com.id.kusumakazu.domain.ItemObject;
import com.id.kusumakazu.repository.ItemObjectRepository;
import com.id.kusumakazu.service.dto.ItemObjectDTO;
import com.id.kusumakazu.service.mapper.ItemObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ItemObject}.
 */
@Service
@Transactional
public class ItemObjectService {

    private final Logger log = LoggerFactory.getLogger(ItemObjectService.class);

    private final ItemObjectRepository itemObjectRepository;

    private final ItemObjectMapper itemObjectMapper;

    public ItemObjectService(ItemObjectRepository itemObjectRepository, ItemObjectMapper itemObjectMapper) {
        this.itemObjectRepository = itemObjectRepository;
        this.itemObjectMapper = itemObjectMapper;
    }

    /**
     * Save a itemObject.
     *
     * @param itemObjectDTO the entity to save.
     * @return the persisted entity.
     */
    public ItemObjectDTO save(ItemObjectDTO itemObjectDTO) {
        log.debug("Request to save ItemObject : {}", itemObjectDTO);
        ItemObject itemObject = itemObjectMapper.toEntity(itemObjectDTO);
        itemObject = itemObjectRepository.save(itemObject);
        return itemObjectMapper.toDto(itemObject);
    }

    /**
     * Get all the itemObjects.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ItemObjectDTO> findAll() {
        log.debug("Request to get all ItemObjects");
        return itemObjectRepository.findAllWithEagerRelationships().stream()
            .map(itemObjectMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get all the itemObjects with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<ItemObjectDTO> findAllWithEagerRelationships(Pageable pageable) {
        return itemObjectRepository.findAllWithEagerRelationships(pageable).map(itemObjectMapper::toDto);
    }

    /**
     * Get one itemObject by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ItemObjectDTO> findOne(Long id) {
        log.debug("Request to get ItemObject : {}", id);
        return itemObjectRepository.findOneWithEagerRelationships(id)
            .map(itemObjectMapper::toDto);
    }

    /**
     * Delete the itemObject by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ItemObject : {}", id);
        itemObjectRepository.deleteById(id);
    }
}
