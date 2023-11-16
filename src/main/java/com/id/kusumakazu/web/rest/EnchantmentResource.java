package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.service.EnchantmentService;
import com.id.kusumakazu.web.rest.errors.BadRequestAlertException;
import com.id.kusumakazu.service.dto.EnchantmentDTO;

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
 * REST controller for managing {@link com.id.kusumakazu.domain.Enchantment}.
 */
@RestController
@RequestMapping("/api")
public class EnchantmentResource {

    private final Logger log = LoggerFactory.getLogger(EnchantmentResource.class);

    private static final String ENTITY_NAME = "cgatewaysvcEnchantment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EnchantmentService enchantmentService;

    public EnchantmentResource(EnchantmentService enchantmentService) {
        this.enchantmentService = enchantmentService;
    }

    /**
     * {@code POST  /enchantments} : Create a new enchantment.
     *
     * @param enchantmentDTO the enchantmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new enchantmentDTO, or with status {@code 400 (Bad Request)} if the enchantment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/enchantments")
    public ResponseEntity<EnchantmentDTO> createEnchantment(@RequestBody EnchantmentDTO enchantmentDTO) throws URISyntaxException {
        log.debug("REST request to save Enchantment : {}", enchantmentDTO);
        if (enchantmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new enchantment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EnchantmentDTO result = enchantmentService.save(enchantmentDTO);
        return ResponseEntity.created(new URI("/api/enchantments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /enchantments} : Updates an existing enchantment.
     *
     * @param enchantmentDTO the enchantmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated enchantmentDTO,
     * or with status {@code 400 (Bad Request)} if the enchantmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the enchantmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/enchantments")
    public ResponseEntity<EnchantmentDTO> updateEnchantment(@RequestBody EnchantmentDTO enchantmentDTO) throws URISyntaxException {
        log.debug("REST request to update Enchantment : {}", enchantmentDTO);
        if (enchantmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EnchantmentDTO result = enchantmentService.save(enchantmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, enchantmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /enchantments} : get all the enchantments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enchantments in body.
     */
    @GetMapping("/enchantments")
    public List<EnchantmentDTO> getAllEnchantments() {
        log.debug("REST request to get all Enchantments");
        return enchantmentService.findAll();
    }

    /**
     * {@code GET  /enchantments/:id} : get the "id" enchantment.
     *
     * @param id the id of the enchantmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the enchantmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/enchantments/{id}")
    public ResponseEntity<EnchantmentDTO> getEnchantment(@PathVariable Long id) {
        log.debug("REST request to get Enchantment : {}", id);
        Optional<EnchantmentDTO> enchantmentDTO = enchantmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(enchantmentDTO);
    }

    /**
     * {@code DELETE  /enchantments/:id} : delete the "id" enchantment.
     *
     * @param id the id of the enchantmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/enchantments/{id}")
    public ResponseEntity<Void> deleteEnchantment(@PathVariable Long id) {
        log.debug("REST request to delete Enchantment : {}", id);
        enchantmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
