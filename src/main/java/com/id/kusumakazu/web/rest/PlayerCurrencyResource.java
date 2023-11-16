package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.service.PlayerCurrencyService;
import com.id.kusumakazu.web.rest.errors.BadRequestAlertException;
import com.id.kusumakazu.service.dto.PlayerCurrencyDTO;

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
 * REST controller for managing {@link com.id.kusumakazu.domain.PlayerCurrency}.
 */
@RestController
@RequestMapping("/api")
public class PlayerCurrencyResource {

    private final Logger log = LoggerFactory.getLogger(PlayerCurrencyResource.class);

    private static final String ENTITY_NAME = "cgatewaysvcPlayerCurrency";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlayerCurrencyService playerCurrencyService;

    public PlayerCurrencyResource(PlayerCurrencyService playerCurrencyService) {
        this.playerCurrencyService = playerCurrencyService;
    }

    /**
     * {@code POST  /player-currencies} : Create a new playerCurrency.
     *
     * @param playerCurrencyDTO the playerCurrencyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new playerCurrencyDTO, or with status {@code 400 (Bad Request)} if the playerCurrency has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/player-currencies")
    public ResponseEntity<PlayerCurrencyDTO> createPlayerCurrency(@RequestBody PlayerCurrencyDTO playerCurrencyDTO) throws URISyntaxException {
        log.debug("REST request to save PlayerCurrency : {}", playerCurrencyDTO);
        if (playerCurrencyDTO.getId() != null) {
            throw new BadRequestAlertException("A new playerCurrency cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlayerCurrencyDTO result = playerCurrencyService.save(playerCurrencyDTO);
        return ResponseEntity.created(new URI("/api/player-currencies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /player-currencies} : Updates an existing playerCurrency.
     *
     * @param playerCurrencyDTO the playerCurrencyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated playerCurrencyDTO,
     * or with status {@code 400 (Bad Request)} if the playerCurrencyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the playerCurrencyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/player-currencies")
    public ResponseEntity<PlayerCurrencyDTO> updatePlayerCurrency(@RequestBody PlayerCurrencyDTO playerCurrencyDTO) throws URISyntaxException {
        log.debug("REST request to update PlayerCurrency : {}", playerCurrencyDTO);
        if (playerCurrencyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlayerCurrencyDTO result = playerCurrencyService.save(playerCurrencyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, playerCurrencyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /player-currencies} : get all the playerCurrencies.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of playerCurrencies in body.
     */
    @GetMapping("/player-currencies")
    public List<PlayerCurrencyDTO> getAllPlayerCurrencies(@RequestParam(required = false) String filter) {
        if ("playerdetl-is-null".equals(filter)) {
            log.debug("REST request to get all PlayerCurrencys where playerDetl is null");
            return playerCurrencyService.findAllWherePlayerDetlIsNull();
        }
        log.debug("REST request to get all PlayerCurrencies");
        return playerCurrencyService.findAll();
    }

    /**
     * {@code GET  /player-currencies/:id} : get the "id" playerCurrency.
     *
     * @param id the id of the playerCurrencyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the playerCurrencyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/player-currencies/{id}")
    public ResponseEntity<PlayerCurrencyDTO> getPlayerCurrency(@PathVariable Long id) {
        log.debug("REST request to get PlayerCurrency : {}", id);
        Optional<PlayerCurrencyDTO> playerCurrencyDTO = playerCurrencyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(playerCurrencyDTO);
    }

    /**
     * {@code DELETE  /player-currencies/:id} : delete the "id" playerCurrency.
     *
     * @param id the id of the playerCurrencyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/player-currencies/{id}")
    public ResponseEntity<Void> deletePlayerCurrency(@PathVariable Long id) {
        log.debug("REST request to delete PlayerCurrency : {}", id);
        playerCurrencyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
