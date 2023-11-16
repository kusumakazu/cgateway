package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.MasterAccount;
import com.id.kusumakazu.repository.MasterAccountRepository;
import com.id.kusumakazu.service.MasterAccountService;
import com.id.kusumakazu.service.dto.MasterAccountDTO;
import com.id.kusumakazu.service.mapper.MasterAccountMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.id.kusumakazu.domain.enumeration.AccountStatus;
/**
 * Integration tests for the {@link MasterAccountResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MasterAccountResourceIT {

    private static final String DEFAULT_EMAIL = "oO,aV@c_.ng";
    private static final String UPDATED_EMAIL = "8az@8\\P+.szNDD";

    private static final String DEFAULT_HASH_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_HASH_PASSWORD = "BBBBBBBBBB";

    private static final Long DEFAULT_ACCOUNT_BALANCE = 1L;
    private static final Long UPDATED_ACCOUNT_BALANCE = 2L;

    private static final AccountStatus DEFAULT_ACCOUNT_STATUS = AccountStatus.ACTIVATED;
    private static final AccountStatus UPDATED_ACCOUNT_STATUS = AccountStatus.UNACTIVATED;

    private static final Boolean DEFAULT_SUBSCRIPTION_STATUS = false;
    private static final Boolean UPDATED_SUBSCRIPTION_STATUS = true;

    private static final Instant DEFAULT_SUBSCRIPTION_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUBSCRIPTION_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SUBSCRIPTION_END_DATE = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_END_DATE = "BBBBBBBBBB";

    @Autowired
    private MasterAccountRepository masterAccountRepository;

    @Autowired
    private MasterAccountMapper masterAccountMapper;

    @Autowired
    private MasterAccountService masterAccountService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMasterAccountMockMvc;

    private MasterAccount masterAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MasterAccount createEntity(EntityManager em) {
        MasterAccount masterAccount = new MasterAccount()
            .email(DEFAULT_EMAIL)
            .hashPassword(DEFAULT_HASH_PASSWORD)
            .accountBalance(DEFAULT_ACCOUNT_BALANCE)
            .accountStatus(DEFAULT_ACCOUNT_STATUS)
            .subscriptionStatus(DEFAULT_SUBSCRIPTION_STATUS)
            .subscriptionStartDate(DEFAULT_SUBSCRIPTION_START_DATE)
            .subscriptionEndDate(DEFAULT_SUBSCRIPTION_END_DATE);
        return masterAccount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MasterAccount createUpdatedEntity(EntityManager em) {
        MasterAccount masterAccount = new MasterAccount()
            .email(UPDATED_EMAIL)
            .hashPassword(UPDATED_HASH_PASSWORD)
            .accountBalance(UPDATED_ACCOUNT_BALANCE)
            .accountStatus(UPDATED_ACCOUNT_STATUS)
            .subscriptionStatus(UPDATED_SUBSCRIPTION_STATUS)
            .subscriptionStartDate(UPDATED_SUBSCRIPTION_START_DATE)
            .subscriptionEndDate(UPDATED_SUBSCRIPTION_END_DATE);
        return masterAccount;
    }

    @BeforeEach
    public void initTest() {
        masterAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createMasterAccount() throws Exception {
        int databaseSizeBeforeCreate = masterAccountRepository.findAll().size();
        // Create the MasterAccount
        MasterAccountDTO masterAccountDTO = masterAccountMapper.toDto(masterAccount);
        restMasterAccountMockMvc.perform(post("/api/master-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(masterAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the MasterAccount in the database
        List<MasterAccount> masterAccountList = masterAccountRepository.findAll();
        assertThat(masterAccountList).hasSize(databaseSizeBeforeCreate + 1);
        MasterAccount testMasterAccount = masterAccountList.get(masterAccountList.size() - 1);
        assertThat(testMasterAccount.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testMasterAccount.getHashPassword()).isEqualTo(DEFAULT_HASH_PASSWORD);
        assertThat(testMasterAccount.getAccountBalance()).isEqualTo(DEFAULT_ACCOUNT_BALANCE);
        assertThat(testMasterAccount.getAccountStatus()).isEqualTo(DEFAULT_ACCOUNT_STATUS);
        assertThat(testMasterAccount.isSubscriptionStatus()).isEqualTo(DEFAULT_SUBSCRIPTION_STATUS);
        assertThat(testMasterAccount.getSubscriptionStartDate()).isEqualTo(DEFAULT_SUBSCRIPTION_START_DATE);
        assertThat(testMasterAccount.getSubscriptionEndDate()).isEqualTo(DEFAULT_SUBSCRIPTION_END_DATE);
    }

    @Test
    @Transactional
    public void createMasterAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = masterAccountRepository.findAll().size();

        // Create the MasterAccount with an existing ID
        masterAccount.setId(1L);
        MasterAccountDTO masterAccountDTO = masterAccountMapper.toDto(masterAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMasterAccountMockMvc.perform(post("/api/master-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(masterAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MasterAccount in the database
        List<MasterAccount> masterAccountList = masterAccountRepository.findAll();
        assertThat(masterAccountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = masterAccountRepository.findAll().size();
        // set the field null
        masterAccount.setEmail(null);

        // Create the MasterAccount, which fails.
        MasterAccountDTO masterAccountDTO = masterAccountMapper.toDto(masterAccount);


        restMasterAccountMockMvc.perform(post("/api/master-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(masterAccountDTO)))
            .andExpect(status().isBadRequest());

        List<MasterAccount> masterAccountList = masterAccountRepository.findAll();
        assertThat(masterAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHashPasswordIsRequired() throws Exception {
        int databaseSizeBeforeTest = masterAccountRepository.findAll().size();
        // set the field null
        masterAccount.setHashPassword(null);

        // Create the MasterAccount, which fails.
        MasterAccountDTO masterAccountDTO = masterAccountMapper.toDto(masterAccount);


        restMasterAccountMockMvc.perform(post("/api/master-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(masterAccountDTO)))
            .andExpect(status().isBadRequest());

        List<MasterAccount> masterAccountList = masterAccountRepository.findAll();
        assertThat(masterAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMasterAccounts() throws Exception {
        // Initialize the database
        masterAccountRepository.saveAndFlush(masterAccount);

        // Get all the masterAccountList
        restMasterAccountMockMvc.perform(get("/api/master-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(masterAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].hashPassword").value(hasItem(DEFAULT_HASH_PASSWORD)))
            .andExpect(jsonPath("$.[*].accountBalance").value(hasItem(DEFAULT_ACCOUNT_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].accountStatus").value(hasItem(DEFAULT_ACCOUNT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].subscriptionStatus").value(hasItem(DEFAULT_SUBSCRIPTION_STATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].subscriptionStartDate").value(hasItem(DEFAULT_SUBSCRIPTION_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].subscriptionEndDate").value(hasItem(DEFAULT_SUBSCRIPTION_END_DATE)));
    }
    
    @Test
    @Transactional
    public void getMasterAccount() throws Exception {
        // Initialize the database
        masterAccountRepository.saveAndFlush(masterAccount);

        // Get the masterAccount
        restMasterAccountMockMvc.perform(get("/api/master-accounts/{id}", masterAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(masterAccount.getId().intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.hashPassword").value(DEFAULT_HASH_PASSWORD))
            .andExpect(jsonPath("$.accountBalance").value(DEFAULT_ACCOUNT_BALANCE.intValue()))
            .andExpect(jsonPath("$.accountStatus").value(DEFAULT_ACCOUNT_STATUS.toString()))
            .andExpect(jsonPath("$.subscriptionStatus").value(DEFAULT_SUBSCRIPTION_STATUS.booleanValue()))
            .andExpect(jsonPath("$.subscriptionStartDate").value(DEFAULT_SUBSCRIPTION_START_DATE.toString()))
            .andExpect(jsonPath("$.subscriptionEndDate").value(DEFAULT_SUBSCRIPTION_END_DATE));
    }
    @Test
    @Transactional
    public void getNonExistingMasterAccount() throws Exception {
        // Get the masterAccount
        restMasterAccountMockMvc.perform(get("/api/master-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMasterAccount() throws Exception {
        // Initialize the database
        masterAccountRepository.saveAndFlush(masterAccount);

        int databaseSizeBeforeUpdate = masterAccountRepository.findAll().size();

        // Update the masterAccount
        MasterAccount updatedMasterAccount = masterAccountRepository.findById(masterAccount.getId()).get();
        // Disconnect from session so that the updates on updatedMasterAccount are not directly saved in db
        em.detach(updatedMasterAccount);
        updatedMasterAccount
            .email(UPDATED_EMAIL)
            .hashPassword(UPDATED_HASH_PASSWORD)
            .accountBalance(UPDATED_ACCOUNT_BALANCE)
            .accountStatus(UPDATED_ACCOUNT_STATUS)
            .subscriptionStatus(UPDATED_SUBSCRIPTION_STATUS)
            .subscriptionStartDate(UPDATED_SUBSCRIPTION_START_DATE)
            .subscriptionEndDate(UPDATED_SUBSCRIPTION_END_DATE);
        MasterAccountDTO masterAccountDTO = masterAccountMapper.toDto(updatedMasterAccount);

        restMasterAccountMockMvc.perform(put("/api/master-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(masterAccountDTO)))
            .andExpect(status().isOk());

        // Validate the MasterAccount in the database
        List<MasterAccount> masterAccountList = masterAccountRepository.findAll();
        assertThat(masterAccountList).hasSize(databaseSizeBeforeUpdate);
        MasterAccount testMasterAccount = masterAccountList.get(masterAccountList.size() - 1);
        assertThat(testMasterAccount.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testMasterAccount.getHashPassword()).isEqualTo(UPDATED_HASH_PASSWORD);
        assertThat(testMasterAccount.getAccountBalance()).isEqualTo(UPDATED_ACCOUNT_BALANCE);
        assertThat(testMasterAccount.getAccountStatus()).isEqualTo(UPDATED_ACCOUNT_STATUS);
        assertThat(testMasterAccount.isSubscriptionStatus()).isEqualTo(UPDATED_SUBSCRIPTION_STATUS);
        assertThat(testMasterAccount.getSubscriptionStartDate()).isEqualTo(UPDATED_SUBSCRIPTION_START_DATE);
        assertThat(testMasterAccount.getSubscriptionEndDate()).isEqualTo(UPDATED_SUBSCRIPTION_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingMasterAccount() throws Exception {
        int databaseSizeBeforeUpdate = masterAccountRepository.findAll().size();

        // Create the MasterAccount
        MasterAccountDTO masterAccountDTO = masterAccountMapper.toDto(masterAccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMasterAccountMockMvc.perform(put("/api/master-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(masterAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MasterAccount in the database
        List<MasterAccount> masterAccountList = masterAccountRepository.findAll();
        assertThat(masterAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMasterAccount() throws Exception {
        // Initialize the database
        masterAccountRepository.saveAndFlush(masterAccount);

        int databaseSizeBeforeDelete = masterAccountRepository.findAll().size();

        // Delete the masterAccount
        restMasterAccountMockMvc.perform(delete("/api/master-accounts/{id}", masterAccount.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MasterAccount> masterAccountList = masterAccountRepository.findAll();
        assertThat(masterAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
