package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.Weapon;
import com.id.kusumakazu.repository.WeaponRepository;
import com.id.kusumakazu.service.WeaponService;
import com.id.kusumakazu.service.dto.WeaponDTO;
import com.id.kusumakazu.service.mapper.WeaponMapper;

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

import com.id.kusumakazu.domain.enumeration.WeaponType;
import com.id.kusumakazu.domain.enumeration.WeaponSize;
import com.id.kusumakazu.domain.enumeration.Rarity;
/**
 * Integration tests for the {@link WeaponResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WeaponResourceIT {

    private static final Double DEFAULT_BASE_ATK = 1D;
    private static final Double UPDATED_BASE_ATK = 2D;

    private static final WeaponType DEFAULT_WEAPON_TYPE = WeaponType.STAFF;
    private static final WeaponType UPDATED_WEAPON_TYPE = WeaponType.WAND;

    private static final WeaponSize DEFAULT_WEAPON_SIZE = WeaponSize.HEAVY;
    private static final WeaponSize UPDATED_WEAPON_SIZE = WeaponSize.MEDIUM;

    private static final Rarity DEFAULT_RARITY = Rarity.COMMON;
    private static final Rarity UPDATED_RARITY = Rarity.UNCOMMON;

    @Autowired
    private WeaponRepository weaponRepository;

    @Autowired
    private WeaponMapper weaponMapper;

    @Autowired
    private WeaponService weaponService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWeaponMockMvc;

    private Weapon weapon;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Weapon createEntity(EntityManager em) {
        Weapon weapon = new Weapon()
            .baseATK(DEFAULT_BASE_ATK)
            .weaponType(DEFAULT_WEAPON_TYPE)
            .weaponSize(DEFAULT_WEAPON_SIZE)
            .rarity(DEFAULT_RARITY);
        return weapon;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Weapon createUpdatedEntity(EntityManager em) {
        Weapon weapon = new Weapon()
            .baseATK(UPDATED_BASE_ATK)
            .weaponType(UPDATED_WEAPON_TYPE)
            .weaponSize(UPDATED_WEAPON_SIZE)
            .rarity(UPDATED_RARITY);
        return weapon;
    }

    @BeforeEach
    public void initTest() {
        weapon = createEntity(em);
    }

    @Test
    @Transactional
    public void createWeapon() throws Exception {
        int databaseSizeBeforeCreate = weaponRepository.findAll().size();
        // Create the Weapon
        WeaponDTO weaponDTO = weaponMapper.toDto(weapon);
        restWeaponMockMvc.perform(post("/api/weapons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weaponDTO)))
            .andExpect(status().isCreated());

        // Validate the Weapon in the database
        List<Weapon> weaponList = weaponRepository.findAll();
        assertThat(weaponList).hasSize(databaseSizeBeforeCreate + 1);
        Weapon testWeapon = weaponList.get(weaponList.size() - 1);
        assertThat(testWeapon.getBaseATK()).isEqualTo(DEFAULT_BASE_ATK);
        assertThat(testWeapon.getWeaponType()).isEqualTo(DEFAULT_WEAPON_TYPE);
        assertThat(testWeapon.getWeaponSize()).isEqualTo(DEFAULT_WEAPON_SIZE);
        assertThat(testWeapon.getRarity()).isEqualTo(DEFAULT_RARITY);
    }

    @Test
    @Transactional
    public void createWeaponWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = weaponRepository.findAll().size();

        // Create the Weapon with an existing ID
        weapon.setId(1L);
        WeaponDTO weaponDTO = weaponMapper.toDto(weapon);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWeaponMockMvc.perform(post("/api/weapons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weaponDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Weapon in the database
        List<Weapon> weaponList = weaponRepository.findAll();
        assertThat(weaponList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWeapons() throws Exception {
        // Initialize the database
        weaponRepository.saveAndFlush(weapon);

        // Get all the weaponList
        restWeaponMockMvc.perform(get("/api/weapons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(weapon.getId().intValue())))
            .andExpect(jsonPath("$.[*].baseATK").value(hasItem(DEFAULT_BASE_ATK.doubleValue())))
            .andExpect(jsonPath("$.[*].weaponType").value(hasItem(DEFAULT_WEAPON_TYPE.toString())))
            .andExpect(jsonPath("$.[*].weaponSize").value(hasItem(DEFAULT_WEAPON_SIZE.toString())))
            .andExpect(jsonPath("$.[*].rarity").value(hasItem(DEFAULT_RARITY.toString())));
    }
    
    @Test
    @Transactional
    public void getWeapon() throws Exception {
        // Initialize the database
        weaponRepository.saveAndFlush(weapon);

        // Get the weapon
        restWeaponMockMvc.perform(get("/api/weapons/{id}", weapon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(weapon.getId().intValue()))
            .andExpect(jsonPath("$.baseATK").value(DEFAULT_BASE_ATK.doubleValue()))
            .andExpect(jsonPath("$.weaponType").value(DEFAULT_WEAPON_TYPE.toString()))
            .andExpect(jsonPath("$.weaponSize").value(DEFAULT_WEAPON_SIZE.toString()))
            .andExpect(jsonPath("$.rarity").value(DEFAULT_RARITY.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingWeapon() throws Exception {
        // Get the weapon
        restWeaponMockMvc.perform(get("/api/weapons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWeapon() throws Exception {
        // Initialize the database
        weaponRepository.saveAndFlush(weapon);

        int databaseSizeBeforeUpdate = weaponRepository.findAll().size();

        // Update the weapon
        Weapon updatedWeapon = weaponRepository.findById(weapon.getId()).get();
        // Disconnect from session so that the updates on updatedWeapon are not directly saved in db
        em.detach(updatedWeapon);
        updatedWeapon
            .baseATK(UPDATED_BASE_ATK)
            .weaponType(UPDATED_WEAPON_TYPE)
            .weaponSize(UPDATED_WEAPON_SIZE)
            .rarity(UPDATED_RARITY);
        WeaponDTO weaponDTO = weaponMapper.toDto(updatedWeapon);

        restWeaponMockMvc.perform(put("/api/weapons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weaponDTO)))
            .andExpect(status().isOk());

        // Validate the Weapon in the database
        List<Weapon> weaponList = weaponRepository.findAll();
        assertThat(weaponList).hasSize(databaseSizeBeforeUpdate);
        Weapon testWeapon = weaponList.get(weaponList.size() - 1);
        assertThat(testWeapon.getBaseATK()).isEqualTo(UPDATED_BASE_ATK);
        assertThat(testWeapon.getWeaponType()).isEqualTo(UPDATED_WEAPON_TYPE);
        assertThat(testWeapon.getWeaponSize()).isEqualTo(UPDATED_WEAPON_SIZE);
        assertThat(testWeapon.getRarity()).isEqualTo(UPDATED_RARITY);
    }

    @Test
    @Transactional
    public void updateNonExistingWeapon() throws Exception {
        int databaseSizeBeforeUpdate = weaponRepository.findAll().size();

        // Create the Weapon
        WeaponDTO weaponDTO = weaponMapper.toDto(weapon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWeaponMockMvc.perform(put("/api/weapons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(weaponDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Weapon in the database
        List<Weapon> weaponList = weaponRepository.findAll();
        assertThat(weaponList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWeapon() throws Exception {
        // Initialize the database
        weaponRepository.saveAndFlush(weapon);

        int databaseSizeBeforeDelete = weaponRepository.findAll().size();

        // Delete the weapon
        restWeaponMockMvc.perform(delete("/api/weapons/{id}", weapon.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Weapon> weaponList = weaponRepository.findAll();
        assertThat(weaponList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
