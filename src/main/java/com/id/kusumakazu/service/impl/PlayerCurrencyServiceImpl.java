package com.id.kusumakazu.service.impl;

import com.id.kusumakazu.service.PlayerCurrencyService;
import com.id.kusumakazu.domain.PlayerCurrency;
import com.id.kusumakazu.repository.PlayerCurrencyRepository;
import com.id.kusumakazu.service.dto.PlayerCurrencyDTO;
import com.id.kusumakazu.service.mapper.PlayerCurrencyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link PlayerCurrency}.
 */
@Service
@Transactional
public class PlayerCurrencyServiceImpl implements PlayerCurrencyService {

    private final Logger log = LoggerFactory.getLogger(PlayerCurrencyServiceImpl.class);

    private final PlayerCurrencyRepository playerCurrencyRepository;

    private final PlayerCurrencyMapper playerCurrencyMapper;

    public PlayerCurrencyServiceImpl(PlayerCurrencyRepository playerCurrencyRepository, PlayerCurrencyMapper playerCurrencyMapper) {
        this.playerCurrencyRepository = playerCurrencyRepository;
        this.playerCurrencyMapper = playerCurrencyMapper;
    }

    @Override
    public PlayerCurrencyDTO save(PlayerCurrencyDTO playerCurrencyDTO) {
        log.debug("Request to save PlayerCurrency : {}", playerCurrencyDTO);
        PlayerCurrency playerCurrency = playerCurrencyMapper.toEntity(playerCurrencyDTO);
        playerCurrency = playerCurrencyRepository.save(playerCurrency);
        return playerCurrencyMapper.toDto(playerCurrency);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerCurrencyDTO> findAll() {
        log.debug("Request to get all PlayerCurrencies");
        return playerCurrencyRepository.findAll().stream()
            .map(playerCurrencyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the playerCurrencies where Player is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PlayerCurrencyDTO> findAllWherePlayerIsNull() {
        log.debug("Request to get all playerCurrencies where Player is null");
        return StreamSupport
            .stream(playerCurrencyRepository.findAll().spliterator(), false)
            .filter(playerCurrency -> playerCurrency.getPlayer() == null)
            .map(playerCurrencyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PlayerCurrencyDTO> findOne(Long id) {
        log.debug("Request to get PlayerCurrency : {}", id);
        return playerCurrencyRepository.findById(id)
            .map(playerCurrencyMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlayerCurrency : {}", id);
        playerCurrencyRepository.deleteById(id);
    }
}
