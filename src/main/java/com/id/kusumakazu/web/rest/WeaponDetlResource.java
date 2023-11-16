package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.service.WeaponDetlService;
import com.id.kusumakazu.web.rest.errors.BadRequestAlertException;
import com.id.kusumakazu.service.dto.WeaponDetlDTO;

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
 * REST controller for managing {@link com.id.kusumakazu.domain.WeaponDetl}.
 */
@RestController
@RequestMapping("/api")
public class WeaponDetlResource {

    private final Logger log = LoggerFactory.getLogger(WeaponDetlResource.class);

    private static final String ENTITY_NAME = "cgatewaysvcWeaponDetl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WeaponDetlService weaponDetlService;

    public WeaponDetlResource(WeaponDetlService weaponDetlService) {
        this.weaponDetlService = weaponDetlService;
    }

    /**
     * {@code POST  /weapon-detls} : Create a new weaponDetl.
     *
     * @param weaponDetlDTO the weaponDetlDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new weaponDetlDTO, or with status {@code 400 (Bad Request)} if the weaponDetl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/weapon-detls")
    public ResponseEntity<WeaponDetlDTO> createWeaponDetl(@RequestBody WeaponDetlDTO weaponDetlDTO) throws URISyntaxException {
        log.debug("REST request to save WeaponDetl : {}", weaponDetlDTO);
        if (weaponDetlDTO.getId() != null) {
            throw new BadRequestAlertException("A new weaponDetl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WeaponDetlDTO result = weaponDetlService.save(weaponDetlDTO);
        return ResponseEntity.created(new URI("/api/weapon-detls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /weapon-detls} : Updates an existing weaponDetl.
     *
     * @param weaponDetlDTO the weaponDetlDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated weaponDetlDTO,
     * or with status {@code 400 (Bad Request)} if the weaponDetlDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the weaponDetlDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/weapon-detls")
    public ResponseEntity<WeaponDetlDTO> updateWeaponDetl(@RequestBody WeaponDetlDTO weaponDetlDTO) throws URISyntaxException {
        log.debug("REST request to update WeaponDetl : {}", weaponDetlDTO);
        if (weaponDetlDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WeaponDetlDTO result = weaponDetlService.save(weaponDetlDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, weaponDetlDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /weapon-detls} : get all the weaponDetls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of weaponDetls in body.
     */
    @GetMapping("/weapon-detls")
    public List<WeaponDetlDTO> getAllWeaponDetls() {
        log.debug("REST request to get all WeaponDetls");
        return weaponDetlService.findAll();
    }

    /**
     * {@code GET  /weapon-detls/:id} : get the "id" weaponDetl.
     *
     * @param id the id of the weaponDetlDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the weaponDetlDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/weapon-detls/{id}")
    public ResponseEntity<WeaponDetlDTO> getWeaponDetl(@PathVariable Long id) {
        log.debug("REST request to get WeaponDetl : {}", id);
        Optional<WeaponDetlDTO> weaponDetlDTO = weaponDetlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(weaponDetlDTO);
    }

    /**
     * {@code DELETE  /weapon-detls/:id} : delete the "id" weaponDetl.
     *
     * @param id the id of the weaponDetlDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/weapon-detls/{id}")
    public ResponseEntity<Void> deleteWeaponDetl(@PathVariable Long id) {
        log.debug("REST request to delete WeaponDetl : {}", id);
        weaponDetlService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
