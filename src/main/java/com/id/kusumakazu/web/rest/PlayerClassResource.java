package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.service.PlayerClassService;
import com.id.kusumakazu.web.rest.errors.BadRequestAlertException;
import com.id.kusumakazu.service.dto.PlayerClassDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.id.kusumakazu.domain.PlayerClass}.
 */
@RestController
@RequestMapping("/api")
public class PlayerClassResource {

    private final Logger log = LoggerFactory.getLogger(PlayerClassResource.class);

    private static final String ENTITY_NAME = "cgatewaysvcPlayerClass";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlayerClassService playerClassService;

    public PlayerClassResource(PlayerClassService playerClassService) {
        this.playerClassService = playerClassService;
    }

    /**
     * {@code POST  /player-classes} : Create a new playerClass.
     *
     * @param playerClassDTO the playerClassDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new playerClassDTO, or with status {@code 400 (Bad Request)} if the playerClass has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/player-classes")
    public ResponseEntity<PlayerClassDTO> createPlayerClass(@RequestBody PlayerClassDTO playerClassDTO) throws URISyntaxException {
        log.debug("REST request to save PlayerClass : {}", playerClassDTO);
        if (playerClassDTO.getId() != null) {
            throw new BadRequestAlertException("A new playerClass cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlayerClassDTO result = playerClassService.save(playerClassDTO);
        return ResponseEntity.created(new URI("/api/player-classes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /player-classes} : Updates an existing playerClass.
     *
     * @param playerClassDTO the playerClassDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated playerClassDTO,
     * or with status {@code 400 (Bad Request)} if the playerClassDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the playerClassDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/player-classes")
    public ResponseEntity<PlayerClassDTO> updatePlayerClass(@RequestBody PlayerClassDTO playerClassDTO) throws URISyntaxException {
        log.debug("REST request to update PlayerClass : {}", playerClassDTO);
        if (playerClassDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlayerClassDTO result = playerClassService.save(playerClassDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, playerClassDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /player-classes} : get all the playerClasses.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of playerClasses in body.
     */
    @GetMapping("/player-classes")
    public List<PlayerClassDTO> getAllPlayerClasses(@RequestParam(required = false) String filter) {
        if ("player-is-null".equals(filter)) {
            log.debug("REST request to get all PlayerClasss where player is null");
            return playerClassService.findAllWherePlayerIsNull();
        }
        log.debug("REST request to get all PlayerClasses");
        return playerClassService.findAll();
    }

    /**
     * {@code GET  /player-classes/:id} : get the "id" playerClass.
     *
     * @param id the id of the playerClassDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the playerClassDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/player-classes/{id}")
    public ResponseEntity<PlayerClassDTO> getPlayerClass(@PathVariable Long id) {
        log.debug("REST request to get PlayerClass : {}", id);
        Optional<PlayerClassDTO> playerClassDTO = playerClassService.findOne(id);
        return ResponseUtil.wrapOrNotFound(playerClassDTO);
    }

    /**
     * {@code DELETE  /player-classes/:id} : delete the "id" playerClass.
     *
     * @param id the id of the playerClassDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/player-classes/{id}")
    public ResponseEntity<Void> deletePlayerClass(@PathVariable Long id) {
        log.debug("REST request to delete PlayerClass : {}", id);
        playerClassService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
