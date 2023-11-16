package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.WeaponDetl;
import com.id.kusumakazu.repository.WeaponDetlRepository;
import com.id.kusumakazu.service.WeaponDetlService;
import com.id.kusumakazu.service.dto.WeaponDetlDTO;
import com.id.kusumakazu.service.mapper.WeaponDetlMapper;

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
 * Integration tests for the {@link WeaponDetlResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WeaponDetlResourceIT {

    private static final Integer DEFAULT_RANDOM_STAT_BONUS = 1;
    private static final Integer UPDATED_RANDOM_STAT_BONUS = 2;

    @Autowired
    private WeaponDetlRepository weaponDetlRepository;

    @Autowired
    private WeaponDetlMapper weaponDetlMapper;

    @Autowired
    private WeaponDetlService weaponDetlService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWeaponDetlMockMvc;

    private WeaponDetl weaponDetl;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WeaponDetl createEntity(EntityManager em) {
        WeaponDetl weaponDetl = new WeaponDetl()
            .randomStatBonus(DEFAULT_RANDOM_STAT_BONUS);
        return weaponDetl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WeaponDetl createUpdatedEntity(EntityManager em) {
        WeaponDetl weaponDetl = new WeaponDetl()
            .randomStatBonus(UPDATED_RANDOM_STAT_BONUS);
        return weaponDetl;
    }

    @BeforeEach
    public void initTest() {
        weaponDetl = createEntity(em);
    }

    @Test
    @Transactional
    public void createWeaponDetl() throws Exception {
        int databaseSizeBeforeCreate = weaponDetlRepository.findAll().size();
        // Create the WeaponDetl
        WeaponDetlDTO weaponDetlDTO = weaponDetlMapper.toDto(weaponDetl);
        restWeaponDetlMockMvc.perform(post("/api/weapon-detls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weaponDetlDTO)))
            .andExpect(status().isCreated());

        // Validate the WeaponDetl in the database
        List<WeaponDetl> weaponDetlList = weaponDetlRepository.findAll();
        assertThat(weaponDetlList).hasSize(databaseSizeBeforeCreate + 1);
        WeaponDetl testWeaponDetl = weaponDetlList.get(weaponDetlList.size() - 1);
        assertThat(testWeaponDetl.getRandomStatBonus()).isEqualTo(DEFAULT_RANDOM_STAT_BONUS);
    }

    @Test
    @Transactional
    public void createWeaponDetlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = weaponDetlRepository.findAll().size();

        // Create the WeaponDetl with an existing ID
        weaponDetl.setId(1L);
        WeaponDetlDTO weaponDetlDTO = weaponDetlMapper.toDto(weaponDetl);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWeaponDetlMockMvc.perform(post("/api/weapon-detls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weaponDetlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WeaponDetl in the database
        List<WeaponDetl> weaponDetlList = weaponDetlRepository.findAll();
        assertThat(weaponDetlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWeaponDetls() throws Exception {
        // Initialize the database
        weaponDetlRepository.saveAndFlush(weaponDetl);

        // Get all the weaponDetlList
        restWeaponDetlMockMvc.perform(get("/api/weapon-detls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(weaponDetl.getId().intValue())))
            .andExpect(jsonPath("$.[*].randomStatBonus").value(hasItem(DEFAULT_RANDOM_STAT_BONUS)));
    }
    
    @Test
    @Transactional
    public void getWeaponDetl() throws Exception {
        // Initialize the database
        weaponDetlRepository.saveAndFlush(weaponDetl);

        // Get the weaponDetl
        restWeaponDetlMockMvc.perform(get("/api/weapon-detls/{id}", weaponDetl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(weaponDetl.getId().intValue()))
            .andExpect(jsonPath("$.randomStatBonus").value(DEFAULT_RANDOM_STAT_BONUS));
    }
    @Test
    @Transactional
    public void getNonExistingWeaponDetl() throws Exception {
        // Get the weaponDetl
        restWeaponDetlMockMvc.perform(get("/api/weapon-detls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWeaponDetl() throws Exception {
        // Initialize the database
        weaponDetlRepository.saveAndFlush(weaponDetl);

        int databaseSizeBeforeUpdate = weaponDetlRepository.findAll().size();

        // Update the weaponDetl
        WeaponDetl updatedWeaponDetl = weaponDetlRepository.findById(weaponDetl.getId()).get();
        // Disconnect from session so that the updates on updatedWeaponDetl are not directly saved in db
        em.detach(updatedWeaponDetl);
        updatedWeaponDetl
            .randomStatBonus(UPDATED_RANDOM_STAT_BONUS);
        WeaponDetlDTO weaponDetlDTO = weaponDetlMapper.toDto(updatedWeaponDetl);

        restWeaponDetlMockMvc.perform(put("/api/weapon-detls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weaponDetlDTO)))
            .andExpect(status().isOk());

        // Validate the WeaponDetl in the database
        List<WeaponDetl> weaponDetlList = weaponDetlRepository.findAll();
        assertThat(weaponDetlList).hasSize(databaseSizeBeforeUpdate);
        WeaponDetl testWeaponDetl = weaponDetlList.get(weaponDetlList.size() - 1);
        assertThat(testWeaponDetl.getRandomStatBonus()).isEqualTo(UPDATED_RANDOM_STAT_BONUS);
    }

    @Test
    @Transactional
    public void updateNonExistingWeaponDetl() throws Exception {
        int databaseSizeBeforeUpdate = weaponDetlRepository.findAll().size();

        // Create the WeaponDetl
        WeaponDetlDTO weaponDetlDTO = weaponDetlMapper.toDto(weaponDetl);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWeaponDetlMockMvc.perform(put("/api/weapon-detls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weaponDetlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WeaponDetl in the database
        List<WeaponDetl> weaponDetlList = weaponDetlRepository.findAll();
        assertThat(weaponDetlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWeaponDetl() throws Exception {
        // Initialize the database
        weaponDetlRepository.saveAndFlush(weaponDetl);

        int databaseSizeBeforeDelete = weaponDetlRepository.findAll().size();

        // Delete the weaponDetl
        restWeaponDetlMockMvc.perform(delete("/api/weapon-detls/{id}", weaponDetl.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WeaponDetl> weaponDetlList = weaponDetlRepository.findAll();
        assertThat(weaponDetlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
