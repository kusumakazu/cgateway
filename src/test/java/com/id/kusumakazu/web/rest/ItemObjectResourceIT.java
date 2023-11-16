package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.ItemObject;
import com.id.kusumakazu.repository.ItemObjectRepository;
import com.id.kusumakazu.service.ItemObjectService;
import com.id.kusumakazu.service.dto.ItemObjectDTO;
import com.id.kusumakazu.service.mapper.ItemObjectMapper;

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

import com.id.kusumakazu.domain.enumeration.ObtainFrom;
import com.id.kusumakazu.domain.enumeration.ItemObjectType;
/**
 * Integration tests for the {@link ItemObjectResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ItemObjectResourceIT {

    private static final ObtainFrom DEFAULT_OBTAINEDFROM = ObtainFrom.CASHSHOP;
    private static final ObtainFrom UPDATED_OBTAINEDFROM = ObtainFrom.CRAFTING;

    private static final Boolean DEFAULT_IS_ENCHANT = false;
    private static final Boolean UPDATED_IS_ENCHANT = true;

    private static final ItemObjectType DEFAULT_ITEM_OBJECT_TYPE = ItemObjectType.WEAPON;
    private static final ItemObjectType UPDATED_ITEM_OBJECT_TYPE = ItemObjectType.ARMOR;

    @Autowired
    private ItemObjectRepository itemObjectRepository;

    @Autowired
    private ItemObjectMapper itemObjectMapper;

    @Autowired
    private ItemObjectService itemObjectService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemObjectMockMvc;

    private ItemObject itemObject;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemObject createEntity(EntityManager em) {
        ItemObject itemObject = new ItemObject()
            .obtainedfrom(DEFAULT_OBTAINEDFROM)
            .isEnchant(DEFAULT_IS_ENCHANT)
            .itemObjectType(DEFAULT_ITEM_OBJECT_TYPE);
        return itemObject;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemObject createUpdatedEntity(EntityManager em) {
        ItemObject itemObject = new ItemObject()
            .obtainedfrom(UPDATED_OBTAINEDFROM)
            .isEnchant(UPDATED_IS_ENCHANT)
            .itemObjectType(UPDATED_ITEM_OBJECT_TYPE);
        return itemObject;
    }

    @BeforeEach
    public void initTest() {
        itemObject = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemObject() throws Exception {
        int databaseSizeBeforeCreate = itemObjectRepository.findAll().size();
        // Create the ItemObject
        ItemObjectDTO itemObjectDTO = itemObjectMapper.toDto(itemObject);
        restItemObjectMockMvc.perform(post("/api/item-objects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemObjectDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemObject in the database
        List<ItemObject> itemObjectList = itemObjectRepository.findAll();
        assertThat(itemObjectList).hasSize(databaseSizeBeforeCreate + 1);
        ItemObject testItemObject = itemObjectList.get(itemObjectList.size() - 1);
        assertThat(testItemObject.getObtainedfrom()).isEqualTo(DEFAULT_OBTAINEDFROM);
        assertThat(testItemObject.isIsEnchant()).isEqualTo(DEFAULT_IS_ENCHANT);
        assertThat(testItemObject.getItemObjectType()).isEqualTo(DEFAULT_ITEM_OBJECT_TYPE);
    }

    @Test
    @Transactional
    public void createItemObjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemObjectRepository.findAll().size();

        // Create the ItemObject with an existing ID
        itemObject.setId(1L);
        ItemObjectDTO itemObjectDTO = itemObjectMapper.toDto(itemObject);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemObjectMockMvc.perform(post("/api/item-objects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemObjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemObject in the database
        List<ItemObject> itemObjectList = itemObjectRepository.findAll();
        assertThat(itemObjectList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllItemObjects() throws Exception {
        // Initialize the database
        itemObjectRepository.saveAndFlush(itemObject);

        // Get all the itemObjectList
        restItemObjectMockMvc.perform(get("/api/item-objects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemObject.getId().intValue())))
            .andExpect(jsonPath("$.[*].obtainedfrom").value(hasItem(DEFAULT_OBTAINEDFROM.toString())))
            .andExpect(jsonPath("$.[*].isEnchant").value(hasItem(DEFAULT_IS_ENCHANT.booleanValue())))
            .andExpect(jsonPath("$.[*].itemObjectType").value(hasItem(DEFAULT_ITEM_OBJECT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getItemObject() throws Exception {
        // Initialize the database
        itemObjectRepository.saveAndFlush(itemObject);

        // Get the itemObject
        restItemObjectMockMvc.perform(get("/api/item-objects/{id}", itemObject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemObject.getId().intValue()))
            .andExpect(jsonPath("$.obtainedfrom").value(DEFAULT_OBTAINEDFROM.toString()))
            .andExpect(jsonPath("$.isEnchant").value(DEFAULT_IS_ENCHANT.booleanValue()))
            .andExpect(jsonPath("$.itemObjectType").value(DEFAULT_ITEM_OBJECT_TYPE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingItemObject() throws Exception {
        // Get the itemObject
        restItemObjectMockMvc.perform(get("/api/item-objects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemObject() throws Exception {
        // Initialize the database
        itemObjectRepository.saveAndFlush(itemObject);

        int databaseSizeBeforeUpdate = itemObjectRepository.findAll().size();

        // Update the itemObject
        ItemObject updatedItemObject = itemObjectRepository.findById(itemObject.getId()).get();
        // Disconnect from session so that the updates on updatedItemObject are not directly saved in db
        em.detach(updatedItemObject);
        updatedItemObject
            .obtainedfrom(UPDATED_OBTAINEDFROM)
            .isEnchant(UPDATED_IS_ENCHANT)
            .itemObjectType(UPDATED_ITEM_OBJECT_TYPE);
        ItemObjectDTO itemObjectDTO = itemObjectMapper.toDto(updatedItemObject);

        restItemObjectMockMvc.perform(put("/api/item-objects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemObjectDTO)))
            .andExpect(status().isOk());

        // Validate the ItemObject in the database
        List<ItemObject> itemObjectList = itemObjectRepository.findAll();
        assertThat(itemObjectList).hasSize(databaseSizeBeforeUpdate);
        ItemObject testItemObject = itemObjectList.get(itemObjectList.size() - 1);
        assertThat(testItemObject.getObtainedfrom()).isEqualTo(UPDATED_OBTAINEDFROM);
        assertThat(testItemObject.isIsEnchant()).isEqualTo(UPDATED_IS_ENCHANT);
        assertThat(testItemObject.getItemObjectType()).isEqualTo(UPDATED_ITEM_OBJECT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingItemObject() throws Exception {
        int databaseSizeBeforeUpdate = itemObjectRepository.findAll().size();

        // Create the ItemObject
        ItemObjectDTO itemObjectDTO = itemObjectMapper.toDto(itemObject);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemObjectMockMvc.perform(put("/api/item-objects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemObjectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemObject in the database
        List<ItemObject> itemObjectList = itemObjectRepository.findAll();
        assertThat(itemObjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemObject() throws Exception {
        // Initialize the database
        itemObjectRepository.saveAndFlush(itemObject);

        int databaseSizeBeforeDelete = itemObjectRepository.findAll().size();

        // Delete the itemObject
        restItemObjectMockMvc.perform(delete("/api/item-objects/{id}", itemObject.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemObject> itemObjectList = itemObjectRepository.findAll();
        assertThat(itemObjectList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
