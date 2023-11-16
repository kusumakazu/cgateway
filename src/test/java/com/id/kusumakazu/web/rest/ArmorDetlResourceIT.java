package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.ArmorDetl;
import com.id.kusumakazu.repository.ArmorDetlRepository;
import com.id.kusumakazu.service.ArmorDetlService;
import com.id.kusumakazu.service.dto.ArmorDetlDTO;
import com.id.kusumakazu.service.mapper.ArmorDetlMapper;

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
 * Integration tests for the {@link ArmorDetlResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ArmorDetlResourceIT {

    private static final Integer DEFAULT_RANDOM_STAT_BONUS = 1;
    private static final Integer UPDATED_RANDOM_STAT_BONUS = 2;

    @Autowired
    private ArmorDetlRepository armorDetlRepository;

    @Autowired
    private ArmorDetlMapper armorDetlMapper;

    @Autowired
    private ArmorDetlService armorDetlService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restArmorDetlMockMvc;

    private ArmorDetl armorDetl;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArmorDetl createEntity(EntityManager em) {
        ArmorDetl armorDetl = new ArmorDetl()
            .randomStatBonus(DEFAULT_RANDOM_STAT_BONUS);
        return armorDetl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArmorDetl createUpdatedEntity(EntityManager em) {
        ArmorDetl armorDetl = new ArmorDetl()
            .randomStatBonus(UPDATED_RANDOM_STAT_BONUS);
        return armorDetl;
    }

    @BeforeEach
    public void initTest() {
        armorDetl = createEntity(em);
    }

    @Test
    @Transactional
    public void createArmorDetl() throws Exception {
        int databaseSizeBeforeCreate = armorDetlRepository.findAll().size();
        // Create the ArmorDetl
        ArmorDetlDTO armorDetlDTO = armorDetlMapper.toDto(armorDetl);
        restArmorDetlMockMvc.perform(post("/api/armor-detls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(armorDetlDTO)))
            .andExpect(status().isCreated());

        // Validate the ArmorDetl in the database
        List<ArmorDetl> armorDetlList = armorDetlRepository.findAll();
        assertThat(armorDetlList).hasSize(databaseSizeBeforeCreate + 1);
        ArmorDetl testArmorDetl = armorDetlList.get(armorDetlList.size() - 1);
        assertThat(testArmorDetl.getRandomStatBonus()).isEqualTo(DEFAULT_RANDOM_STAT_BONUS);
    }

    @Test
    @Transactional
    public void createArmorDetlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = armorDetlRepository.findAll().size();

        // Create the ArmorDetl with an existing ID
        armorDetl.setId(1L);
        ArmorDetlDTO armorDetlDTO = armorDetlMapper.toDto(armorDetl);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArmorDetlMockMvc.perform(post("/api/armor-detls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(armorDetlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ArmorDetl in the database
        List<ArmorDetl> armorDetlList = armorDetlRepository.findAll();
        assertThat(armorDetlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllArmorDetls() throws Exception {
        // Initialize the database
        armorDetlRepository.saveAndFlush(armorDetl);

        // Get all the armorDetlList
        restArmorDetlMockMvc.perform(get("/api/armor-detls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(armorDetl.getId().intValue())))
            .andExpect(jsonPath("$.[*].randomStatBonus").value(hasItem(DEFAULT_RANDOM_STAT_BONUS)));
    }
    
    @Test
    @Transactional
    public void getArmorDetl() throws Exception {
        // Initialize the database
        armorDetlRepository.saveAndFlush(armorDetl);

        // Get the armorDetl
        restArmorDetlMockMvc.perform(get("/api/armor-detls/{id}", armorDetl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(armorDetl.getId().intValue()))
            .andExpect(jsonPath("$.randomStatBonus").value(DEFAULT_RANDOM_STAT_BONUS));
    }
    @Test
    @Transactional
    public void getNonExistingArmorDetl() throws Exception {
        // Get the armorDetl
        restArmorDetlMockMvc.perform(get("/api/armor-detls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArmorDetl() throws Exception {
        // Initialize the database
        armorDetlRepository.saveAndFlush(armorDetl);

        int databaseSizeBeforeUpdate = armorDetlRepository.findAll().size();

        // Update the armorDetl
        ArmorDetl updatedArmorDetl = armorDetlRepository.findById(armorDetl.getId()).get();
        // Disconnect from session so that the updates on updatedArmorDetl are not directly saved in db
        em.detach(updatedArmorDetl);
        updatedArmorDetl
            .randomStatBonus(UPDATED_RANDOM_STAT_BONUS);
        ArmorDetlDTO armorDetlDTO = armorDetlMapper.toDto(updatedArmorDetl);

        restArmorDetlMockMvc.perform(put("/api/armor-detls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(armorDetlDTO)))
            .andExpect(status().isOk());

        // Validate the ArmorDetl in the database
        List<ArmorDetl> armorDetlList = armorDetlRepository.findAll();
        assertThat(armorDetlList).hasSize(databaseSizeBeforeUpdate);
        ArmorDetl testArmorDetl = armorDetlList.get(armorDetlList.size() - 1);
        assertThat(testArmorDetl.getRandomStatBonus()).isEqualTo(UPDATED_RANDOM_STAT_BONUS);
    }

    @Test
    @Transactional
    public void updateNonExistingArmorDetl() throws Exception {
        int databaseSizeBeforeUpdate = armorDetlRepository.findAll().size();

        // Create the ArmorDetl
        ArmorDetlDTO armorDetlDTO = armorDetlMapper.toDto(armorDetl);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArmorDetlMockMvc.perform(put("/api/armor-detls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(armorDetlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ArmorDetl in the database
        List<ArmorDetl> armorDetlList = armorDetlRepository.findAll();
        assertThat(armorDetlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArmorDetl() throws Exception {
        // Initialize the database
        armorDetlRepository.saveAndFlush(armorDetl);

        int databaseSizeBeforeDelete = armorDetlRepository.findAll().size();

        // Delete the armorDetl
        restArmorDetlMockMvc.perform(delete("/api/armor-detls/{id}", armorDetl.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ArmorDetl> armorDetlList = armorDetlRepository.findAll();
        assertThat(armorDetlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
