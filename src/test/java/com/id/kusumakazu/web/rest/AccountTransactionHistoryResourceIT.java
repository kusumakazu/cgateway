package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.AccountTransactionHistory;
import com.id.kusumakazu.repository.AccountTransactionHistoryRepository;
import com.id.kusumakazu.service.AccountTransactionHistoryService;
import com.id.kusumakazu.service.dto.AccountTransactionHistoryDTO;
import com.id.kusumakazu.service.mapper.AccountTransactionHistoryMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.id.kusumakazu.domain.enumeration.TransactionType;
/**
 * Integration tests for the {@link AccountTransactionHistoryResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AccountTransactionHistoryResourceIT {

    private static final String DEFAULT_TRANSACTION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_CODE = "BBBBBBBBBB";

    private static final TransactionType DEFAULT_TRANSACTION_TYPE = TransactionType.TOPUP;
    private static final TransactionType UPDATED_TRANSACTION_TYPE = TransactionType.TRADE;

    private static final String DEFAULT_TRANSACTION_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_DETAIL = "BBBBBBBBBB";

    @Autowired
    private AccountTransactionHistoryRepository accountTransactionHistoryRepository;

    @Autowired
    private AccountTransactionHistoryMapper accountTransactionHistoryMapper;

    @Autowired
    private AccountTransactionHistoryService accountTransactionHistoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAccountTransactionHistoryMockMvc;

    private AccountTransactionHistory accountTransactionHistory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountTransactionHistory createEntity(EntityManager em) {
        AccountTransactionHistory accountTransactionHistory = new AccountTransactionHistory()
            .transactionCode(DEFAULT_TRANSACTION_CODE)
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .transactionDetail(DEFAULT_TRANSACTION_DETAIL);
        return accountTransactionHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountTransactionHistory createUpdatedEntity(EntityManager em) {
        AccountTransactionHistory accountTransactionHistory = new AccountTransactionHistory()
            .transactionCode(UPDATED_TRANSACTION_CODE)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .transactionDetail(UPDATED_TRANSACTION_DETAIL);
        return accountTransactionHistory;
    }

    @BeforeEach
    public void initTest() {
        accountTransactionHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountTransactionHistory() throws Exception {
        int databaseSizeBeforeCreate = accountTransactionHistoryRepository.findAll().size();
        // Create the AccountTransactionHistory
        AccountTransactionHistoryDTO accountTransactionHistoryDTO = accountTransactionHistoryMapper.toDto(accountTransactionHistory);
        restAccountTransactionHistoryMockMvc.perform(post("/api/account-transaction-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountTransactionHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountTransactionHistory in the database
        List<AccountTransactionHistory> accountTransactionHistoryList = accountTransactionHistoryRepository.findAll();
        assertThat(accountTransactionHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        AccountTransactionHistory testAccountTransactionHistory = accountTransactionHistoryList.get(accountTransactionHistoryList.size() - 1);
        assertThat(testAccountTransactionHistory.getTransactionCode()).isEqualTo(DEFAULT_TRANSACTION_CODE);
        assertThat(testAccountTransactionHistory.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testAccountTransactionHistory.getTransactionDetail()).isEqualTo(DEFAULT_TRANSACTION_DETAIL);
    }

    @Test
    @Transactional
    public void createAccountTransactionHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountTransactionHistoryRepository.findAll().size();

        // Create the AccountTransactionHistory with an existing ID
        accountTransactionHistory.setId(1L);
        AccountTransactionHistoryDTO accountTransactionHistoryDTO = accountTransactionHistoryMapper.toDto(accountTransactionHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountTransactionHistoryMockMvc.perform(post("/api/account-transaction-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountTransactionHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountTransactionHistory in the database
        List<AccountTransactionHistory> accountTransactionHistoryList = accountTransactionHistoryRepository.findAll();
        assertThat(accountTransactionHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccountTransactionHistories() throws Exception {
        // Initialize the database
        accountTransactionHistoryRepository.saveAndFlush(accountTransactionHistory);

        // Get all the accountTransactionHistoryList
        restAccountTransactionHistoryMockMvc.perform(get("/api/account-transaction-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountTransactionHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].transactionCode").value(hasItem(DEFAULT_TRANSACTION_CODE)))
            .andExpect(jsonPath("$.[*].transactionType").value(hasItem(DEFAULT_TRANSACTION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].transactionDetail").value(hasItem(DEFAULT_TRANSACTION_DETAIL)));
    }
    
    @Test
    @Transactional
    public void getAccountTransactionHistory() throws Exception {
        // Initialize the database
        accountTransactionHistoryRepository.saveAndFlush(accountTransactionHistory);

        // Get the accountTransactionHistory
        restAccountTransactionHistoryMockMvc.perform(get("/api/account-transaction-histories/{id}", accountTransactionHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(accountTransactionHistory.getId().intValue()))
            .andExpect(jsonPath("$.transactionCode").value(DEFAULT_TRANSACTION_CODE))
            .andExpect(jsonPath("$.transactionType").value(DEFAULT_TRANSACTION_TYPE.toString()))
            .andExpect(jsonPath("$.transactionDetail").value(DEFAULT_TRANSACTION_DETAIL));
    }
    @Test
    @Transactional
    public void getNonExistingAccountTransactionHistory() throws Exception {
        // Get the accountTransactionHistory
        restAccountTransactionHistoryMockMvc.perform(get("/api/account-transaction-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountTransactionHistory() throws Exception {
        // Initialize the database
        accountTransactionHistoryRepository.saveAndFlush(accountTransactionHistory);

        int databaseSizeBeforeUpdate = accountTransactionHistoryRepository.findAll().size();

        // Update the accountTransactionHistory
        AccountTransactionHistory updatedAccountTransactionHistory = accountTransactionHistoryRepository.findById(accountTransactionHistory.getId()).get();
        // Disconnect from session so that the updates on updatedAccountTransactionHistory are not directly saved in db
        em.detach(updatedAccountTransactionHistory);
        updatedAccountTransactionHistory
            .transactionCode(UPDATED_TRANSACTION_CODE)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .transactionDetail(UPDATED_TRANSACTION_DETAIL);
        AccountTransactionHistoryDTO accountTransactionHistoryDTO = accountTransactionHistoryMapper.toDto(updatedAccountTransactionHistory);

        restAccountTransactionHistoryMockMvc.perform(put("/api/account-transaction-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountTransactionHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the AccountTransactionHistory in the database
        List<AccountTransactionHistory> accountTransactionHistoryList = accountTransactionHistoryRepository.findAll();
        assertThat(accountTransactionHistoryList).hasSize(databaseSizeBeforeUpdate);
        AccountTransactionHistory testAccountTransactionHistory = accountTransactionHistoryList.get(accountTransactionHistoryList.size() - 1);
        assertThat(testAccountTransactionHistory.getTransactionCode()).isEqualTo(UPDATED_TRANSACTION_CODE);
        assertThat(testAccountTransactionHistory.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testAccountTransactionHistory.getTransactionDetail()).isEqualTo(UPDATED_TRANSACTION_DETAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountTransactionHistory() throws Exception {
        int databaseSizeBeforeUpdate = accountTransactionHistoryRepository.findAll().size();

        // Create the AccountTransactionHistory
        AccountTransactionHistoryDTO accountTransactionHistoryDTO = accountTransactionHistoryMapper.toDto(accountTransactionHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountTransactionHistoryMockMvc.perform(put("/api/account-transaction-histories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(accountTransactionHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountTransactionHistory in the database
        List<AccountTransactionHistory> accountTransactionHistoryList = accountTransactionHistoryRepository.findAll();
        assertThat(accountTransactionHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountTransactionHistory() throws Exception {
        // Initialize the database
        accountTransactionHistoryRepository.saveAndFlush(accountTransactionHistory);

        int databaseSizeBeforeDelete = accountTransactionHistoryRepository.findAll().size();

        // Delete the accountTransactionHistory
        restAccountTransactionHistoryMockMvc.perform(delete("/api/account-transaction-histories/{id}", accountTransactionHistory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountTransactionHistory> accountTransactionHistoryList = accountTransactionHistoryRepository.findAll();
        assertThat(accountTransactionHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
