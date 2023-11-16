package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.PlayerInventory;
import com.id.kusumakazu.repository.PlayerInventoryRepository;
import com.id.kusumakazu.service.PlayerInventoryService;
import com.id.kusumakazu.service.dto.PlayerInventoryDTO;
import com.id.kusumakazu.service.mapper.PlayerInventoryMapper;

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
 * Integration tests for the {@link PlayerInventoryResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PlayerInventoryResourceIT {

    private static final Integer DEFAULT_SLOT = 1;
    private static final Integer UPDATED_SLOT = 2;

    private static final Integer DEFAULT_AMOUNT = 1;
    private static final Integer UPDATED_AMOUNT = 2;

    @Autowired
    private PlayerInventoryRepository playerInventoryRepository;

    @Autowired
    private PlayerInventoryMapper playerInventoryMapper;

    @Autowired
    private PlayerInventoryService playerInventoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlayerInventoryMockMvc;

    private PlayerInventory playerInventory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlayerInventory createEntity(EntityManager em) {
        PlayerInventory playerInventory = new PlayerInventory()
            .slot(DEFAULT_SLOT)
            .amount(DEFAULT_AMOUNT);
        return playerInventory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlayerInventory createUpdatedEntity(EntityManager em) {
        PlayerInventory playerInventory = new PlayerInventory()
            .slot(UPDATED_SLOT)
            .amount(UPDATED_AMOUNT);
        return playerInventory;
    }

    @BeforeEach
    public void initTest() {
        playerInventory = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlayerInventory() throws Exception {
        int databaseSizeBeforeCreate = playerInventoryRepository.findAll().size();
        // Create the PlayerInventory
        PlayerInventoryDTO playerInventoryDTO = playerInventoryMapper.toDto(playerInventory);
        restPlayerInventoryMockMvc.perform(post("/api/player-inventories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerInventoryDTO)))
            .andExpect(status().isCreated());

        // Validate the PlayerInventory in the database
        List<PlayerInventory> playerInventoryList = playerInventoryRepository.findAll();
        assertThat(playerInventoryList).hasSize(databaseSizeBeforeCreate + 1);
        PlayerInventory testPlayerInventory = playerInventoryList.get(playerInventoryList.size() - 1);
        assertThat(testPlayerInventory.getSlot()).isEqualTo(DEFAULT_SLOT);
        assertThat(testPlayerInventory.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createPlayerInventoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = playerInventoryRepository.findAll().size();

        // Create the PlayerInventory with an existing ID
        playerInventory.setId(1L);
        PlayerInventoryDTO playerInventoryDTO = playerInventoryMapper.toDto(playerInventory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlayerInventoryMockMvc.perform(post("/api/player-inventories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerInventoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlayerInventory in the database
        List<PlayerInventory> playerInventoryList = playerInventoryRepository.findAll();
        assertThat(playerInventoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPlayerInventories() throws Exception {
        // Initialize the database
        playerInventoryRepository.saveAndFlush(playerInventory);

        // Get all the playerInventoryList
        restPlayerInventoryMockMvc.perform(get("/api/player-inventories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(playerInventory.getId().intValue())))
            .andExpect(jsonPath("$.[*].slot").value(hasItem(DEFAULT_SLOT)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)));
    }
    
    @Test
    @Transactional
    public void getPlayerInventory() throws Exception {
        // Initialize the database
        playerInventoryRepository.saveAndFlush(playerInventory);

        // Get the playerInventory
        restPlayerInventoryMockMvc.perform(get("/api/player-inventories/{id}", playerInventory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(playerInventory.getId().intValue()))
            .andExpect(jsonPath("$.slot").value(DEFAULT_SLOT))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT));
    }
    @Test
    @Transactional
    public void getNonExistingPlayerInventory() throws Exception {
        // Get the playerInventory
        restPlayerInventoryMockMvc.perform(get("/api/player-inventories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlayerInventory() throws Exception {
        // Initialize the database
        playerInventoryRepository.saveAndFlush(playerInventory);

        int databaseSizeBeforeUpdate = playerInventoryRepository.findAll().size();

        // Update the playerInventory
        PlayerInventory updatedPlayerInventory = playerInventoryRepository.findById(playerInventory.getId()).get();
        // Disconnect from session so that the updates on updatedPlayerInventory are not directly saved in db
        em.detach(updatedPlayerInventory);
        updatedPlayerInventory
            .slot(UPDATED_SLOT)
            .amount(UPDATED_AMOUNT);
        PlayerInventoryDTO playerInventoryDTO = playerInventoryMapper.toDto(updatedPlayerInventory);

        restPlayerInventoryMockMvc.perform(put("/api/player-inventories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerInventoryDTO)))
            .andExpect(status().isOk());

        // Validate the PlayerInventory in the database
        List<PlayerInventory> playerInventoryList = playerInventoryRepository.findAll();
        assertThat(playerInventoryList).hasSize(databaseSizeBeforeUpdate);
        PlayerInventory testPlayerInventory = playerInventoryList.get(playerInventoryList.size() - 1);
        assertThat(testPlayerInventory.getSlot()).isEqualTo(UPDATED_SLOT);
        assertThat(testPlayerInventory.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingPlayerInventory() throws Exception {
        int databaseSizeBeforeUpdate = playerInventoryRepository.findAll().size();

        // Create the PlayerInventory
        PlayerInventoryDTO playerInventoryDTO = playerInventoryMapper.toDto(playerInventory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlayerInventoryMockMvc.perform(put("/api/player-inventories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerInventoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlayerInventory in the database
        List<PlayerInventory> playerInventoryList = playerInventoryRepository.findAll();
        assertThat(playerInventoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlayerInventory() throws Exception {
        // Initialize the database
        playerInventoryRepository.saveAndFlush(playerInventory);

        int databaseSizeBeforeDelete = playerInventoryRepository.findAll().size();

        // Delete the playerInventory
        restPlayerInventoryMockMvc.perform(delete("/api/player-inventories/{id}", playerInventory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PlayerInventory> playerInventoryList = playerInventoryRepository.findAll();
        assertThat(playerInventoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
