package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.Armor;
import com.id.kusumakazu.repository.ArmorRepository;
import com.id.kusumakazu.service.ArmorService;
import com.id.kusumakazu.service.dto.ArmorDTO;
import com.id.kusumakazu.service.mapper.ArmorMapper;

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

import com.id.kusumakazu.domain.enumeration.ArmorType;
import com.id.kusumakazu.domain.enumeration.ArmorSize;
import com.id.kusumakazu.domain.enumeration.Rarity;
/**
 * Integration tests for the {@link ArmorResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ArmorResourceIT {

    private static final Double DEFAULT_BASE_DEF = 1D;
    private static final Double UPDATED_BASE_DEF = 2D;

    private static final Double DEFAULT_BASE_HP = 1D;
    private static final Double UPDATED_BASE_HP = 2D;

    private static final ArmorType DEFAULT_ARMOR_TYPE = ArmorType.LEATHER;
    private static final ArmorType UPDATED_ARMOR_TYPE = ArmorType.IRON;

    private static final ArmorSize DEFAULT_ARMORSIZE = ArmorSize.HEAVY;
    private static final ArmorSize UPDATED_ARMORSIZE = ArmorSize.MEDIUM;

    private static final Rarity DEFAULT_RARITY = Rarity.COMMON;
    private static final Rarity UPDATED_RARITY = Rarity.UNCOMMON;

    @Autowired
    private ArmorRepository armorRepository;

    @Autowired
    private ArmorMapper armorMapper;

    @Autowired
    private ArmorService armorService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restArmorMockMvc;

    private Armor armor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Armor createEntity(EntityManager em) {
        Armor armor = new Armor()
            .baseDEF(DEFAULT_BASE_DEF)
            .baseHP(DEFAULT_BASE_HP)
            .armorType(DEFAULT_ARMOR_TYPE)
            .armorsize(DEFAULT_ARMORSIZE)
            .rarity(DEFAULT_RARITY);
        return armor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Armor createUpdatedEntity(EntityManager em) {
        Armor armor = new Armor()
            .baseDEF(UPDATED_BASE_DEF)
            .baseHP(UPDATED_BASE_HP)
            .armorType(UPDATED_ARMOR_TYPE)
            .armorsize(UPDATED_ARMORSIZE)
            .rarity(UPDATED_RARITY);
        return armor;
    }

    @BeforeEach
    public void initTest() {
        armor = createEntity(em);
    }

    @Test
    @Transactional
    public void createArmor() throws Exception {
        int databaseSizeBeforeCreate = armorRepository.findAll().size();
        // Create the Armor
        ArmorDTO armorDTO = armorMapper.toDto(armor);
        restArmorMockMvc.perform(post("/api/armors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(armorDTO)))
            .andExpect(status().isCreated());

        // Validate the Armor in the database
        List<Armor> armorList = armorRepository.findAll();
        assertThat(armorList).hasSize(databaseSizeBeforeCreate + 1);
        Armor testArmor = armorList.get(armorList.size() - 1);
        assertThat(testArmor.getBaseDEF()).isEqualTo(DEFAULT_BASE_DEF);
        assertThat(testArmor.getBaseHP()).isEqualTo(DEFAULT_BASE_HP);
        assertThat(testArmor.getArmorType()).isEqualTo(DEFAULT_ARMOR_TYPE);
        assertThat(testArmor.getArmorsize()).isEqualTo(DEFAULT_ARMORSIZE);
        assertThat(testArmor.getRarity()).isEqualTo(DEFAULT_RARITY);
    }

    @Test
    @Transactional
    public void createArmorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = armorRepository.findAll().size();

        // Create the Armor with an existing ID
        armor.setId(1L);
        ArmorDTO armorDTO = armorMapper.toDto(armor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArmorMockMvc.perform(post("/api/armors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(armorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Armor in the database
        List<Armor> armorList = armorRepository.findAll();
        assertThat(armorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllArmors() throws Exception {
        // Initialize the database
        armorRepository.saveAndFlush(armor);

        // Get all the armorList
        restArmorMockMvc.perform(get("/api/armors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(armor.getId().intValue())))
            .andExpect(jsonPath("$.[*].baseDEF").value(hasItem(DEFAULT_BASE_DEF.doubleValue())))
            .andExpect(jsonPath("$.[*].baseHP").value(hasItem(DEFAULT_BASE_HP.doubleValue())))
            .andExpect(jsonPath("$.[*].armorType").value(hasItem(DEFAULT_ARMOR_TYPE.toString())))
            .andExpect(jsonPath("$.[*].armorsize").value(hasItem(DEFAULT_ARMORSIZE.toString())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY.toString())));
    }
    
    @Test
    @Transactional
    public void getArmor() throws Exception {
        // Initialize the database
        armorRepository.saveAndFlush(armor);

        // Get the armor
        restArmorMockMvc.perform(get("/api/armors/{id}", armor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(armor.getId().intValue()))
            .andExpect(jsonPath("$.baseDEF").value(DEFAULT_BASE_DEF.doubleValue()))
            .andExpect(jsonPath("$.baseHP").value(DEFAULT_BASE_HP.doubleValue()))
            .andExpect(jsonPath("$.armorType").value(DEFAULT_ARMOR_TYPE.toString()))
            .andExpect(jsonPath("$.armorsize").value(DEFAULT_ARMORSIZE.toString()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingArmor() throws Exception {
        // Get the armor
        restArmorMockMvc.perform(get("/api/armors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArmor() throws Exception {
        // Initialize the database
        armorRepository.saveAndFlush(armor);

        int databaseSizeBeforeUpdate = armorRepository.findAll().size();

        // Update the armor
        Armor updatedArmor = armorRepository.findById(armor.getId()).get();
        // Disconnect from session so that the updates on updatedArmor are not directly saved in db
        em.detach(updatedArmor);
        updatedArmor
            .baseDEF(UPDATED_BASE_DEF)
            .baseHP(UPDATED_BASE_HP)
            .armorType(UPDATED_ARMOR_TYPE)
            .armorsize(UPDATED_ARMORSIZE)
            .rarity(UPDATED_RARITY);
        ArmorDTO armorDTO = armorMapper.toDto(updatedArmor);

        restArmorMockMvc.perform(put("/api/armors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(armorDTO)))
            .andExpect(status().isOk());

        // Validate the Armor in the database
        List<Armor> armorList = armorRepository.findAll();
        assertThat(armorList).hasSize(databaseSizeBeforeUpdate);
        Armor testArmor = armorList.get(armorList.size() - 1);
        assertThat(testArmor.getBaseDEF()).isEqualTo(UPDATED_BASE_DEF);
        assertThat(testArmor.getBaseHP()).isEqualTo(UPDATED_BASE_HP);
        assertThat(testArmor.getArmorType()).isEqualTo(UPDATED_ARMOR_TYPE);
        assertThat(testArmor.getArmorsize()).isEqualTo(UPDATED_ARMORSIZE);
        assertThat(testArmor.getRarity()).isEqualTo(UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void updateNonExistingArmor() throws Exception {
        int databaseSizeBeforeUpdate = armorRepository.findAll().size();

        // Create the Armor
        ArmorDTO armorDTO = armorMapper.toDto(armor);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArmorMockMvc.perform(put("/api/armors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(armorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Armor in the database
        List<Armor> armorList = armorRepository.findAll();
        assertThat(armorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArmor() throws Exception {
        // Initialize the database
        armorRepository.saveAndFlush(armor);

        int databaseSizeBeforeDelete = armorRepository.findAll().size();

        // Delete the armor
        restArmorMockMvc.perform(delete("/api/armors/{id}", armor.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Armor> armorList = armorRepository.findAll();
        assertThat(armorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
