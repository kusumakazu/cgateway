package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.service.StorageInventoryService;
import com.id.kusumakazu.web.rest.errors.BadRequestAlertException;
import com.id.kusumakazu.service.dto.StorageInventoryDTO;

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
 * REST controller for managing {@link com.id.kusumakazu.domain.StorageInventory}.
 */
@RestController
@RequestMapping("/api")
public class StorageInventoryResource {

    private final Logger log = LoggerFactory.getLogger(StorageInventoryResource.class);

    private static final String ENTITY_NAME = "cgatewaysvcStorageInventory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StorageInventoryService storageInventoryService;

    public StorageInventoryResource(StorageInventoryService storageInventoryService) {
        this.storageInventoryService = storageInventoryService;
    }

    /**
     * {@code POST  /storage-inventories} : Create a new storageInventory.
     *
     * @param storageInventoryDTO the storageInventoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new storageInventoryDTO, or with status {@code 400 (Bad Request)} if the storageInventory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/storage-inventories")
    public ResponseEntity<StorageInventoryDTO> createStorageInventory(@RequestBody StorageInventoryDTO storageInventoryDTO) throws URISyntaxException {
        log.debug("REST request to save StorageInventory : {}", storageInventoryDTO);
        if (storageInventoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new storageInventory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StorageInventoryDTO result = storageInventoryService.save(storageInventoryDTO);
        return ResponseEntity.created(new URI("/api/storage-inventories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /storage-inventories} : Updates an existing storageInventory.
     *
     * @param storageInventoryDTO the storageInventoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated storageInventoryDTO,
     * or with status {@code 400 (Bad Request)} if the storageInventoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the storageInventoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/storage-inventories")
    public ResponseEntity<StorageInventoryDTO> updateStorageInventory(@RequestBody StorageInventoryDTO storageInventoryDTO) throws URISyntaxException {
        log.debug("REST request to update StorageInventory : {}", storageInventoryDTO);
        if (storageInventoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StorageInventoryDTO result = storageInventoryService.save(storageInventoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, storageInventoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /storage-inventories} : get all the storageInventories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of storageInventories in body.
     */
    @GetMapping("/storage-inventories")
    public List<StorageInventoryDTO> getAllStorageInventories() {
        log.debug("REST request to get all StorageInventories");
        return storageInventoryService.findAll();
    }

    /**
     * {@code GET  /storage-inventories/:id} : get the "id" storageInventory.
     *
     * @param id the id of the storageInventoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the storageInventoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/storage-inventories/{id}")
    public ResponseEntity<StorageInventoryDTO> getStorageInventory(@PathVariable Long id) {
        log.debug("REST request to get StorageInventory : {}", id);
        Optional<StorageInventoryDTO> storageInventoryDTO = storageInventoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(storageInventoryDTO);
    }

    /**
     * {@code DELETE  /storage-inventories/:id} : delete the "id" storageInventory.
     *
     * @param id the id of the storageInventoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/storage-inventories/{id}")
    public ResponseEntity<Void> deleteStorageInventory(@PathVariable Long id) {
        log.debug("REST request to delete StorageInventory : {}", id);
        storageInventoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
