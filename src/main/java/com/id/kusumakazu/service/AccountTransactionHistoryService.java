package com.id.kusumakazu.service;

import com.id.kusumakazu.domain.AccountTransactionHistory;
import com.id.kusumakazu.repository.AccountTransactionHistoryRepository;
import com.id.kusumakazu.service.dto.AccountTransactionHistoryDTO;
import com.id.kusumakazu.service.mapper.AccountTransactionHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AccountTransactionHistory}.
 */
@Service
@Transactional
public class AccountTransactionHistoryService {

    private final Logger log = LoggerFactory.getLogger(AccountTransactionHistoryService.class);

    private final AccountTransactionHistoryRepository accountTransactionHistoryRepository;

    private final AccountTransactionHistoryMapper accountTransactionHistoryMapper;

    public AccountTransactionHistoryService(AccountTransactionHistoryRepository accountTransactionHistoryRepository, AccountTransactionHistoryMapper accountTransactionHistoryMapper) {
        this.accountTransactionHistoryRepository = accountTransactionHistoryRepository;
        this.accountTransactionHistoryMapper = accountTransactionHistoryMapper;
    }

    /**
     * Save a accountTransactionHistory.
     *
     * @param accountTransactionHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    public AccountTransactionHistoryDTO save(AccountTransactionHistoryDTO accountTransactionHistoryDTO) {
        log.debug("Request to save AccountTransactionHistory : {}", accountTransactionHistoryDTO);
        AccountTransactionHistory accountTransactionHistory = accountTransactionHistoryMapper.toEntity(accountTransactionHistoryDTO);
        accountTransactionHistory = accountTransactionHistoryRepository.save(accountTransactionHistory);
        return accountTransactionHistoryMapper.toDto(accountTransactionHistory);
    }

    /**
     * Get all the accountTransactionHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AccountTransactionHistoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccountTransactionHistories");
        return accountTransactionHistoryRepository.findAll(pageable)
            .map(accountTransactionHistoryMapper::toDto);
    }


    /**
     * Get one accountTransactionHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AccountTransactionHistoryDTO> findOne(Long id) {
        log.debug("Request to get AccountTransactionHistory : {}", id);
        return accountTransactionHistoryRepository.findById(id)
            .map(accountTransactionHistoryMapper::toDto);
    }

    /**
     * Delete the accountTransactionHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AccountTransactionHistory : {}", id);
        accountTransactionHistoryRepository.deleteById(id);
    }
}
