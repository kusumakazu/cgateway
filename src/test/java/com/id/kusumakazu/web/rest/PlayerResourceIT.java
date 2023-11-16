package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.Player;
import com.id.kusumakazu.repository.PlayerRepository;
import com.id.kusumakazu.service.PlayerService;
import com.id.kusumakazu.service.dto.PlayerDTO;
import com.id.kusumakazu.service.mapper.PlayerMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.id.kusumakazu.domain.enumeration.PlayerType;
import com.id.kusumakazu.domain.enumeration.Faction;
/**
 * Integration tests for the {@link PlayerResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PlayerResourceIT {

    private static final String DEFAULT_CHARACTER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHARACTER_NAME = "BBBBBBBBBB";

    private static final PlayerType DEFAULT_CHARACTER_TYPE = PlayerType.HUMAN;
    private static final PlayerType UPDATED_CHARACTER_TYPE = PlayerType.ELF;

    private static final Faction DEFAULT_CHARACTER_FACTION = Faction.FACTION_A;
    private static final Faction UPDATED_CHARACTER_FACTION = Faction.FACTION_B;

    private static final Long DEFAULT_EXPERIENCE = 1L;
    private static final Long UPDATED_EXPERIENCE = 2L;

    private static final Integer DEFAULT_CHARACTER_LEVEL = 1;
    private static final Integer UPDATED_CHARACTER_LEVEL = 2;

    private static final String DEFAULT_LAST_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LAST_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_LOCATION_COORDINATE = "AAAAAAAAAA";
    private static final String UPDATED_LAST_LOCATION_COORDINATE = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_LOGIN = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_LOGIN = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlayerMockMvc;

    private Player player;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Player createEntity(EntityManager em) {
        Player player = new Player()
            .characterName(DEFAULT_CHARACTER_NAME)
            .characterType(DEFAULT_CHARACTER_TYPE)
            .characterFaction(DEFAULT_CHARACTER_FACTION)
            .experience(DEFAULT_EXPERIENCE)
            .characterLevel(DEFAULT_CHARACTER_LEVEL)
            .lastLocation(DEFAULT_LAST_LOCATION)
            .lastLocationCoordinate(DEFAULT_LAST_LOCATION_COORDINATE)
            .lastLogin(DEFAULT_LAST_LOGIN);
        return player;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Player createUpdatedEntity(EntityManager em) {
        Player player = new Player()
            .characterName(UPDATED_CHARACTER_NAME)
            .characterType(UPDATED_CHARACTER_TYPE)
            .characterFaction(UPDATED_CHARACTER_FACTION)
            .experience(UPDATED_EXPERIENCE)
            .characterLevel(UPDATED_CHARACTER_LEVEL)
            .lastLocation(UPDATED_LAST_LOCATION)
            .lastLocationCoordinate(UPDATED_LAST_LOCATION_COORDINATE)
            .lastLogin(UPDATED_LAST_LOGIN);
        return player;
    }

    @BeforeEach
    public void initTest() {
        player = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlayer() throws Exception {
        int databaseSizeBeforeCreate = playerRepository.findAll().size();
        // Create the Player
        PlayerDTO playerDTO = playerMapper.toDto(player);
        restPlayerMockMvc.perform(post("/api/players")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerDTO)))
            .andExpect(status().isCreated());

        // Validate the Player in the database
        List<Player> playerList = playerRepository.findAll();
        assertThat(playerList).hasSize(databaseSizeBeforeCreate + 1);
        Player testPlayer = playerList.get(playerList.size() - 1);
        assertThat(testPlayer.getCharacterName()).isEqualTo(DEFAULT_CHARACTER_NAME);
        assertThat(testPlayer.getCharacterType()).isEqualTo(DEFAULT_CHARACTER_TYPE);
        assertThat(testPlayer.getCharacterFaction()).isEqualTo(DEFAULT_CHARACTER_FACTION);
        assertThat(testPlayer.getExperience()).isEqualTo(DEFAULT_EXPERIENCE);
        assertThat(testPlayer.getCharacterLevel()).isEqualTo(DEFAULT_CHARACTER_LEVEL);
        assertThat(testPlayer.getLastLocation()).isEqualTo(DEFAULT_LAST_LOCATION);
        assertThat(testPlayer.getLastLocationCoordinate()).isEqualTo(DEFAULT_LAST_LOCATION_COORDINATE);
        assertThat(testPlayer.getLastLogin()).isEqualTo(DEFAULT_LAST_LOGIN);
    }

    @Test
    @Transactional
    public void createPlayerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = playerRepository.findAll().size();

        // Create the Player with an existing ID
        player.setId(1L);
        PlayerDTO playerDTO = playerMapper.toDto(player);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlayerMockMvc.perform(post("/api/players")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Player in the database
        List<Player> playerList = playerRepository.findAll();
        assertThat(playerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCharacterNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = playerRepository.findAll().size();
        // set the field null
        player.setCharacterName(null);

        // Create the Player, which fails.
        PlayerDTO playerDTO = playerMapper.toDto(player);


        restPlayerMockMvc.perform(post("/api/players")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerDTO)))
            .andExpect(status().isBadRequest());

        List<Player> playerList = playerRepository.findAll();
        assertThat(playerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlayers() throws Exception {
        // Initialize the database
        playerRepository.saveAndFlush(player);

        // Get all the playerList
        restPlayerMockMvc.perform(get("/api/players?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(player.getId().intValue())))
            .andExpect(jsonPath("$.[*].characterName").value(hasItem(DEFAULT_CHARACTER_NAME)))
            .andExpect(jsonPath("$.[*].characterType").value(hasItem(DEFAULT_CHARACTER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].characterFaction").value(hasItem(DEFAULT_CHARACTER_FACTION.toString())))
            .andExpect(jsonPath("$.[*].experience").value(hasItem(DEFAULT_EXPERIENCE.intValue())))
            .andExpect(jsonPath("$.[*].characterLevel").value(hasItem(DEFAULT_CHARACTER_LEVEL)))
            .andExpect(jsonPath("$.[*].lastLocation").value(hasItem(DEFAULT_LAST_LOCATION)))
            .andExpect(jsonPath("$.[*].lastLocationCoordinate").value(hasItem(DEFAULT_LAST_LOCATION_COORDINATE)))
            .andExpect(jsonPath("$.[*].lastLogin").value(hasItem(DEFAULT_LAST_LOGIN.toString())));
    }
    
    @Test
    @Transactional
    public void getPlayer() throws Exception {
        // Initialize the database
        playerRepository.saveAndFlush(player);

        // Get the player
        restPlayerMockMvc.perform(get("/api/players/{id}", player.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(player.getId().intValue()))
            .andExpect(jsonPath("$.characterName").value(DEFAULT_CHARACTER_NAME))
            .andExpect(jsonPath("$.characterType").value(DEFAULT_CHARACTER_TYPE.toString()))
            .andExpect(jsonPath("$.characterFaction").value(DEFAULT_CHARACTER_FACTION.toString()))
            .andExpect(jsonPath("$.experience").value(DEFAULT_EXPERIENCE.intValue()))
            .andExpect(jsonPath("$.characterLevel").value(DEFAULT_CHARACTER_LEVEL))
            .andExpect(jsonPath("$.lastLocation").value(DEFAULT_LAST_LOCATION))
            .andExpect(jsonPath("$.lastLocationCoordinate").value(DEFAULT_LAST_LOCATION_COORDINATE))
            .andExpect(jsonPath("$.lastLogin").value(DEFAULT_LAST_LOGIN.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPlayer() throws Exception {
        // Get the player
        restPlayerMockMvc.perform(get("/api/players/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlayer() throws Exception {
        // Initialize the database
        playerRepository.saveAndFlush(player);

        int databaseSizeBeforeUpdate = playerRepository.findAll().size();

        // Update the player
        Player updatedPlayer = playerRepository.findById(player.getId()).get();
        // Disconnect from session so that the updates on updatedPlayer are not directly saved in db
        em.detach(updatedPlayer);
        updatedPlayer
            .characterName(UPDATED_CHARACTER_NAME)
            .characterType(UPDATED_CHARACTER_TYPE)
            .characterFaction(UPDATED_CHARACTER_FACTION)
            .experience(UPDATED_EXPERIENCE)
            .characterLevel(UPDATED_CHARACTER_LEVEL)
            .lastLocation(UPDATED_LAST_LOCATION)
            .lastLocationCoordinate(UPDATED_LAST_LOCATION_COORDINATE)
            .lastLogin(UPDATED_LAST_LOGIN);
        PlayerDTO playerDTO = playerMapper.toDto(updatedPlayer);

        restPlayerMockMvc.perform(put("/api/players")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerDTO)))
            .andExpect(status().isOk());

        // Validate the Player in the database
        List<Player> playerList = playerRepository.findAll();
        assertThat(playerList).hasSize(databaseSizeBeforeUpdate);
        Player testPlayer = playerList.get(playerList.size() - 1);
        assertThat(testPlayer.getCharacterName()).isEqualTo(UPDATED_CHARACTER_NAME);
        assertThat(testPlayer.getCharacterType()).isEqualTo(UPDATED_CHARACTER_TYPE);
        assertThat(testPlayer.getCharacterFaction()).isEqualTo(UPDATED_CHARACTER_FACTION);
        assertThat(testPlayer.getExperience()).isEqualTo(UPDATED_EXPERIENCE);
        assertThat(testPlayer.getCharacterLevel()).isEqualTo(UPDATED_CHARACTER_LEVEL);
        assertThat(testPlayer.getLastLocation()).isEqualTo(UPDATED_LAST_LOCATION);
        assertThat(testPlayer.getLastLocationCoordinate()).isEqualTo(UPDATED_LAST_LOCATION_COORDINATE);
        assertThat(testPlayer.getLastLogin()).isEqualTo(UPDATED_LAST_LOGIN);
    }

    @Test
    @Transactional
    public void updateNonExistingPlayer() throws Exception {
        int databaseSizeBeforeUpdate = playerRepository.findAll().size();

        // Create the Player
        PlayerDTO playerDTO = playerMapper.toDto(player);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlayerMockMvc.perform(put("/api/players")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(playerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Player in the database
        List<Player> playerList = playerRepository.findAll();
        assertThat(playerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlayer() throws Exception {
        // Initialize the database
        playerRepository.saveAndFlush(player);

        int databaseSizeBeforeDelete = playerRepository.findAll().size();

        // Delete the player
        restPlayerMockMvc.perform(delete("/api/players/{id}", player.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Player> playerList = playerRepository.findAll();
        assertThat(playerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
