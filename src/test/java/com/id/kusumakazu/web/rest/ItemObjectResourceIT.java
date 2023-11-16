package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.ItemObject;
import com.id.kusumakazu.repository.ItemObjectRepository;
import com.id.kusumakazu.service.ItemObjectService;
import com.id.kusumakazu.service.dto.ItemObjectDTO;
import com.id.kusumakazu.service.mapper.ItemObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.id.kusumakazu.domain.enumeration.ObtainFrom;
/**
 * Integration tests for the {@link ItemObjectResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ItemObjectResourceIT {

    private static final String DEFAULT_ITEM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_DESCRIPTION = "BBBBBBBBBB";

    private static final ObtainFrom DEFAULT_OBTAINEDFROM = ObtainFrom.CASHSHOP;
    private static final ObtainFrom UPDATED_OBTAINEDFROM = ObtainFrom.CRAFTING;

    private static final Boolean DEFAULT_IS_ENCHANT = false;
    private static final Boolean UPDATED_IS_ENCHANT = true;

    @Autowired
    private ItemObjectRepository itemObjectRepository;

    @Mock
    private ItemObjectRepository itemObjectRepositoryMock;

    @Autowired
    private ItemObjectMapper itemObjectMapper;

    @Mock
    private ItemObjectService itemObjectServiceMock;

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
            .itemName(DEFAULT_ITEM_NAME)
            .itemDescription(DEFAULT_ITEM_DESCRIPTION)
            .obtainedfrom(DEFAULT_OBTAINEDFROM)
            .isEnchant(DEFAULT_IS_ENCHANT);
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
            .itemName(UPDATED_ITEM_NAME)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .obtainedfrom(UPDATED_OBTAINEDFROM)
            .isEnchant(UPDATED_IS_ENCHANT);
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
        assertThat(testItemObject.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItemObject.getItemDescription()).isEqualTo(DEFAULT_ITEM_DESCRIPTION);
        assertThat(testItemObject.getObtainedfrom()).isEqualTo(DEFAULT_OBTAINEDFROM);
        assertThat(testItemObject.isIsEnchant()).isEqualTo(DEFAULT_IS_ENCHANT);
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
            .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME)))
            .andExpect(jsonPath("$.[*].itemDescription").value(hasItem(DEFAULT_ITEM_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].obtainedfrom").value(hasItem(DEFAULT_OBTAINEDFROM.toString())))
            .andExpect(jsonPath("$.[*].isEnchant").value(hasItem(DEFAULT_IS_ENCHANT.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllItemObjectsWithEagerRelationshipsIsEnabled() throws Exception {
        when(itemObjectServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restItemObjectMockMvc.perform(get("/api/item-objects?eagerload=true"))
            .andExpect(status().isOk());

        verify(itemObjectServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllItemObjectsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(itemObjectServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restItemObjectMockMvc.perform(get("/api/item-objects?eagerload=true"))
            .andExpect(status().isOk());

        verify(itemObjectServiceMock, times(1)).findAllWithEagerRelationships(any());
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
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME))
            .andExpect(jsonPath("$.itemDescription").value(DEFAULT_ITEM_DESCRIPTION))
            .andExpect(jsonPath("$.obtainedfrom").value(DEFAULT_OBTAINEDFROM.toString()))
            .andExpect(jsonPath("$.isEnchant").value(DEFAULT_IS_ENCHANT.booleanValue()));
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
            .itemName(UPDATED_ITEM_NAME)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .obtainedfrom(UPDATED_OBTAINEDFROM)
            .isEnchant(UPDATED_IS_ENCHANT);
        ItemObjectDTO itemObjectDTO = itemObjectMapper.toDto(updatedItemObject);

        restItemObjectMockMvc.perform(put("/api/item-objects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemObjectDTO)))
            .andExpect(status().isOk());

        // Validate the ItemObject in the database
        List<ItemObject> itemObjectList = itemObjectRepository.findAll();
        assertThat(itemObjectList).hasSize(databaseSizeBeforeUpdate);
        ItemObject testItemObject = itemObjectList.get(itemObjectList.size() - 1);
        assertThat(testItemObject.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemObject.getItemDescription()).isEqualTo(UPDATED_ITEM_DESCRIPTION);
        assertThat(testItemObject.getObtainedfrom()).isEqualTo(UPDATED_OBTAINEDFROM);
        assertThat(testItemObject.isIsEnchant()).isEqualTo(UPDATED_IS_ENCHANT);
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
