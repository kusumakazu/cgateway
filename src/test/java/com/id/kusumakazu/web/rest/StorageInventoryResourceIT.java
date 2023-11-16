package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.StorageInventory;
import com.id.kusumakazu.repository.StorageInventoryRepository;
import com.id.kusumakazu.service.StorageInventoryService;
import com.id.kusumakazu.service.dto.StorageInventoryDTO;
import com.id.kusumakazu.service.mapper.StorageInventoryMapper;

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
 * Integration tests for the {@link StorageInventoryResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StorageInventoryResourceIT {

    private static final String DEFAULT_STORAGE_INVENTORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STORAGE_INVENTORY_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_SLOT = 1;
    private static final Integer UPDATED_SLOT = 2;

    private static final Integer DEFAULT_AMOUNT = 1;
    private static final Integer UPDATED_AMOUNT = 2;

    private static final Integer DEFAULT_LOCATION_COOR_A = 1;
    private static final Integer UPDATED_LOCATION_COOR_A = 2;

    private static final Integer DEFAULT_LOCATION_COOR_B = 1;
    private static final Integer UPDATED_LOCATION_COOR_B = 2;

    @Autowired
    private StorageInventoryRepository storageInventoryRepository;

    @Autowired
    private StorageInventoryMapper storageInventoryMapper;

    @Autowired
    private StorageInventoryService storageInventoryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStorageInventoryMockMvc;

    private StorageInventory storageInventory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StorageInventory createEntity(EntityManager em) {
        StorageInventory storageInventory = new StorageInventory()
            .storageInventoryName(DEFAULT_STORAGE_INVENTORY_NAME)
            .slot(DEFAULT_SLOT)
            .amount(DEFAULT_AMOUNT)
            .locationCoorA(DEFAULT_LOCATION_COOR_A)
            .locationCoorB(DEFAULT_LOCATION_COOR_B);
        return storageInventory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StorageInventory createUpdatedEntity(EntityManager em) {
        StorageInventory storageInventory = new StorageInventory()
            .storageInventoryName(UPDATED_STORAGE_INVENTORY_NAME)
            .slot(UPDATED_SLOT)
            .amount(UPDATED_AMOUNT)
            .locationCoorA(UPDATED_LOCATION_COOR_A)
            .locationCoorB(UPDATED_LOCATION_COOR_B);
        return storageInventory;
    }

    @BeforeEach
    public void initTest() {
        storageInventory = createEntity(em);
    }

    @Test
    @Transactional
    public void createStorageInventory() throws Exception {
        int databaseSizeBeforeCreate = storageInventoryRepository.findAll().size();
        // Create the StorageInventory
        StorageInventoryDTO storageInventoryDTO = storageInventoryMapper.toDto(storageInventory);
        restStorageInventoryMockMvc.perform(post("/api/storage-inventories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(storageInventoryDTO)))
            .andExpect(status().isCreated());

        // Validate the StorageInventory in the database
        List<StorageInventory> storageInventoryList = storageInventoryRepository.findAll();
        assertThat(storageInventoryList).hasSize(databaseSizeBeforeCreate + 1);
        StorageInventory testStorageInventory = storageInventoryList.get(storageInventoryList.size() - 1);
        assertThat(testStorageInventory.getStorageInventoryName()).isEqualTo(DEFAULT_STORAGE_INVENTORY_NAME);
        assertThat(testStorageInventory.getSlot()).isEqualTo(DEFAULT_SLOT);
        assertThat(testStorageInventory.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testStorageInventory.getLocationCoorA()).isEqualTo(DEFAULT_LOCATION_COOR_A);
        assertThat(testStorageInventory.getLocationCoorB()).isEqualTo(DEFAULT_LOCATION_COOR_B);
    }

    @Test
    @Transactional
    public void createStorageInventoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = storageInventoryRepository.findAll().size();

        // Create the StorageInventory with an existing ID
        storageInventory.setId(1L);
        StorageInventoryDTO storageInventoryDTO = storageInventoryMapper.toDto(storageInventory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStorageInventoryMockMvc.perform(post("/api/storage-inventories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(storageInventoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StorageInventory in the database
        List<StorageInventory> storageInventoryList = storageInventoryRepository.findAll();
        assertThat(storageInventoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStorageInventories() throws Exception {
        // Initialize the database
        storageInventoryRepository.saveAndFlush(storageInventory);

        // Get all the storageInventoryList
        restStorageInventoryMockMvc.perform(get("/api/storage-inventories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(storageInventory.getId().intValue())))
            .andExpect(jsonPath("$.[*].storageInventoryName").value(hasItem(DEFAULT_STORAGE_INVENTORY_NAME)))
            .andExpect(jsonPath("$.[*].slot").value(hasItem(DEFAULT_SLOT)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.[*].locationCoorA").value(hasItem(DEFAULT_LOCATION_COOR_A)))
            .andExpect(jsonPath("$.[*].locationCoorB").value(hasItem(DEFAULT_LOCATION_COOR_B)));
    }
    
    @Test
    @Transactional
    public void getStorageInventory() throws Exception {
        // Initialize the database
        storageInventoryRepository.saveAndFlush(storageInventory);

        // Get the storageInventory
        restStorageInventoryMockMvc.perform(get("/api/storage-inventories/{id}", storageInventory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(storageInventory.getId().intValue()))
            .andExpect(jsonPath("$.storageInventoryName").value(DEFAULT_STORAGE_INVENTORY_NAME))
            .andExpect(jsonPath("$.slot").value(DEFAULT_SLOT))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT))
            .andExpect(jsonPath("$.locationCoorA").value(DEFAULT_LOCATION_COOR_A))
            .andExpect(jsonPath("$.locationCoorB").value(DEFAULT_LOCATION_COOR_B));
    }
    @Test
    @Transactional
    public void getNonExistingStorageInventory() throws Exception {
        // Get the storageInventory
        restStorageInventoryMockMvc.perform(get("/api/storage-inventories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStorageInventory() throws Exception {
        // Initialize the database
        storageInventoryRepository.saveAndFlush(storageInventory);

        int databaseSizeBeforeUpdate = storageInventoryRepository.findAll().size();

        // Update the storageInventory
        StorageInventory updatedStorageInventory = storageInventoryRepository.findById(storageInventory.getId()).get();
        // Disconnect from session so that the updates on updatedStorageInventory are not directly saved in db
        em.detach(updatedStorageInventory);
        updatedStorageInventory
            .storageInventoryName(UPDATED_STORAGE_INVENTORY_NAME)
            .slot(UPDATED_SLOT)
            .amount(UPDATED_AMOUNT)
            .locationCoorA(UPDATED_LOCATION_COOR_A)
            .locationCoorB(UPDATED_LOCATION_COOR_B);
        StorageInventoryDTO storageInventoryDTO = storageInventoryMapper.toDto(updatedStorageInventory);

        restStorageInventoryMockMvc.perform(put("/api/storage-inventories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(storageInventoryDTO)))
            .andExpect(status().isOk());

        // Validate the StorageInventory in the database
        List<StorageInventory> storageInventoryList = storageInventoryRepository.findAll();
        assertThat(storageInventoryList).hasSize(databaseSizeBeforeUpdate);
        StorageInventory testStorageInventory = storageInventoryList.get(storageInventoryList.size() - 1);
        assertThat(testStorageInventory.getStorageInventoryName()).isEqualTo(UPDATED_STORAGE_INVENTORY_NAME);
        assertThat(testStorageInventory.getSlot()).isEqualTo(UPDATED_SLOT);
        assertThat(testStorageInventory.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testStorageInventory.getLocationCoorA()).isEqualTo(UPDATED_LOCATION_COOR_A);
        assertThat(testStorageInventory.getLocationCoorB()).isEqualTo(UPDATED_LOCATION_COOR_B);
    }

    @Test
    @Transactional
    public void updateNonExistingStorageInventory() throws Exception {
        int databaseSizeBeforeUpdate = storageInventoryRepository.findAll().size();

        // Create the StorageInventory
        StorageInventoryDTO storageInventoryDTO = storageInventoryMapper.toDto(storageInventory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStorageInventoryMockMvc.perform(put("/api/storage-inventories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(storageInventoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StorageInventory in the database
        List<StorageInventory> storageInventoryList = storageInventoryRepository.findAll();
        assertThat(storageInventoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStorageInventory() throws Exception {
        // Initialize the database
        storageInventoryRepository.saveAndFlush(storageInventory);

        int databaseSizeBeforeDelete = storageInventoryRepository.findAll().size();

        // Delete the storageInventory
        restStorageInventoryMockMvc.perform(delete("/api/storage-inventories/{id}", storageInventory.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StorageInventory> storageInventoryList = storageInventoryRepository.findAll();
        assertThat(storageInventoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
