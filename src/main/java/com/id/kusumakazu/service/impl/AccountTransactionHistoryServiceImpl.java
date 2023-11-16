package com.id.kusumakazu.service.impl;

import com.id.kusumakazu.service.AccountTransactionHistoryService;
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
public class AccountTransactionHistoryServiceImpl implements AccountTransactionHistoryService {

    private final Logger log = LoggerFactory.getLogger(AccountTransactionHistoryServiceImpl.class);

    private final AccountTransactionHistoryRepository accountTransactionHistoryRepository;

    private final AccountTransactionHistoryMapper accountTransactionHistoryMapper;

    public AccountTransactionHistoryServiceImpl(AccountTransactionHistoryRepository accountTransactionHistoryRepository, AccountTransactionHistoryMapper accountTransactionHistoryMapper) {
        this.accountTransactionHistoryRepository = accountTransactionHistoryRepository;
        this.accountTransactionHistoryMapper = accountTransactionHistoryMapper;
    }

    @Override
    public AccountTransactionHistoryDTO save(AccountTransactionHistoryDTO accountTransactionHistoryDTO) {
        log.debug("Request to save AccountTransactionHistory : {}", accountTransactionHistoryDTO);
        AccountTransactionHistory accountTransactionHistory = accountTransactionHistoryMapper.toEntity(accountTransactionHistoryDTO);
        accountTransactionHistory = accountTransactionHistoryRepository.save(accountTransactionHistory);
        return accountTransactionHistoryMapper.toDto(accountTransactionHistory);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccountTransactionHistoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccountTransactionHistories");
        return accountTransactionHistoryRepository.findAll(pageable)
            .map(accountTransactionHistoryMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AccountTransactionHistoryDTO> findOne(Long id) {
        log.debug("Request to get AccountTransactionHistory : {}", id);
        return accountTransactionHistoryRepository.findById(id)
            .map(accountTransactionHistoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountTransactionHistory : {}", id);
        accountTransactionHistoryRepository.deleteById(id);
    }
}
