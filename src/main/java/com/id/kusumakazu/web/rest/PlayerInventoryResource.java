package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.service.PlayerInventoryService;
import com.id.kusumakazu.web.rest.errors.BadRequestAlertException;
import com.id.kusumakazu.service.dto.PlayerInventoryDTO;

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
 * REST controller for managing {@link com.id.kusumakazu.domain.PlayerInventory}.
 */
@RestController
@RequestMapping("/api")
public class PlayerInventoryResource {

    private final Logger log = LoggerFactory.getLogger(PlayerInventoryResource.class);

    private static final String ENTITY_NAME = "cgatewaysvcPlayerInventory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlayerInventoryService playerInventoryService;

    public PlayerInventoryResource(PlayerInventoryService playerInventoryService) {
        this.playerInventoryService = playerInventoryService;
    }

    /**
     * {@code POST  /player-inventories} : Create a new playerInventory.
     *
     * @param playerInventoryDTO the playerInventoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new playerInventoryDTO, or with status {@code 400 (Bad Request)} if the playerInventory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/player-inventories")
    public ResponseEntity<PlayerInventoryDTO> createPlayerInventory(@RequestBody PlayerInventoryDTO playerInventoryDTO) throws URISyntaxException {
        log.debug("REST request to save PlayerInventory : {}", playerInventoryDTO);
        if (playerInventoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new playerInventory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlayerInventoryDTO result = playerInventoryService.save(playerInventoryDTO);
        return ResponseEntity.created(new URI("/api/player-inventories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /player-inventories} : Updates an existing playerInventory.
     *
     * @param playerInventoryDTO the playerInventoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated playerInventoryDTO,
     * or with status {@code 400 (Bad Request)} if the playerInventoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the playerInventoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/player-inventories")
    public ResponseEntity<PlayerInventoryDTO> updatePlayerInventory(@RequestBody PlayerInventoryDTO playerInventoryDTO) throws URISyntaxException {
        log.debug("REST request to update PlayerInventory : {}", playerInventoryDTO);
        if (playerInventoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlayerInventoryDTO result = playerInventoryService.save(playerInventoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, playerInventoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /player-inventories} : get all the playerInventories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of playerInventories in body.
     */
    @GetMapping("/player-inventories")
    public List<PlayerInventoryDTO> getAllPlayerInventories() {
        log.debug("REST request to get all PlayerInventories");
        return playerInventoryService.findAll();
    }

    /**
     * {@code GET  /player-inventories/:id} : get the "id" playerInventory.
     *
     * @param id the id of the playerInventoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the playerInventoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/player-inventories/{id}")
    public ResponseEntity<PlayerInventoryDTO> getPlayerInventory(@PathVariable Long id) {
        log.debug("REST request to get PlayerInventory : {}", id);
        Optional<PlayerInventoryDTO> playerInventoryDTO = playerInventoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(playerInventoryDTO);
    }

    /**
     * {@code DELETE  /player-inventories/:id} : delete the "id" playerInventory.
     *
     * @param id the id of the playerInventoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/player-inventories/{id}")
    public ResponseEntity<Void> deletePlayerInventory(@PathVariable Long id) {
        log.debug("REST request to delete PlayerInventory : {}", id);
        playerInventoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
