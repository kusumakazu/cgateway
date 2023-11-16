package com.id.kusumakazu.service.impl;

import com.id.kusumakazu.service.ItemObjectService;
import com.id.kusumakazu.domain.ItemObject;
import com.id.kusumakazu.repository.ItemObjectRepository;
import com.id.kusumakazu.service.dto.ItemObjectDTO;
import com.id.kusumakazu.service.mapper.ItemObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class ItemObjectServiceImpl implements ItemObjectService {

    private final Logger log = LoggerFactory.getLogger(ItemObjectServiceImpl.class);

    private final ItemObjectRepository itemObjectRepository;

    private final ItemObjectMapper itemObjectMapper;

    public ItemObjectServiceImpl(ItemObjectRepository itemObjectRepository, ItemObjectMapper itemObjectMapper) {
        this.itemObjectRepository = itemObjectRepository;
        this.itemObjectMapper = itemObjectMapper;
    }

    @Override
    public ItemObjectDTO save(ItemObjectDTO itemObjectDTO) {
        log.debug("Request to save ItemObject : {}", itemObjectDTO);
        ItemObject itemObject = itemObjectMapper.toEntity(itemObjectDTO);
        itemObject = itemObjectRepository.save(itemObject);
        return itemObjectMapper.toDto(itemObject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemObjectDTO> findAll() {
        log.debug("Request to get all ItemObjects");
        return itemObjectRepository.findAll().stream()
            .map(itemObjectMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ItemObjectDTO> findOne(Long id) {
        log.debug("Request to get ItemObject : {}", id);
        return itemObjectRepository.findById(id)
            .map(itemObjectMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemObject : {}", id);
        itemObjectRepository.deleteById(id);
    }
}
