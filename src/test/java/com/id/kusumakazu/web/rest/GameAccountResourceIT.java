package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.GameAccount;
import com.id.kusumakazu.repository.GameAccountRepository;
import com.id.kusumakazu.service.GameAccountService;
import com.id.kusumakazu.service.dto.GameAccountDTO;
import com.id.kusumakazu.service.mapper.GameAccountMapper;

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

/**
 * Integration tests for the {@link GameAccountResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GameAccountResourceIT {

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    @Autowired
    private GameAccountRepository gameAccountRepository;

    @Autowired
    private GameAccountMapper gameAccountMapper;

    @Autowired
    private GameAccountService gameAccountService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGameAccountMockMvc;

    private GameAccount gameAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GameAccount createEntity(EntityManager em) {
        GameAccount gameAccount = new GameAccount()
            .accountName(DEFAULT_ACCOUNT_NAME);
        return gameAccount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GameAccount createUpdatedEntity(EntityManager em) {
        GameAccount gameAccount = new GameAccount()
            .accountName(UPDATED_ACCOUNT_NAME);
        return gameAccount;
    }

    @BeforeEach
    public void initTest() {
        gameAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createGameAccount() throws Exception {
        int databaseSizeBeforeCreate = gameAccountRepository.findAll().size();
        // Create the GameAccount
        GameAccountDTO gameAccountDTO = gameAccountMapper.toDto(gameAccount);
        restGameAccountMockMvc.perform(post("/api/game-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gameAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the GameAccount in the database
        List<GameAccount> gameAccountList = gameAccountRepository.findAll();
        assertThat(gameAccountList).hasSize(databaseSizeBeforeCreate + 1);
        GameAccount testGameAccount = gameAccountList.get(gameAccountList.size() - 1);
        assertThat(testGameAccount.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    public void createGameAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gameAccountRepository.findAll().size();

        // Create the GameAccount with an existing ID
        gameAccount.setId(1L);
        GameAccountDTO gameAccountDTO = gameAccountMapper.toDto(gameAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGameAccountMockMvc.perform(post("/api/game-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gameAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GameAccount in the database
        List<GameAccount> gameAccountList = gameAccountRepository.findAll();
        assertThat(gameAccountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAccountNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = gameAccountRepository.findAll().size();
        // set the field null
        gameAccount.setAccountName(null);

        // Create the GameAccount, which fails.
        GameAccountDTO gameAccountDTO = gameAccountMapper.toDto(gameAccount);


        restGameAccountMockMvc.perform(post("/api/game-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gameAccountDTO)))
            .andExpect(status().isBadRequest());

        List<GameAccount> gameAccountList = gameAccountRepository.findAll();
        assertThat(gameAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGameAccounts() throws Exception {
        // Initialize the database
        gameAccountRepository.saveAndFlush(gameAccount);

        // Get all the gameAccountList
        restGameAccountMockMvc.perform(get("/api/game-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gameAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME)));
    }
    
    @Test
    @Transactional
    public void getGameAccount() throws Exception {
        // Initialize the database
        gameAccountRepository.saveAndFlush(gameAccount);

        // Get the gameAccount
        restGameAccountMockMvc.perform(get("/api/game-accounts/{id}", gameAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gameAccount.getId().intValue()))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingGameAccount() throws Exception {
        // Get the gameAccount
        restGameAccountMockMvc.perform(get("/api/game-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGameAccount() throws Exception {
        // Initialize the database
        gameAccountRepository.saveAndFlush(gameAccount);

        int databaseSizeBeforeUpdate = gameAccountRepository.findAll().size();

        // Update the gameAccount
        GameAccount updatedGameAccount = gameAccountRepository.findById(gameAccount.getId()).get();
        // Disconnect from session so that the updates on updatedGameAccount are not directly saved in db
        em.detach(updatedGameAccount);
        updatedGameAccount
            .accountName(UPDATED_ACCOUNT_NAME);
        GameAccountDTO gameAccountDTO = gameAccountMapper.toDto(updatedGameAccount);

        restGameAccountMockMvc.perform(put("/api/game-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gameAccountDTO)))
            .andExpect(status().isOk());

        // Validate the GameAccount in the database
        List<GameAccount> gameAccountList = gameAccountRepository.findAll();
        assertThat(gameAccountList).hasSize(databaseSizeBeforeUpdate);
        GameAccount testGameAccount = gameAccountList.get(gameAccountList.size() - 1);
        assertThat(testGameAccount.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingGameAccount() throws Exception {
        int databaseSizeBeforeUpdate = gameAccountRepository.findAll().size();

        // Create the GameAccount
        GameAccountDTO gameAccountDTO = gameAccountMapper.toDto(gameAccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGameAccountMockMvc.perform(put("/api/game-accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gameAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GameAccount in the database
        List<GameAccount> gameAccountList = gameAccountRepository.findAll();
        assertThat(gameAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGameAccount() throws Exception {
        // Initialize the database
        gameAccountRepository.saveAndFlush(gameAccount);

        int databaseSizeBeforeDelete = gameAccountRepository.findAll().size();

        // Delete the gameAccount
        restGameAccountMockMvc.perform(delete("/api/game-accounts/{id}", gameAccount.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GameAccount> gameAccountList = gameAccountRepository.findAll();
        assertThat(gameAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
