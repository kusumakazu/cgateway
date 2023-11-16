package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.service.GameAccountService;
import com.id.kusumakazu.web.rest.errors.BadRequestAlertException;
import com.id.kusumakazu.service.dto.GameAccountDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.id.kusumakazu.domain.GameAccount}.
 */
@RestController
@RequestMapping("/api")
public class GameAccountResource {

    private final Logger log = LoggerFactory.getLogger(GameAccountResource.class);

    private static final String ENTITY_NAME = "cgatewaysvcGameAccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GameAccountService gameAccountService;

    public GameAccountResource(GameAccountService gameAccountService) {
        this.gameAccountService = gameAccountService;
    }

    /**
     * {@code POST  /game-accounts} : Create a new gameAccount.
     *
     * @param gameAccountDTO the gameAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gameAccountDTO, or with status {@code 400 (Bad Request)} if the gameAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/game-accounts")
    public ResponseEntity<GameAccountDTO> createGameAccount(@Valid @RequestBody GameAccountDTO gameAccountDTO) throws URISyntaxException {
        log.debug("REST request to save GameAccount : {}", gameAccountDTO);
        if (gameAccountDTO.getId() != null) {
            throw new BadRequestAlertException("A new gameAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GameAccountDTO result = gameAccountService.save(gameAccountDTO);
        return ResponseEntity.created(new URI("/api/game-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /game-accounts} : Updates an existing gameAccount.
     *
     * @param gameAccountDTO the gameAccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gameAccountDTO,
     * or with status {@code 400 (Bad Request)} if the gameAccountDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gameAccountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/game-accounts")
    public ResponseEntity<GameAccountDTO> updateGameAccount(@Valid @RequestBody GameAccountDTO gameAccountDTO) throws URISyntaxException {
        log.debug("REST request to update GameAccount : {}", gameAccountDTO);
        if (gameAccountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GameAccountDTO result = gameAccountService.save(gameAccountDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gameAccountDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /game-accounts} : get all the gameAccounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gameAccounts in body.
     */
    @GetMapping("/game-accounts")
    public List<GameAccountDTO> getAllGameAccounts() {
        log.debug("REST request to get all GameAccounts");
        return gameAccountService.findAll();
    }

    /**
     * {@code GET  /game-accounts/:id} : get the "id" gameAccount.
     *
     * @param id the id of the gameAccountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gameAccountDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/game-accounts/{id}")
    public ResponseEntity<GameAccountDTO> getGameAccount(@PathVariable Long id) {
        log.debug("REST request to get GameAccount : {}", id);
        Optional<GameAccountDTO> gameAccountDTO = gameAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gameAccountDTO);
    }

    /**
     * {@code DELETE  /game-accounts/:id} : delete the "id" gameAccount.
     *
     * @param id the id of the gameAccountDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/game-accounts/{id}")
    public ResponseEntity<Void> deleteGameAccount(@PathVariable Long id) {
        log.debug("REST request to delete GameAccount : {}", id);
        gameAccountService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
