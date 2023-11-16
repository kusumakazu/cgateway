package com.id.kusumakazu.service;

import com.id.kusumakazu.domain.GameAccount;
import com.id.kusumakazu.repository.GameAccountRepository;
import com.id.kusumakazu.service.dto.GameAccountDTO;
import com.id.kusumakazu.service.mapper.GameAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link GameAccount}.
 */
@Service
@Transactional
public class GameAccountService {

    private final Logger log = LoggerFactory.getLogger(GameAccountService.class);

    private final GameAccountRepository gameAccountRepository;

    private final GameAccountMapper gameAccountMapper;

    public GameAccountService(GameAccountRepository gameAccountRepository, GameAccountMapper gameAccountMapper) {
        this.gameAccountRepository = gameAccountRepository;
        this.gameAccountMapper = gameAccountMapper;
    }

    /**
     * Save a gameAccount.
     *
     * @param gameAccountDTO the entity to save.
     * @return the persisted entity.
     */
    public GameAccountDTO save(GameAccountDTO gameAccountDTO) {
        log.debug("Request to save GameAccount : {}", gameAccountDTO);
        GameAccount gameAccount = gameAccountMapper.toEntity(gameAccountDTO);
        gameAccount = gameAccountRepository.save(gameAccount);
        return gameAccountMapper.toDto(gameAccount);
    }

    /**
     * Get all the gameAccounts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<GameAccountDTO> findAll() {
        log.debug("Request to get all GameAccounts");
        return gameAccountRepository.findAll().stream()
            .map(gameAccountMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one gameAccount by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GameAccountDTO> findOne(Long id) {
        log.debug("Request to get GameAccount : {}", id);
        return gameAccountRepository.findById(id)
            .map(gameAccountMapper::toDto);
    }

    /**
     * Delete the gameAccount by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GameAccount : {}", id);
        gameAccountRepository.deleteById(id);
    }
}
