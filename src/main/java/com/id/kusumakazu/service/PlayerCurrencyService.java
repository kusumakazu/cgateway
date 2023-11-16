package com.id.kusumakazu.service;

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
public class PlayerCurrencyService {

    private final Logger log = LoggerFactory.getLogger(PlayerCurrencyService.class);

    private final PlayerCurrencyRepository playerCurrencyRepository;

    private final PlayerCurrencyMapper playerCurrencyMapper;

    public PlayerCurrencyService(PlayerCurrencyRepository playerCurrencyRepository, PlayerCurrencyMapper playerCurrencyMapper) {
        this.playerCurrencyRepository = playerCurrencyRepository;
        this.playerCurrencyMapper = playerCurrencyMapper;
    }

    /**
     * Save a playerCurrency.
     *
     * @param playerCurrencyDTO the entity to save.
     * @return the persisted entity.
     */
    public PlayerCurrencyDTO save(PlayerCurrencyDTO playerCurrencyDTO) {
        log.debug("Request to save PlayerCurrency : {}", playerCurrencyDTO);
        PlayerCurrency playerCurrency = playerCurrencyMapper.toEntity(playerCurrencyDTO);
        playerCurrency = playerCurrencyRepository.save(playerCurrency);
        return playerCurrencyMapper.toDto(playerCurrency);
    }

    /**
     * Get all the playerCurrencies.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PlayerCurrencyDTO> findAll() {
        log.debug("Request to get all PlayerCurrencies");
        return playerCurrencyRepository.findAll().stream()
            .map(playerCurrencyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the playerCurrencies where PlayerDetl is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PlayerCurrencyDTO> findAllWherePlayerDetlIsNull() {
        log.debug("Request to get all playerCurrencies where PlayerDetl is null");
        return StreamSupport
            .stream(playerCurrencyRepository.findAll().spliterator(), false)
            .filter(playerCurrency -> playerCurrency.getPlayerDetl() == null)
            .map(playerCurrencyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one playerCurrency by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PlayerCurrencyDTO> findOne(Long id) {
        log.debug("Request to get PlayerCurrency : {}", id);
        return playerCurrencyRepository.findById(id)
            .map(playerCurrencyMapper::toDto);
    }

    /**
     * Delete the playerCurrency by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PlayerCurrency : {}", id);
        playerCurrencyRepository.deleteById(id);
    }
}
