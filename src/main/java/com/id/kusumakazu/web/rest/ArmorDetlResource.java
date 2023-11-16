package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.service.ArmorDetlService;
import com.id.kusumakazu.web.rest.errors.BadRequestAlertException;
import com.id.kusumakazu.service.dto.ArmorDetlDTO;

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
 * REST controller for managing {@link com.id.kusumakazu.domain.ArmorDetl}.
 */
@RestController
@RequestMapping("/api")
public class ArmorDetlResource {

    private final Logger log = LoggerFactory.getLogger(ArmorDetlResource.class);

    private static final String ENTITY_NAME = "cgatewaysvcArmorDetl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ArmorDetlService armorDetlService;

    public ArmorDetlResource(ArmorDetlService armorDetlService) {
        this.armorDetlService = armorDetlService;
    }

    /**
     * {@code POST  /armor-detls} : Create a new armorDetl.
     *
     * @param armorDetlDTO the armorDetlDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new armorDetlDTO, or with status {@code 400 (Bad Request)} if the armorDetl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/armor-detls")
    public ResponseEntity<ArmorDetlDTO> createArmorDetl(@RequestBody ArmorDetlDTO armorDetlDTO) throws URISyntaxException {
        log.debug("REST request to save ArmorDetl : {}", armorDetlDTO);
        if (armorDetlDTO.getId() != null) {
            throw new BadRequestAlertException("A new armorDetl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ArmorDetlDTO result = armorDetlService.save(armorDetlDTO);
        return ResponseEntity.created(new URI("/api/armor-detls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /armor-detls} : Updates an existing armorDetl.
     *
     * @param armorDetlDTO the armorDetlDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated armorDetlDTO,
     * or with status {@code 400 (Bad Request)} if the armorDetlDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the armorDetlDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/armor-detls")
    public ResponseEntity<ArmorDetlDTO> updateArmorDetl(@RequestBody ArmorDetlDTO armorDetlDTO) throws URISyntaxException {
        log.debug("REST request to update ArmorDetl : {}", armorDetlDTO);
        if (armorDetlDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ArmorDetlDTO result = armorDetlService.save(armorDetlDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, armorDetlDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /armor-detls} : get all the armorDetls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of armorDetls in body.
     */
    @GetMapping("/armor-detls")
    public List<ArmorDetlDTO> getAllArmorDetls() {
        log.debug("REST request to get all ArmorDetls");
        return armorDetlService.findAll();
    }

    /**
     * {@code GET  /armor-detls/:id} : get the "id" armorDetl.
     *
     * @param id the id of the armorDetlDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the armorDetlDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/armor-detls/{id}")
    public ResponseEntity<ArmorDetlDTO> getArmorDetl(@PathVariable Long id) {
        log.debug("REST request to get ArmorDetl : {}", id);
        Optional<ArmorDetlDTO> armorDetlDTO = armorDetlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(armorDetlDTO);
    }

    /**
     * {@code DELETE  /armor-detls/:id} : delete the "id" armorDetl.
     *
     * @param id the id of the armorDetlDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/armor-detls/{id}")
    public ResponseEntity<Void> deleteArmorDetl(@PathVariable Long id) {
        log.debug("REST request to delete ArmorDetl : {}", id);
        armorDetlService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
