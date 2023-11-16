package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.PlayerCurrency;
import com.id.kusumakazu.repository.PlayerCurrencyRepository;
import com.id.kusumakazu.service.PlayerCurrencyService;
import com.id.kusumakazu.service.dto.PlayerCurrencyDTO;
import com.id.kusumakazu.service.mapper.PlayerCurrencyMapper;

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
 * Integration tests for the {@link PlayerCurrencyResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PlayerCurrencyResourceIT {

    private static final Long DEFAULT_GOLD = 1L;
    private static final Long UPDATED_GOLD = 2L;

    private static final Long DEFAULT_SILVER = 1L;
    private static final Long UPDATED_SILVER = 2L;

    private static final Long DEFAULT_COPPER = 1L;
    private static final Long UPDATED_COPPER = 2L;

    @Autowired
    private PlayerCurrencyRepository playerCurrencyRepository;

    @Autowired
    private PlayerCurrencyMapper playerCurrencyMapper;

    @Autowired
    private PlayerCurrencyService playerCurrencyService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlayerCurrencyMockMvc;

    private PlayerCurrency playerCurrency;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlayerCurrency createEntity(EntityManager em) {
        PlayerCurrency playerCurrency = new PlayerCurrency()
            .gold(DEFAULT_GOLD)
            .silver(DEFAULT_SILVER)
            .copper(DEFAULT_COPPER);
        return playerCurrency;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlayerCurrency createUpdatedEntity(EntityManager em) {
        PlayerCurrency playerCurrency = new PlayerCurrency()
            .gold(UPDATED_GOLD)
            .silver(UPDATED_SILVER)
            .copper(UPDATED_COPPER);
        return playerCurrency;
    }

    @BeforeEach
    public void initTest() {
        playerCurrency = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlayerCurrency() throws Exception {
        int databaseSizeBeforeCreate = playerCurrencyRepository.findAll().size();
        // Create the PlayerCurrency
        PlayerCurrencyDTO playerCurrencyDTO = playerCurrencyMapper.toDto(playerCurrency);
        restPlayerCurrencyMockMvc.perform(post("/api/player-currencies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerCurrencyDTO)))
            .andExpect(status().isCreated());

        // Validate the PlayerCurrency in the database
        List<PlayerCurrency> playerCurrencyList = playerCurrencyRepository.findAll();
        assertThat(playerCurrencyList).hasSize(databaseSizeBeforeCreate + 1);
        PlayerCurrency testPlayerCurrency = playerCurrencyList.get(playerCurrencyList.size() - 1);
        assertThat(testPlayerCurrency.getGold()).isEqualTo(DEFAULT_GOLD);
        assertThat(testPlayerCurrency.getSilver()).isEqualTo(DEFAULT_SILVER);
        assertThat(testPlayerCurrency.getCopper()).isEqualTo(DEFAULT_COPPER);
    }

    @Test
    @Transactional
    public void createPlayerCurrencyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = playerCurrencyRepository.findAll().size();

        // Create the PlayerCurrency with an existing ID
        playerCurrency.setId(1L);
        PlayerCurrencyDTO playerCurrencyDTO = playerCurrencyMapper.toDto(playerCurrency);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlayerCurrencyMockMvc.perform(post("/api/player-currencies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerCurrencyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlayerCurrency in the database
        List<PlayerCurrency> playerCurrencyList = playerCurrencyRepository.findAll();
        assertThat(playerCurrencyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPlayerCurrencies() throws Exception {
        // Initialize the database
        playerCurrencyRepository.saveAndFlush(playerCurrency);

        // Get all the playerCurrencyList
        restPlayerCurrencyMockMvc.perform(get("/api/player-currencies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(playerCurrency.getId().intValue())))
            .andExpect(jsonPath("$.[*].gold").value(hasItem(DEFAULT_GOLD.intValue())))
            .andExpect(jsonPath("$.[*].silver").value(hasItem(DEFAULT_SILVER.intValue())))
            .andExpect(jsonPath("$.[*].copper").value(hasItem(DEFAULT_COPPER.intValue())));
    }
    
    @Test
    @Transactional
    public void getPlayerCurrency() throws Exception {
        // Initialize the database
        playerCurrencyRepository.saveAndFlush(playerCurrency);

        // Get the playerCurrency
        restPlayerCurrencyMockMvc.perform(get("/api/player-currencies/{id}", playerCurrency.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(playerCurrency.getId().intValue()))
            .andExpect(jsonPath("$.gold").value(DEFAULT_GOLD.intValue()))
            .andExpect(jsonPath("$.silver").value(DEFAULT_SILVER.intValue()))
            .andExpect(jsonPath("$.copper").value(DEFAULT_COPPER.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPlayerCurrency() throws Exception {
        // Get the playerCurrency
        restPlayerCurrencyMockMvc.perform(get("/api/player-currencies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlayerCurrency() throws Exception {
        // Initialize the database
        playerCurrencyRepository.saveAndFlush(playerCurrency);

        int databaseSizeBeforeUpdate = playerCurrencyRepository.findAll().size();

        // Update the playerCurrency
        PlayerCurrency updatedPlayerCurrency = playerCurrencyRepository.findById(playerCurrency.getId()).get();
        // Disconnect from session so that the updates on updatedPlayerCurrency are not directly saved in db
        em.detach(updatedPlayerCurrency);
        updatedPlayerCurrency
            .gold(UPDATED_GOLD)
            .silver(UPDATED_SILVER)
            .copper(UPDATED_COPPER);
        PlayerCurrencyDTO playerCurrencyDTO = playerCurrencyMapper.toDto(updatedPlayerCurrency);

        restPlayerCurrencyMockMvc.perform(put("/api/player-currencies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerCurrencyDTO)))
            .andExpect(status().isOk());

        // Validate the PlayerCurrency in the database
        List<PlayerCurrency> playerCurrencyList = playerCurrencyRepository.findAll();
        assertThat(playerCurrencyList).hasSize(databaseSizeBeforeUpdate);
        PlayerCurrency testPlayerCurrency = playerCurrencyList.get(playerCurrencyList.size() - 1);
        assertThat(testPlayerCurrency.getGold()).isEqualTo(UPDATED_GOLD);
        assertThat(testPlayerCurrency.getSilver()).isEqualTo(UPDATED_SILVER);
        assertThat(testPlayerCurrency.getCopper()).isEqualTo(UPDATED_COPPER);
    }

    @Test
    @Transactional
    public void updateNonExistingPlayerCurrency() throws Exception {
        int databaseSizeBeforeUpdate = playerCurrencyRepository.findAll().size();

        // Create the PlayerCurrency
        PlayerCurrencyDTO playerCurrencyDTO = playerCurrencyMapper.toDto(playerCurrency);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlayerCurrencyMockMvc.perform(put("/api/player-currencies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerCurrencyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlayerCurrency in the database
        List<PlayerCurrency> playerCurrencyList = playerCurrencyRepository.findAll();
        assertThat(playerCurrencyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlayerCurrency() throws Exception {
        // Initialize the database
        playerCurrencyRepository.saveAndFlush(playerCurrency);

        int databaseSizeBeforeDelete = playerCurrencyRepository.findAll().size();

        // Delete the playerCurrency
        restPlayerCurrencyMockMvc.perform(delete("/api/player-currencies/{id}", playerCurrency.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PlayerCurrency> playerCurrencyList = playerCurrencyRepository.findAll();
        assertThat(playerCurrencyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
