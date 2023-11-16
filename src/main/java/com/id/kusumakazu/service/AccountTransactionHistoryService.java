package com.id.kusumakazu.service;

import com.id.kusumakazu.service.dto.AccountTransactionHistoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.id.kusumakazu.domain.AccountTransactionHistory}.
 */
public interface AccountTransactionHistoryService {

    /**
     * Save a accountTransactionHistory.
     *
     * @param accountTransactionHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    AccountTransactionHistoryDTO save(AccountTransactionHistoryDTO accountTransactionHistoryDTO);

    /**
     * Get all the accountTransactionHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AccountTransactionHistoryDTO> findAll(Pageable pageable);


    /**
     * Get the "id" accountTransactionHistory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountTransactionHistoryDTO> findOne(Long id);

    /**
     * Delete the "id" accountTransactionHistory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
