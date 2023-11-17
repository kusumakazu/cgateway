package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.service.UserSessionService;
import com.id.kusumakazu.web.rest.errors.BadRequestAlertException;
import com.id.kusumakazu.service.dto.UserSessionDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.id.kusumakazu.domain.UserSession}.
 */
@RestController
@RequestMapping("/api")
public class UserSessionResource {

    private final Logger log = LoggerFactory.getLogger(UserSessionResource.class);

    private static final String ENTITY_NAME = "cgatewaysvcUserSession";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserSessionService userSessionService;

    public UserSessionResource(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    /**
     * {@code POST  /user-sessions} : Create a new userSession.
     *
     * @param userSessionDTO the userSessionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userSessionDTO, or with status {@code 400 (Bad Request)} if the userSession has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-sessions")
    public ResponseEntity<UserSessionDTO> createUserSession(@RequestBody UserSessionDTO userSessionDTO) throws URISyntaxException {
        log.debug("REST request to save UserSession : {}", userSessionDTO);
        if (userSessionDTO.getId() != null) {
            throw new BadRequestAlertException("A new userSession cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserSessionDTO result = userSessionService.save(userSessionDTO);
        return ResponseEntity.created(new URI("/api/user-sessions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-sessions} : Updates an existing userSession.
     *
     * @param userSessionDTO the userSessionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userSessionDTO,
     * or with status {@code 400 (Bad Request)} if the userSessionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userSessionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-sessions")
    public ResponseEntity<UserSessionDTO> updateUserSession(@RequestBody UserSessionDTO userSessionDTO) throws URISyntaxException {
        log.debug("REST request to update UserSession : {}", userSessionDTO);
        if (userSessionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserSessionDTO result = userSessionService.save(userSessionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userSessionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-sessions} : get all the userSessions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userSessions in body.
     */
    @GetMapping("/user-sessions")
    public List<UserSessionDTO> getAllUserSessions() {
        log.debug("REST request to get all UserSessions");
        return userSessionService.findAll();
    }

    /**
     * {@code GET  /user-sessions/:id} : get the "id" userSession.
     *
     * @param id the id of the userSessionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userSessionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-sessions/{id}")
    public ResponseEntity<UserSessionDTO> getUserSession(@PathVariable Long id) {
        log.debug("REST request to get UserSession : {}", id);
        Optional<UserSessionDTO> userSessionDTO = userSessionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userSessionDTO);
    }

    /**
     * {@code DELETE  /user-sessions/:id} : delete the "id" userSession.
     *
     * @param id the id of the userSessionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-sessions/{id}")
    public ResponseEntity<Void> deleteUserSession(@PathVariable Long id) {
        log.debug("REST request to delete UserSession : {}", id);
        userSessionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
