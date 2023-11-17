package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.CgatewaysvcApp;
import com.id.kusumakazu.domain.UserSession;
import com.id.kusumakazu.repository.UserSessionRepository;
import com.id.kusumakazu.service.UserSessionService;
import com.id.kusumakazu.service.dto.UserSessionDTO;
import com.id.kusumakazu.service.mapper.UserSessionMapper;

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
 * Integration tests for the {@link UserSessionResource} REST controller.
 */
@SpringBootTest(classes = CgatewaysvcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UserSessionResourceIT {

    private static final String DEFAULT_SESSION_ID = "AAAAAAAAAA";
    private static final String UPDATED_SESSION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CONN_ID = "AAAAAAAAAA";
    private static final String UPDATED_CONN_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_STATUS_CONN = false;
    private static final Boolean UPDATED_STATUS_CONN = true;

    private static final Long DEFAULT_PLAYER_ID = 1L;
    private static final Long UPDATED_PLAYER_ID = 2L;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private UserSessionMapper userSessionMapper;

    @Autowired
    private UserSessionService userSessionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserSessionMockMvc;

    private UserSession userSession;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserSession createEntity(EntityManager em) {
        UserSession userSession = new UserSession()
            .sessionId(DEFAULT_SESSION_ID)
            .email(DEFAULT_EMAIL)
            .connId(DEFAULT_CONN_ID)
            .statusConn(DEFAULT_STATUS_CONN)
            .playerId(DEFAULT_PLAYER_ID);
        return userSession;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserSession createUpdatedEntity(EntityManager em) {
        UserSession userSession = new UserSession()
            .sessionId(UPDATED_SESSION_ID)
            .email(UPDATED_EMAIL)
            .connId(UPDATED_CONN_ID)
            .statusConn(UPDATED_STATUS_CONN)
            .playerId(UPDATED_PLAYER_ID);
        return userSession;
    }

    @BeforeEach
    public void initTest() {
        userSession = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserSession() throws Exception {
        int databaseSizeBeforeCreate = userSessionRepository.findAll().size();
        // Create the UserSession
        UserSessionDTO userSessionDTO = userSessionMapper.toDto(userSession);
        restUserSessionMockMvc.perform(post("/api/user-sessions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userSessionDTO)))
            .andExpect(status().isCreated());

        // Validate the UserSession in the database
        List<UserSession> userSessionList = userSessionRepository.findAll();
        assertThat(userSessionList).hasSize(databaseSizeBeforeCreate + 1);
        UserSession testUserSession = userSessionList.get(userSessionList.size() - 1);
        assertThat(testUserSession.getSessionId()).isEqualTo(DEFAULT_SESSION_ID);
        assertThat(testUserSession.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUserSession.getConnId()).isEqualTo(DEFAULT_CONN_ID);
        assertThat(testUserSession.isStatusConn()).isEqualTo(DEFAULT_STATUS_CONN);
        assertThat(testUserSession.getPlayerId()).isEqualTo(DEFAULT_PLAYER_ID);
    }

    @Test
    @Transactional
    public void createUserSessionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userSessionRepository.findAll().size();

        // Create the UserSession with an existing ID
        userSession.setId(1L);
        UserSessionDTO userSessionDTO = userSessionMapper.toDto(userSession);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserSessionMockMvc.perform(post("/api/user-sessions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userSessionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserSession in the database
        List<UserSession> userSessionList = userSessionRepository.findAll();
        assertThat(userSessionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserSessions() throws Exception {
        // Initialize the database
        userSessionRepository.saveAndFlush(userSession);

        // Get all the userSessionList
        restUserSessionMockMvc.perform(get("/api/user-sessions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userSession.getId().intValue())))
            .andExpect(jsonPath("$.[*].sessionId").value(hasItem(DEFAULT_SESSION_ID)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].connId").value(hasItem(DEFAULT_CONN_ID)))
            .andExpect(jsonPath("$.[*].statusConn").value(hasItem(DEFAULT_STATUS_CONN.booleanValue())))
            .andExpect(jsonPath("$.[*].playerId").value(hasItem(DEFAULT_PLAYER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getUserSession() throws Exception {
        // Initialize the database
        userSessionRepository.saveAndFlush(userSession);

        // Get the userSession
        restUserSessionMockMvc.perform(get("/api/user-sessions/{id}", userSession.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userSession.getId().intValue()))
            .andExpect(jsonPath("$.sessionId").value(DEFAULT_SESSION_ID))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.connId").value(DEFAULT_CONN_ID))
            .andExpect(jsonPath("$.statusConn").value(DEFAULT_STATUS_CONN.booleanValue()))
            .andExpect(jsonPath("$.playerId").value(DEFAULT_PLAYER_ID.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingUserSession() throws Exception {
        // Get the userSession
        restUserSessionMockMvc.perform(get("/api/user-sessions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserSession() throws Exception {
        // Initialize the database
        userSessionRepository.saveAndFlush(userSession);

        int databaseSizeBeforeUpdate = userSessionRepository.findAll().size();

        // Update the userSession
        UserSession updatedUserSession = userSessionRepository.findById(userSession.getId()).get();
        // Disconnect from session so that the updates on updatedUserSession are not directly saved in db
        em.detach(updatedUserSession);
        updatedUserSession
            .sessionId(UPDATED_SESSION_ID)
            .email(UPDATED_EMAIL)
            .connId(UPDATED_CONN_ID)
            .statusConn(UPDATED_STATUS_CONN)
            .playerId(UPDATED_PLAYER_ID);
        UserSessionDTO userSessionDTO = userSessionMapper.toDto(updatedUserSession);

        restUserSessionMockMvc.perform(put("/api/user-sessions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userSessionDTO)))
            .andExpect(status().isOk());

        // Validate the UserSession in the database
        List<UserSession> userSessionList = userSessionRepository.findAll();
        assertThat(userSessionList).hasSize(databaseSizeBeforeUpdate);
        UserSession testUserSession = userSessionList.get(userSessionList.size() - 1);
        assertThat(testUserSession.getSessionId()).isEqualTo(UPDATED_SESSION_ID);
        assertThat(testUserSession.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserSession.getConnId()).isEqualTo(UPDATED_CONN_ID);
        assertThat(testUserSession.isStatusConn()).isEqualTo(UPDATED_STATUS_CONN);
        assertThat(testUserSession.getPlayerId()).isEqualTo(UPDATED_PLAYER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingUserSession() throws Exception {
        int databaseSizeBeforeUpdate = userSessionRepository.findAll().size();

        // Create the UserSession
        UserSessionDTO userSessionDTO = userSessionMapper.toDto(userSession);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserSessionMockMvc.perform(put("/api/user-sessions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userSessionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserSession in the database
        List<UserSession> userSessionList = userSessionRepository.findAll();
        assertThat(userSessionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserSession() throws Exception {
        // Initialize the database
        userSessionRepository.saveAndFlush(userSession);

        int databaseSizeBeforeDelete = userSessionRepository.findAll().size();

        // Delete the userSession
        restUserSessionMockMvc.perform(delete("/api/user-sessions/{id}", userSession.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserSession> userSessionList = userSessionRepository.findAll();
        assertThat(userSessionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
