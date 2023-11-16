package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.PlayerClass;
import com.id.kusumakazu.repository.PlayerClassRepository;
import com.id.kusumakazu.service.PlayerClassService;
import com.id.kusumakazu.service.dto.PlayerClassDTO;
import com.id.kusumakazu.service.mapper.PlayerClassMapper;

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

import com.id.kusumakazu.domain.enumeration.ClassAtkType;
/**
 * Integration tests for the {@link PlayerClassResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PlayerClassResourceIT {

    private static final String DEFAULT_CLASS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CLASS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CLASS_DETAIL_INFO = "AAAAAAAAAA";
    private static final String UPDATED_CLASS_DETAIL_INFO = "BBBBBBBBBB";

    private static final ClassAtkType DEFAULT_CLASS_ATK_TYPE = ClassAtkType.RANGED;
    private static final ClassAtkType UPDATED_CLASS_ATK_TYPE = ClassAtkType.PHYSICAL;

    private static final Double DEFAULT_CLASS_BONUS_STAT_A = 1D;
    private static final Double UPDATED_CLASS_BONUS_STAT_A = 2D;

    private static final Double DEFAULT_CLASS_BONUS_STAT_B = 1D;
    private static final Double UPDATED_CLASS_BONUS_STAT_B = 2D;

    private static final Integer DEFAULT_CLASS_BONUS_STAT_C = 1;
    private static final Integer UPDATED_CLASS_BONUS_STAT_C = 2;

    private static final Integer DEFAULT_CLASS_BONUS_STAT_D = 1;
    private static final Integer UPDATED_CLASS_BONUS_STAT_D = 2;

    private static final Integer DEFAULT_CLASS_BONUS_STAT_E = 1;
    private static final Integer UPDATED_CLASS_BONUS_STAT_E = 2;

    private static final Integer DEFAULT_CLASS_BONUS_STAT_F = 1;
    private static final Integer UPDATED_CLASS_BONUS_STAT_F = 2;

    private static final Integer DEFAULT_CLASS_BONUS_STAT_G = 1;
    private static final Integer UPDATED_CLASS_BONUS_STAT_G = 2;

    private static final Integer DEFAULT_CLASS_BONUS_STAT_H = 1;
    private static final Integer UPDATED_CLASS_BONUS_STAT_H = 2;

    private static final Double DEFAULT_CLASS_BONUS_HP = 1D;
    private static final Double UPDATED_CLASS_BONUS_HP = 2D;

    private static final Double DEFAULT_CLASS_BONUS_SP = 1D;
    private static final Double UPDATED_CLASS_BONUS_SP = 2D;

    @Autowired
    private PlayerClassRepository playerClassRepository;

    @Autowired
    private PlayerClassMapper playerClassMapper;

    @Autowired
    private PlayerClassService playerClassService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlayerClassMockMvc;

    private PlayerClass playerClass;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlayerClass createEntity(EntityManager em) {
        PlayerClass playerClass = new PlayerClass()
            .className(DEFAULT_CLASS_NAME)
            .classDetailInfo(DEFAULT_CLASS_DETAIL_INFO)
            .classAtkType(DEFAULT_CLASS_ATK_TYPE)
            .classBonusStatA(DEFAULT_CLASS_BONUS_STAT_A)
            .classBonusStatB(DEFAULT_CLASS_BONUS_STAT_B)
            .classBonusStatC(DEFAULT_CLASS_BONUS_STAT_C)
            .classBonusStatD(DEFAULT_CLASS_BONUS_STAT_D)
            .classBonusStatE(DEFAULT_CLASS_BONUS_STAT_E)
            .classBonusStatF(DEFAULT_CLASS_BONUS_STAT_F)
            .classBonusStatG(DEFAULT_CLASS_BONUS_STAT_G)
            .classBonusStatH(DEFAULT_CLASS_BONUS_STAT_H)
            .classBonusHP(DEFAULT_CLASS_BONUS_HP)
            .classBonusSP(DEFAULT_CLASS_BONUS_SP);
        return playerClass;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlayerClass createUpdatedEntity(EntityManager em) {
        PlayerClass playerClass = new PlayerClass()
            .className(UPDATED_CLASS_NAME)
            .classDetailInfo(UPDATED_CLASS_DETAIL_INFO)
            .classAtkType(UPDATED_CLASS_ATK_TYPE)
            .classBonusStatA(UPDATED_CLASS_BONUS_STAT_A)
            .classBonusStatB(UPDATED_CLASS_BONUS_STAT_B)
            .classBonusStatC(UPDATED_CLASS_BONUS_STAT_C)
            .classBonusStatD(UPDATED_CLASS_BONUS_STAT_D)
            .classBonusStatE(UPDATED_CLASS_BONUS_STAT_E)
            .classBonusStatF(UPDATED_CLASS_BONUS_STAT_F)
            .classBonusStatG(UPDATED_CLASS_BONUS_STAT_G)
            .classBonusStatH(UPDATED_CLASS_BONUS_STAT_H)
            .classBonusHP(UPDATED_CLASS_BONUS_HP)
            .classBonusSP(UPDATED_CLASS_BONUS_SP);
        return playerClass;
    }

    @BeforeEach
    public void initTest() {
        playerClass = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlayerClass() throws Exception {
        int databaseSizeBeforeCreate = playerClassRepository.findAll().size();
        // Create the PlayerClass
        PlayerClassDTO playerClassDTO = playerClassMapper.toDto(playerClass);
        restPlayerClassMockMvc.perform(post("/api/player-classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerClassDTO)))
            .andExpect(status().isCreated());

        // Validate the PlayerClass in the database
        List<PlayerClass> playerClassList = playerClassRepository.findAll();
        assertThat(playerClassList).hasSize(databaseSizeBeforeCreate + 1);
        PlayerClass testPlayerClass = playerClassList.get(playerClassList.size() - 1);
        assertThat(testPlayerClass.getClassName()).isEqualTo(DEFAULT_CLASS_NAME);
        assertThat(testPlayerClass.getClassDetailInfo()).isEqualTo(DEFAULT_CLASS_DETAIL_INFO);
        assertThat(testPlayerClass.getClassAtkType()).isEqualTo(DEFAULT_CLASS_ATK_TYPE);
        assertThat(testPlayerClass.getClassBonusStatA()).isEqualTo(DEFAULT_CLASS_BONUS_STAT_A);
        assertThat(testPlayerClass.getClassBonusStatB()).isEqualTo(DEFAULT_CLASS_BONUS_STAT_B);
        assertThat(testPlayerClass.getClassBonusStatC()).isEqualTo(DEFAULT_CLASS_BONUS_STAT_C);
        assertThat(testPlayerClass.getClassBonusStatD()).isEqualTo(DEFAULT_CLASS_BONUS_STAT_D);
        assertThat(testPlayerClass.getClassBonusStatE()).isEqualTo(DEFAULT_CLASS_BONUS_STAT_E);
        assertThat(testPlayerClass.getClassBonusStatF()).isEqualTo(DEFAULT_CLASS_BONUS_STAT_F);
        assertThat(testPlayerClass.getClassBonusStatG()).isEqualTo(DEFAULT_CLASS_BONUS_STAT_G);
        assertThat(testPlayerClass.getClassBonusStatH()).isEqualTo(DEFAULT_CLASS_BONUS_STAT_H);
        assertThat(testPlayerClass.getClassBonusHP()).isEqualTo(DEFAULT_CLASS_BONUS_HP);
        assertThat(testPlayerClass.getClassBonusSP()).isEqualTo(DEFAULT_CLASS_BONUS_SP);
    }

    @Test
    @Transactional
    public void createPlayerClassWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = playerClassRepository.findAll().size();

        // Create the PlayerClass with an existing ID
        playerClass.setId(1L);
        PlayerClassDTO playerClassDTO = playerClassMapper.toDto(playerClass);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlayerClassMockMvc.perform(post("/api/player-classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerClassDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlayerClass in the database
        List<PlayerClass> playerClassList = playerClassRepository.findAll();
        assertThat(playerClassList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPlayerClasses() throws Exception {
        // Initialize the database
        playerClassRepository.saveAndFlush(playerClass);

        // Get all the playerClassList
        restPlayerClassMockMvc.perform(get("/api/player-classes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(playerClass.getId().intValue())))
            .andExpect(jsonPath("$.[*].className").value(hasItem(DEFAULT_CLASS_NAME)))
            .andExpect(jsonPath("$.[*].classDetailInfo").value(hasItem(DEFAULT_CLASS_DETAIL_INFO)))
            .andExpect(jsonPath("$.[*].classAtkType").value(hasItem(DEFAULT_CLASS_ATK_TYPE.toString())))
            .andExpect(jsonPath("$.[*].classBonusStatA").value(hasItem(DEFAULT_CLASS_BONUS_STAT_A.doubleValue())))
            .andExpect(jsonPath("$.[*].classBonusStatB").value(hasItem(DEFAULT_CLASS_BONUS_STAT_B.doubleValue())))
            .andExpect(jsonPath("$.[*].classBonusStatC").value(hasItem(DEFAULT_CLASS_BONUS_STAT_C)))
            .andExpect(jsonPath("$.[*].classBonusStatD").value(hasItem(DEFAULT_CLASS_BONUS_STAT_D)))
            .andExpect(jsonPath("$.[*].classBonusStatE").value(hasItem(DEFAULT_CLASS_BONUS_STAT_E)))
            .andExpect(jsonPath("$.[*].classBonusStatF").value(hasItem(DEFAULT_CLASS_BONUS_STAT_F)))
            .andExpect(jsonPath("$.[*].classBonusStatG").value(hasItem(DEFAULT_CLASS_BONUS_STAT_G)))
            .andExpect(jsonPath("$.[*].classBonusStatH").value(hasItem(DEFAULT_CLASS_BONUS_STAT_H)))
            .andExpect(jsonPath("$.[*].classBonusHP").value(hasItem(DEFAULT_CLASS_BONUS_HP.doubleValue())))
            .andExpect(jsonPath("$.[*].classBonusSP").value(hasItem(DEFAULT_CLASS_BONUS_SP.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getPlayerClass() throws Exception {
        // Initialize the database
        playerClassRepository.saveAndFlush(playerClass);

        // Get the playerClass
        restPlayerClassMockMvc.perform(get("/api/player-classes/{id}", playerClass.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(playerClass.getId().intValue()))
            .andExpect(jsonPath("$.className").value(DEFAULT_CLASS_NAME))
            .andExpect(jsonPath("$.classDetailInfo").value(DEFAULT_CLASS_DETAIL_INFO))
            .andExpect(jsonPath("$.classAtkType").value(DEFAULT_CLASS_ATK_TYPE.toString()))
            .andExpect(jsonPath("$.classBonusStatA").value(DEFAULT_CLASS_BONUS_STAT_A.doubleValue()))
            .andExpect(jsonPath("$.classBonusStatB").value(DEFAULT_CLASS_BONUS_STAT_B.doubleValue()))
            .andExpect(jsonPath("$.classBonusStatC").value(DEFAULT_CLASS_BONUS_STAT_C))
            .andExpect(jsonPath("$.classBonusStatD").value(DEFAULT_CLASS_BONUS_STAT_D))
            .andExpect(jsonPath("$.classBonusStatE").value(DEFAULT_CLASS_BONUS_STAT_E))
            .andExpect(jsonPath("$.classBonusStatF").value(DEFAULT_CLASS_BONUS_STAT_F))
            .andExpect(jsonPath("$.classBonusStatG").value(DEFAULT_CLASS_BONUS_STAT_G))
            .andExpect(jsonPath("$.classBonusStatH").value(DEFAULT_CLASS_BONUS_STAT_H))
            .andExpect(jsonPath("$.classBonusHP").value(DEFAULT_CLASS_BONUS_HP.doubleValue()))
            .andExpect(jsonPath("$.classBonusSP").value(DEFAULT_CLASS_BONUS_SP.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPlayerClass() throws Exception {
        // Get the playerClass
        restPlayerClassMockMvc.perform(get("/api/player-classes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlayerClass() throws Exception {
        // Initialize the database
        playerClassRepository.saveAndFlush(playerClass);

        int databaseSizeBeforeUpdate = playerClassRepository.findAll().size();

        // Update the playerClass
        PlayerClass updatedPlayerClass = playerClassRepository.findById(playerClass.getId()).get();
        // Disconnect from session so that the updates on updatedPlayerClass are not directly saved in db
        em.detach(updatedPlayerClass);
        updatedPlayerClass
            .className(UPDATED_CLASS_NAME)
            .classDetailInfo(UPDATED_CLASS_DETAIL_INFO)
            .classAtkType(UPDATED_CLASS_ATK_TYPE)
            .classBonusStatA(UPDATED_CLASS_BONUS_STAT_A)
            .classBonusStatB(UPDATED_CLASS_BONUS_STAT_B)
            .classBonusStatC(UPDATED_CLASS_BONUS_STAT_C)
            .classBonusStatD(UPDATED_CLASS_BONUS_STAT_D)
            .classBonusStatE(UPDATED_CLASS_BONUS_STAT_E)
            .classBonusStatF(UPDATED_CLASS_BONUS_STAT_F)
            .classBonusStatG(UPDATED_CLASS_BONUS_STAT_G)
            .classBonusStatH(UPDATED_CLASS_BONUS_STAT_H)
            .classBonusHP(UPDATED_CLASS_BONUS_HP)
            .classBonusSP(UPDATED_CLASS_BONUS_SP);
        PlayerClassDTO playerClassDTO = playerClassMapper.toDto(updatedPlayerClass);

        restPlayerClassMockMvc.perform(put("/api/player-classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerClassDTO)))
            .andExpect(status().isOk());

        // Validate the PlayerClass in the database
        List<PlayerClass> playerClassList = playerClassRepository.findAll();
        assertThat(playerClassList).hasSize(databaseSizeBeforeUpdate);
        PlayerClass testPlayerClass = playerClassList.get(playerClassList.size() - 1);
        assertThat(testPlayerClass.getClassName()).isEqualTo(UPDATED_CLASS_NAME);
        assertThat(testPlayerClass.getClassDetailInfo()).isEqualTo(UPDATED_CLASS_DETAIL_INFO);
        assertThat(testPlayerClass.getClassAtkType()).isEqualTo(UPDATED_CLASS_ATK_TYPE);
        assertThat(testPlayerClass.getClassBonusStatA()).isEqualTo(UPDATED_CLASS_BONUS_STAT_A);
        assertThat(testPlayerClass.getClassBonusStatB()).isEqualTo(UPDATED_CLASS_BONUS_STAT_B);
        assertThat(testPlayerClass.getClassBonusStatC()).isEqualTo(UPDATED_CLASS_BONUS_STAT_C);
        assertThat(testPlayerClass.getClassBonusStatD()).isEqualTo(UPDATED_CLASS_BONUS_STAT_D);
        assertThat(testPlayerClass.getClassBonusStatE()).isEqualTo(UPDATED_CLASS_BONUS_STAT_E);
        assertThat(testPlayerClass.getClassBonusStatF()).isEqualTo(UPDATED_CLASS_BONUS_STAT_F);
        assertThat(testPlayerClass.getClassBonusStatG()).isEqualTo(UPDATED_CLASS_BONUS_STAT_G);
        assertThat(testPlayerClass.getClassBonusStatH()).isEqualTo(UPDATED_CLASS_BONUS_STAT_H);
        assertThat(testPlayerClass.getClassBonusHP()).isEqualTo(UPDATED_CLASS_BONUS_HP);
        assertThat(testPlayerClass.getClassBonusSP()).isEqualTo(UPDATED_CLASS_BONUS_SP);
    }

    @Test
    @Transactional
    public void updateNonExistingPlayerClass() throws Exception {
        int databaseSizeBeforeUpdate = playerClassRepository.findAll().size();

        // Create the PlayerClass
        PlayerClassDTO playerClassDTO = playerClassMapper.toDto(playerClass);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlayerClassMockMvc.perform(put("/api/player-classes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerClassDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlayerClass in the database
        List<PlayerClass> playerClassList = playerClassRepository.findAll();
        assertThat(playerClassList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlayerClass() throws Exception {
        // Initialize the database
        playerClassRepository.saveAndFlush(playerClass);

        int databaseSizeBeforeDelete = playerClassRepository.findAll().size();

        // Delete the playerClass
        restPlayerClassMockMvc.perform(delete("/api/player-classes/{id}", playerClass.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PlayerClass> playerClassList = playerClassRepository.findAll();
        assertThat(playerClassList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
