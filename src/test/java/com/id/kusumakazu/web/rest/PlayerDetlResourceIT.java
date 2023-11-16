package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.PlayerDetl;
import com.id.kusumakazu.repository.PlayerDetlRepository;
import com.id.kusumakazu.service.PlayerDetlService;
import com.id.kusumakazu.service.dto.PlayerDetlDTO;
import com.id.kusumakazu.service.mapper.PlayerDetlMapper;

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
 * Integration tests for the {@link PlayerDetlResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PlayerDetlResourceIT {

    private static final Integer DEFAULT_CHARA_ATTRIBUTE_POINTS = 1;
    private static final Integer UPDATED_CHARA_ATTRIBUTE_POINTS = 2;

    private static final Double DEFAULT_CHARA_STAT_A = 1D;
    private static final Double UPDATED_CHARA_STAT_A = 2D;

    private static final Double DEFAULT_CHARA_STAT_B = 1D;
    private static final Double UPDATED_CHARA_STAT_B = 2D;

    private static final Integer DEFAULT_CHARA_STAT_C = 1;
    private static final Integer UPDATED_CHARA_STAT_C = 2;

    private static final Integer DEFAULT_CHARA_STAT_D = 1;
    private static final Integer UPDATED_CHARA_STAT_D = 2;

    private static final Integer DEFAULT_CHARA_STAT_E = 1;
    private static final Integer UPDATED_CHARA_STAT_E = 2;

    private static final Integer DEFAULT_CHARA_STAT_F = 1;
    private static final Integer UPDATED_CHARA_STAT_F = 2;

    private static final Integer DEFAULT_CHARA_STAT_G = 1;
    private static final Integer UPDATED_CHARA_STAT_G = 2;

    private static final Integer DEFAULT_CHARA_STAT_H = 1;
    private static final Integer UPDATED_CHARA_STAT_H = 2;

    private static final Double DEFAULT_CHARA_BEHAVIOUR_A = 1D;
    private static final Double UPDATED_CHARA_BEHAVIOUR_A = 2D;

    private static final Double DEFAULT_CHARA_BEHAVIOUR_B = 1D;
    private static final Double UPDATED_CHARA_BEHAVIOUR_B = 2D;

    private static final Double DEFAULT_CHARA_STAT_HP = 1D;
    private static final Double UPDATED_CHARA_STAT_HP = 2D;

    private static final Double DEFAULT_CHARA_STAT_MP = 1D;
    private static final Double UPDATED_CHARA_STAT_MP = 2D;

    @Autowired
    private PlayerDetlRepository playerDetlRepository;

    @Autowired
    private PlayerDetlMapper playerDetlMapper;

    @Autowired
    private PlayerDetlService playerDetlService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlayerDetlMockMvc;

    private PlayerDetl playerDetl;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlayerDetl createEntity(EntityManager em) {
        PlayerDetl playerDetl = new PlayerDetl()
            .charaAttributePoints(DEFAULT_CHARA_ATTRIBUTE_POINTS)
            .charaStatA(DEFAULT_CHARA_STAT_A)
            .charaStatB(DEFAULT_CHARA_STAT_B)
            .charaStatC(DEFAULT_CHARA_STAT_C)
            .charaStatD(DEFAULT_CHARA_STAT_D)
            .charaStatE(DEFAULT_CHARA_STAT_E)
            .charaStatF(DEFAULT_CHARA_STAT_F)
            .charaStatG(DEFAULT_CHARA_STAT_G)
            .charaStatH(DEFAULT_CHARA_STAT_H)
            .charaBehaviourA(DEFAULT_CHARA_BEHAVIOUR_A)
            .charaBehaviourB(DEFAULT_CHARA_BEHAVIOUR_B)
            .charaStatHP(DEFAULT_CHARA_STAT_HP)
            .charaStatMP(DEFAULT_CHARA_STAT_MP);
        return playerDetl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlayerDetl createUpdatedEntity(EntityManager em) {
        PlayerDetl playerDetl = new PlayerDetl()
            .charaAttributePoints(UPDATED_CHARA_ATTRIBUTE_POINTS)
            .charaStatA(UPDATED_CHARA_STAT_A)
            .charaStatB(UPDATED_CHARA_STAT_B)
            .charaStatC(UPDATED_CHARA_STAT_C)
            .charaStatD(UPDATED_CHARA_STAT_D)
            .charaStatE(UPDATED_CHARA_STAT_E)
            .charaStatF(UPDATED_CHARA_STAT_F)
            .charaStatG(UPDATED_CHARA_STAT_G)
            .charaStatH(UPDATED_CHARA_STAT_H)
            .charaBehaviourA(UPDATED_CHARA_BEHAVIOUR_A)
            .charaBehaviourB(UPDATED_CHARA_BEHAVIOUR_B)
            .charaStatHP(UPDATED_CHARA_STAT_HP)
            .charaStatMP(UPDATED_CHARA_STAT_MP);
        return playerDetl;
    }

    @BeforeEach
    public void initTest() {
        playerDetl = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlayerDetl() throws Exception {
        int databaseSizeBeforeCreate = playerDetlRepository.findAll().size();
        // Create the PlayerDetl
        PlayerDetlDTO playerDetlDTO = playerDetlMapper.toDto(playerDetl);
        restPlayerDetlMockMvc.perform(post("/api/player-detls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerDetlDTO)))
            .andExpect(status().isCreated());

        // Validate the PlayerDetl in the database
        List<PlayerDetl> playerDetlList = playerDetlRepository.findAll();
        assertThat(playerDetlList).hasSize(databaseSizeBeforeCreate + 1);
        PlayerDetl testPlayerDetl = playerDetlList.get(playerDetlList.size() - 1);
        assertThat(testPlayerDetl.getCharaAttributePoints()).isEqualTo(DEFAULT_CHARA_ATTRIBUTE_POINTS);
        assertThat(testPlayerDetl.getCharaStatA()).isEqualTo(DEFAULT_CHARA_STAT_A);
        assertThat(testPlayerDetl.getCharaStatB()).isEqualTo(DEFAULT_CHARA_STAT_B);
        assertThat(testPlayerDetl.getCharaStatC()).isEqualTo(DEFAULT_CHARA_STAT_C);
        assertThat(testPlayerDetl.getCharaStatD()).isEqualTo(DEFAULT_CHARA_STAT_D);
        assertThat(testPlayerDetl.getCharaStatE()).isEqualTo(DEFAULT_CHARA_STAT_E);
        assertThat(testPlayerDetl.getCharaStatF()).isEqualTo(DEFAULT_CHARA_STAT_F);
        assertThat(testPlayerDetl.getCharaStatG()).isEqualTo(DEFAULT_CHARA_STAT_G);
        assertThat(testPlayerDetl.getCharaStatH()).isEqualTo(DEFAULT_CHARA_STAT_H);
        assertThat(testPlayerDetl.getCharaBehaviourA()).isEqualTo(DEFAULT_CHARA_BEHAVIOUR_A);
        assertThat(testPlayerDetl.getCharaBehaviourB()).isEqualTo(DEFAULT_CHARA_BEHAVIOUR_B);
        assertThat(testPlayerDetl.getCharaStatHP()).isEqualTo(DEFAULT_CHARA_STAT_HP);
        assertThat(testPlayerDetl.getCharaStatMP()).isEqualTo(DEFAULT_CHARA_STAT_MP);
    }

    @Test
    @Transactional
    public void createPlayerDetlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = playerDetlRepository.findAll().size();

        // Create the PlayerDetl with an existing ID
        playerDetl.setId(1L);
        PlayerDetlDTO playerDetlDTO = playerDetlMapper.toDto(playerDetl);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlayerDetlMockMvc.perform(post("/api/player-detls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerDetlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlayerDetl in the database
        List<PlayerDetl> playerDetlList = playerDetlRepository.findAll();
        assertThat(playerDetlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPlayerDetls() throws Exception {
        // Initialize the database
        playerDetlRepository.saveAndFlush(playerDetl);

        // Get all the playerDetlList
        restPlayerDetlMockMvc.perform(get("/api/player-detls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(playerDetl.getId().intValue())))
            .andExpect(jsonPath("$.[*].charaAttributePoints").value(hasItem(DEFAULT_CHARA_ATTRIBUTE_POINTS)))
            .andExpect(jsonPath("$.[*].charaStatA").value(hasItem(DEFAULT_CHARA_STAT_A.doubleValue())))
            .andExpect(jsonPath("$.[*].charaStatB").value(hasItem(DEFAULT_CHARA_STAT_B.doubleValue())))
            .andExpect(jsonPath("$.[*].charaStatC").value(hasItem(DEFAULT_CHARA_STAT_C)))
            .andExpect(jsonPath("$.[*].charaStatD").value(hasItem(DEFAULT_CHARA_STAT_D)))
            .andExpect(jsonPath("$.[*].charaStatE").value(hasItem(DEFAULT_CHARA_STAT_E)))
            .andExpect(jsonPath("$.[*].charaStatF").value(hasItem(DEFAULT_CHARA_STAT_F)))
            .andExpect(jsonPath("$.[*].charaStatG").value(hasItem(DEFAULT_CHARA_STAT_G)))
            .andExpect(jsonPath("$.[*].charaStatH").value(hasItem(DEFAULT_CHARA_STAT_H)))
            .andExpect(jsonPath("$.[*].charaBehaviourA").value(hasItem(DEFAULT_CHARA_BEHAVIOUR_A.doubleValue())))
            .andExpect(jsonPath("$.[*].charaBehaviourB").value(hasItem(DEFAULT_CHARA_BEHAVIOUR_B.doubleValue())))
            .andExpect(jsonPath("$.[*].charaStatHP").value(hasItem(DEFAULT_CHARA_STAT_HP.doubleValue())))
            .andExpect(jsonPath("$.[*].charaStatMP").value(hasItem(DEFAULT_CHARA_STAT_MP.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getPlayerDetl() throws Exception {
        // Initialize the database
        playerDetlRepository.saveAndFlush(playerDetl);

        // Get the playerDetl
        restPlayerDetlMockMvc.perform(get("/api/player-detls/{id}", playerDetl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(playerDetl.getId().intValue()))
            .andExpect(jsonPath("$.charaAttributePoints").value(DEFAULT_CHARA_ATTRIBUTE_POINTS))
            .andExpect(jsonPath("$.charaStatA").value(DEFAULT_CHARA_STAT_A.doubleValue()))
            .andExpect(jsonPath("$.charaStatB").value(DEFAULT_CHARA_STAT_B.doubleValue()))
            .andExpect(jsonPath("$.charaStatC").value(DEFAULT_CHARA_STAT_C))
            .andExpect(jsonPath("$.charaStatD").value(DEFAULT_CHARA_STAT_D))
            .andExpect(jsonPath("$.charaStatE").value(DEFAULT_CHARA_STAT_E))
            .andExpect(jsonPath("$.charaStatF").value(DEFAULT_CHARA_STAT_F))
            .andExpect(jsonPath("$.charaStatG").value(DEFAULT_CHARA_STAT_G))
            .andExpect(jsonPath("$.charaStatH").value(DEFAULT_CHARA_STAT_H))
            .andExpect(jsonPath("$.charaBehaviourA").value(DEFAULT_CHARA_BEHAVIOUR_A.doubleValue()))
            .andExpect(jsonPath("$.charaBehaviourB").value(DEFAULT_CHARA_BEHAVIOUR_B.doubleValue()))
            .andExpect(jsonPath("$.charaStatHP").value(DEFAULT_CHARA_STAT_HP.doubleValue()))
            .andExpect(jsonPath("$.charaStatMP").value(DEFAULT_CHARA_STAT_MP.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingPlayerDetl() throws Exception {
        // Get the playerDetl
        restPlayerDetlMockMvc.perform(get("/api/player-detls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlayerDetl() throws Exception {
        // Initialize the database
        playerDetlRepository.saveAndFlush(playerDetl);

        int databaseSizeBeforeUpdate = playerDetlRepository.findAll().size();

        // Update the playerDetl
        PlayerDetl updatedPlayerDetl = playerDetlRepository.findById(playerDetl.getId()).get();
        // Disconnect from session so that the updates on updatedPlayerDetl are not directly saved in db
        em.detach(updatedPlayerDetl);
        updatedPlayerDetl
            .charaAttributePoints(UPDATED_CHARA_ATTRIBUTE_POINTS)
            .charaStatA(UPDATED_CHARA_STAT_A)
            .charaStatB(UPDATED_CHARA_STAT_B)
            .charaStatC(UPDATED_CHARA_STAT_C)
            .charaStatD(UPDATED_CHARA_STAT_D)
            .charaStatE(UPDATED_CHARA_STAT_E)
            .charaStatF(UPDATED_CHARA_STAT_F)
            .charaStatG(UPDATED_CHARA_STAT_G)
            .charaStatH(UPDATED_CHARA_STAT_H)
            .charaBehaviourA(UPDATED_CHARA_BEHAVIOUR_A)
            .charaBehaviourB(UPDATED_CHARA_BEHAVIOUR_B)
            .charaStatHP(UPDATED_CHARA_STAT_HP)
            .charaStatMP(UPDATED_CHARA_STAT_MP);
        PlayerDetlDTO playerDetlDTO = playerDetlMapper.toDto(updatedPlayerDetl);

        restPlayerDetlMockMvc.perform(put("/api/player-detls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerDetlDTO)))
            .andExpect(status().isOk());

        // Validate the PlayerDetl in the database
        List<PlayerDetl> playerDetlList = playerDetlRepository.findAll();
        assertThat(playerDetlList).hasSize(databaseSizeBeforeUpdate);
        PlayerDetl testPlayerDetl = playerDetlList.get(playerDetlList.size() - 1);
        assertThat(testPlayerDetl.getCharaAttributePoints()).isEqualTo(UPDATED_CHARA_ATTRIBUTE_POINTS);
        assertThat(testPlayerDetl.getCharaStatA()).isEqualTo(UPDATED_CHARA_STAT_A);
        assertThat(testPlayerDetl.getCharaStatB()).isEqualTo(UPDATED_CHARA_STAT_B);
        assertThat(testPlayerDetl.getCharaStatC()).isEqualTo(UPDATED_CHARA_STAT_C);
        assertThat(testPlayerDetl.getCharaStatD()).isEqualTo(UPDATED_CHARA_STAT_D);
        assertThat(testPlayerDetl.getCharaStatE()).isEqualTo(UPDATED_CHARA_STAT_E);
        assertThat(testPlayerDetl.getCharaStatF()).isEqualTo(UPDATED_CHARA_STAT_F);
        assertThat(testPlayerDetl.getCharaStatG()).isEqualTo(UPDATED_CHARA_STAT_G);
        assertThat(testPlayerDetl.getCharaStatH()).isEqualTo(UPDATED_CHARA_STAT_H);
        assertThat(testPlayerDetl.getCharaBehaviourA()).isEqualTo(UPDATED_CHARA_BEHAVIOUR_A);
        assertThat(testPlayerDetl.getCharaBehaviourB()).isEqualTo(UPDATED_CHARA_BEHAVIOUR_B);
        assertThat(testPlayerDetl.getCharaStatHP()).isEqualTo(UPDATED_CHARA_STAT_HP);
        assertThat(testPlayerDetl.getCharaStatMP()).isEqualTo(UPDATED_CHARA_STAT_MP);
    }

    @Test
    @Transactional
    public void updateNonExistingPlayerDetl() throws Exception {
        int databaseSizeBeforeUpdate = playerDetlRepository.findAll().size();

        // Create the PlayerDetl
        PlayerDetlDTO playerDetlDTO = playerDetlMapper.toDto(playerDetl);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlayerDetlMockMvc.perform(put("/api/player-detls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerDetlDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlayerDetl in the database
        List<PlayerDetl> playerDetlList = playerDetlRepository.findAll();
        assertThat(playerDetlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlayerDetl() throws Exception {
        // Initialize the database
        playerDetlRepository.saveAndFlush(playerDetl);

        int databaseSizeBeforeDelete = playerDetlRepository.findAll().size();

        // Delete the playerDetl
        restPlayerDetlMockMvc.perform(delete("/api/player-detls/{id}", playerDetl.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PlayerDetl> playerDetlList = playerDetlRepository.findAll();
        assertThat(playerDetlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
