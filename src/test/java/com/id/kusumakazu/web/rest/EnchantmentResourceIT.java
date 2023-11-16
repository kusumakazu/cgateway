package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.Enchantment;
import com.id.kusumakazu.repository.EnchantmentRepository;
import com.id.kusumakazu.service.EnchantmentService;
import com.id.kusumakazu.service.dto.EnchantmentDTO;
import com.id.kusumakazu.service.mapper.EnchantmentMapper;

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

import com.id.kusumakazu.domain.enumeration.EnchantModifers;
/**
 * Integration tests for the {@link EnchantmentResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EnchantmentResourceIT {

    private static final String DEFAULT_ENCHANT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ENCHANT_NAME = "BBBBBBBBBB";

    private static final EnchantModifers DEFAULT_ENCHANT_MODIFER_TYPE = EnchantModifers.ATK_BOOST;
    private static final EnchantModifers UPDATED_ENCHANT_MODIFER_TYPE = EnchantModifers.ATK_PLUS;

    private static final Integer DEFAULT_VALUE_MODIFER = 1;
    private static final Integer UPDATED_VALUE_MODIFER = 2;

    @Autowired
    private EnchantmentRepository enchantmentRepository;

    @Autowired
    private EnchantmentMapper enchantmentMapper;

    @Autowired
    private EnchantmentService enchantmentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEnchantmentMockMvc;

    private Enchantment enchantment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Enchantment createEntity(EntityManager em) {
        Enchantment enchantment = new Enchantment()
            .enchantName(DEFAULT_ENCHANT_NAME)
            .enchantModiferType(DEFAULT_ENCHANT_MODIFER_TYPE)
            .valueModifer(DEFAULT_VALUE_MODIFER);
        return enchantment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Enchantment createUpdatedEntity(EntityManager em) {
        Enchantment enchantment = new Enchantment()
            .enchantName(UPDATED_ENCHANT_NAME)
            .enchantModiferType(UPDATED_ENCHANT_MODIFER_TYPE)
            .valueModifer(UPDATED_VALUE_MODIFER);
        return enchantment;
    }

    @BeforeEach
    public void initTest() {
        enchantment = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnchantment() throws Exception {
        int databaseSizeBeforeCreate = enchantmentRepository.findAll().size();
        // Create the Enchantment
        EnchantmentDTO enchantmentDTO = enchantmentMapper.toDto(enchantment);
        restEnchantmentMockMvc.perform(post("/api/enchantments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(enchantmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Enchantment in the database
        List<Enchantment> enchantmentList = enchantmentRepository.findAll();
        assertThat(enchantmentList).hasSize(databaseSizeBeforeCreate + 1);
        Enchantment testEnchantment = enchantmentList.get(enchantmentList.size() - 1);
        assertThat(testEnchantment.getEnchantName()).isEqualTo(DEFAULT_ENCHANT_NAME);
        assertThat(testEnchantment.getEnchantModiferType()).isEqualTo(DEFAULT_ENCHANT_MODIFER_TYPE);
        assertThat(testEnchantment.getValueModifer()).isEqualTo(DEFAULT_VALUE_MODIFER);
    }

    @Test
    @Transactional
    public void createEnchantmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enchantmentRepository.findAll().size();

        // Create the Enchantment with an existing ID
        enchantment.setId(1L);
        EnchantmentDTO enchantmentDTO = enchantmentMapper.toDto(enchantment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnchantmentMockMvc.perform(post("/api/enchantments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(enchantmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Enchantment in the database
        List<Enchantment> enchantmentList = enchantmentRepository.findAll();
        assertThat(enchantmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEnchantments() throws Exception {
        // Initialize the database
        enchantmentRepository.saveAndFlush(enchantment);

        // Get all the enchantmentList
        restEnchantmentMockMvc.perform(get("/api/enchantments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enchantment.getId().intValue())))
            .andExpect(jsonPath("$.[*].enchantName").value(hasItem(DEFAULT_ENCHANT_NAME)))
            .andExpect(jsonPath("$.[*].enchantModiferType").value(hasItem(DEFAULT_ENCHANT_MODIFER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].valueModifer").value(hasItem(DEFAULT_VALUE_MODIFER)));
    }
    
    @Test
    @Transactional
    public void getEnchantment() throws Exception {
        // Initialize the database
        enchantmentRepository.saveAndFlush(enchantment);

        // Get the enchantment
        restEnchantmentMockMvc.perform(get("/api/enchantments/{id}", enchantment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(enchantment.getId().intValue()))
            .andExpect(jsonPath("$.enchantName").value(DEFAULT_ENCHANT_NAME))
            .andExpect(jsonPath("$.enchantModiferType").value(DEFAULT_ENCHANT_MODIFER_TYPE.toString()))
            .andExpect(jsonPath("$.valueModifer").value(DEFAULT_VALUE_MODIFER));
    }
    @Test
    @Transactional
    public void getNonExistingEnchantment() throws Exception {
        // Get the enchantment
        restEnchantmentMockMvc.perform(get("/api/enchantments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnchantment() throws Exception {
        // Initialize the database
        enchantmentRepository.saveAndFlush(enchantment);

        int databaseSizeBeforeUpdate = enchantmentRepository.findAll().size();

        // Update the enchantment
        Enchantment updatedEnchantment = enchantmentRepository.findById(enchantment.getId()).get();
        // Disconnect from session so that the updates on updatedEnchantment are not directly saved in db
        em.detach(updatedEnchantment);
        updatedEnchantment
            .enchantName(UPDATED_ENCHANT_NAME)
            .enchantModiferType(UPDATED_ENCHANT_MODIFER_TYPE)
            .valueModifer(UPDATED_VALUE_MODIFER);
        EnchantmentDTO enchantmentDTO = enchantmentMapper.toDto(updatedEnchantment);

        restEnchantmentMockMvc.perform(put("/api/enchantments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(enchantmentDTO)))
            .andExpect(status().isOk());

        // Validate the Enchantment in the database
        List<Enchantment> enchantmentList = enchantmentRepository.findAll();
        assertThat(enchantmentList).hasSize(databaseSizeBeforeUpdate);
        Enchantment testEnchantment = enchantmentList.get(enchantmentList.size() - 1);
        assertThat(testEnchantment.getEnchantName()).isEqualTo(UPDATED_ENCHANT_NAME);
        assertThat(testEnchantment.getEnchantModiferType()).isEqualTo(UPDATED_ENCHANT_MODIFER_TYPE);
        assertThat(testEnchantment.getValueModifer()).isEqualTo(UPDATED_VALUE_MODIFER);
    }

    @Test
    @Transactional
    public void updateNonExistingEnchantment() throws Exception {
        int databaseSizeBeforeUpdate = enchantmentRepository.findAll().size();

        // Create the Enchantment
        EnchantmentDTO enchantmentDTO = enchantmentMapper.toDto(enchantment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEnchantmentMockMvc.perform(put("/api/enchantments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(enchantmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Enchantment in the database
        List<Enchantment> enchantmentList = enchantmentRepository.findAll();
        assertThat(enchantmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEnchantment() throws Exception {
        // Initialize the database
        enchantmentRepository.saveAndFlush(enchantment);

        int databaseSizeBeforeDelete = enchantmentRepository.findAll().size();

        // Delete the enchantment
        restEnchantmentMockMvc.perform(delete("/api/enchantments/{id}", enchantment.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Enchantment> enchantmentList = enchantmentRepository.findAll();
        assertThat(enchantmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
