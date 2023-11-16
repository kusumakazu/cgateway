package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.service.WeaponService;
import com.id.kusumakazu.web.rest.errors.BadRequestAlertException;
import com.id.kusumakazu.service.dto.WeaponDTO;

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
 * REST controller for managing {@link com.id.kusumakazu.domain.Weapon}.
 */
@RestController
@RequestMapping("/api")
public class WeaponResource {

    private final Logger log = LoggerFactory.getLogger(WeaponResource.class);

    private static final String ENTITY_NAME = "cgatewaysvcWeapon";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WeaponService weaponService;

    public WeaponResource(WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    /**
     * {@code POST  /weapons} : Create a new weapon.
     *
     * @param weaponDTO the weaponDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new weaponDTO, or with status {@code 400 (Bad Request)} if the weapon has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/weapons")
    public ResponseEntity<WeaponDTO> createWeapon(@RequestBody WeaponDTO weaponDTO) throws URISyntaxException {
        log.debug("REST request to save Weapon : {}", weaponDTO);
        if (weaponDTO.getId() != null) {
            throw new BadRequestAlertException("A new weapon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WeaponDTO result = weaponService.save(weaponDTO);
        return ResponseEntity.created(new URI("/api/weapons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /weapons} : Updates an existing weapon.
     *
     * @param weaponDTO the weaponDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated weaponDTO,
     * or with status {@code 400 (Bad Request)} if the weaponDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the weaponDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/weapons")
    public ResponseEntity<WeaponDTO> updateWeapon(@RequestBody WeaponDTO weaponDTO) throws URISyntaxException {
        log.debug("REST request to update Weapon : {}", weaponDTO);
        if (weaponDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WeaponDTO result = weaponService.save(weaponDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, weaponDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /weapons} : get all the weapons.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of weapons in body.
     */
    @GetMapping("/weapons")
    public List<WeaponDTO> getAllWeapons() {
        log.debug("REST request to get all Weapons");
        return weaponService.findAll();
    }

    /**
     * {@code GET  /weapons/:id} : get the "id" weapon.
     *
     * @param id the id of the weaponDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the weaponDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/weapons/{id}")
    public ResponseEntity<WeaponDTO> getWeapon(@PathVariable Long id) {
        log.debug("REST request to get Weapon : {}", id);
        Optional<WeaponDTO> weaponDTO = weaponService.findOne(id);
        return ResponseUtil.wrapOrNotFound(weaponDTO);
    }

    /**
     * {@code DELETE  /weapons/:id} : delete the "id" weapon.
     *
     * @param id the id of the weaponDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/weapons/{id}")
    public ResponseEntity<Void> deleteWeapon(@PathVariable Long id) {
        log.debug("REST request to delete Weapon : {}", id);
        weaponService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
