package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.service.PlayerDetlService;
import com.id.kusumakazu.web.rest.errors.BadRequestAlertException;
import com.id.kusumakazu.service.dto.PlayerDetlDTO;

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
 * REST controller for managing {@link com.id.kusumakazu.domain.PlayerDetl}.
 */
@RestController
@RequestMapping("/api")
public class PlayerDetlResource {

    private final Logger log = LoggerFactory.getLogger(PlayerDetlResource.class);

    private static final String ENTITY_NAME = "cgatewaysvcPlayerDetl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlayerDetlService playerDetlService;

    public PlayerDetlResource(PlayerDetlService playerDetlService) {
        this.playerDetlService = playerDetlService;
    }

    /**
     * {@code POST  /player-detls} : Create a new playerDetl.
     *
     * @param playerDetlDTO the playerDetlDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new playerDetlDTO, or with status {@code 400 (Bad Request)} if the playerDetl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/player-detls")
    public ResponseEntity<PlayerDetlDTO> createPlayerDetl(@RequestBody PlayerDetlDTO playerDetlDTO) throws URISyntaxException {
        log.debug("REST request to save PlayerDetl : {}", playerDetlDTO);
        if (playerDetlDTO.getId() != null) {
            throw new BadRequestAlertException("A new playerDetl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlayerDetlDTO result = playerDetlService.save(playerDetlDTO);
        return ResponseEntity.created(new URI("/api/player-detls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /player-detls} : Updates an existing playerDetl.
     *
     * @param playerDetlDTO the playerDetlDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated playerDetlDTO,
     * or with status {@code 400 (Bad Request)} if the playerDetlDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the playerDetlDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/player-detls")
    public ResponseEntity<PlayerDetlDTO> updatePlayerDetl(@RequestBody PlayerDetlDTO playerDetlDTO) throws URISyntaxException {
        log.debug("REST request to update PlayerDetl : {}", playerDetlDTO);
        if (playerDetlDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlayerDetlDTO result = playerDetlService.save(playerDetlDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, playerDetlDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /player-detls} : get all the playerDetls.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of playerDetls in body.
     */
    @GetMapping("/player-detls")
    public List<PlayerDetlDTO> getAllPlayerDetls(@RequestParam(required = false) String filter) {
        if ("player-is-null".equals(filter)) {
            log.debug("REST request to get all PlayerDetls where player is null");
            return playerDetlService.findAllWherePlayerIsNull();
        }
        log.debug("REST request to get all PlayerDetls");
        return playerDetlService.findAll();
    }

    /**
     * {@code GET  /player-detls/:id} : get the "id" playerDetl.
     *
     * @param id the id of the playerDetlDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the playerDetlDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/player-detls/{id}")
    public ResponseEntity<PlayerDetlDTO> getPlayerDetl(@PathVariable Long id) {
        log.debug("REST request to get PlayerDetl : {}", id);
        Optional<PlayerDetlDTO> playerDetlDTO = playerDetlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(playerDetlDTO);
    }

    /**
     * {@code DELETE  /player-detls/:id} : delete the "id" playerDetl.
     *
     * @param id the id of the playerDetlDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/player-detls/{id}")
    public ResponseEntity<Void> deletePlayerDetl(@PathVariable Long id) {
        log.debug("REST request to delete PlayerDetl : {}", id);
        playerDetlService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
