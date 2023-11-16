package com.id.kusumakazu.service.impl;

import com.id.kusumakazu.service.EnchantmentService;
import com.id.kusumakazu.domain.Enchantment;
import com.id.kusumakazu.repository.EnchantmentRepository;
import com.id.kusumakazu.service.dto.EnchantmentDTO;
import com.id.kusumakazu.service.mapper.EnchantmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Enchantment}.
 */
@Service
@Transactional
public class EnchantmentServiceImpl implements EnchantmentService {

    private final Logger log = LoggerFactory.getLogger(EnchantmentServiceImpl.class);

    private final EnchantmentRepository enchantmentRepository;

    private final EnchantmentMapper enchantmentMapper;

    public EnchantmentServiceImpl(EnchantmentRepository enchantmentRepository, EnchantmentMapper enchantmentMapper) {
        this.enchantmentRepository = enchantmentRepository;
        this.enchantmentMapper = enchantmentMapper;
    }

    @Override
    public EnchantmentDTO save(EnchantmentDTO enchantmentDTO) {
        log.debug("Request to save Enchantment : {}", enchantmentDTO);
        Enchantment enchantment = enchantmentMapper.toEntity(enchantmentDTO);
        enchantment = enchantmentRepository.save(enchantment);
        return enchantmentMapper.toDto(enchantment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnchantmentDTO> findAll() {
        log.debug("Request to get all Enchantments");
        return enchantmentRepository.findAll().stream()
            .map(enchantmentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EnchantmentDTO> findOne(Long id) {
        log.debug("Request to get Enchantment : {}", id);
        return enchantmentRepository.findById(id)
            .map(enchantmentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Enchantment : {}", id);
        enchantmentRepository.deleteById(id);
    }
}
