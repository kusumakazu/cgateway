package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.domain.request.CreateItemObjectRequest;
import com.id.kusumakazu.domain.response.CreateItemObjectResponse;
import com.id.kusumakazu.service.ItemObjectService;
import com.id.kusumakazu.web.rest.errors.BadRequestAlertException;
import com.id.kusumakazu.service.dto.ItemObjectDTO;

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
 * REST controller for managing {@link com.id.kusumakazu.domain.ItemObject}.
 */
@RestController
@RequestMapping("/api")
public class ItemObjectResource {

    private final Logger log = LoggerFactory.getLogger(ItemObjectResource.class);

    private static final String ENTITY_NAME = "cgatewaysvcItemObject";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemObjectService itemObjectService;

    public ItemObjectResource(ItemObjectService itemObjectService) {
        this.itemObjectService = itemObjectService;
    }

    /**
     * {@code POST  /item-objects} : Create a new itemObject.
     *
     * @param itemObjectDTO the itemObjectDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemObjectDTO, or with status {@code 400 (Bad Request)} if the itemObject has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-objects")
    public ResponseEntity<ItemObjectDTO> createItemObject(@RequestBody ItemObjectDTO itemObjectDTO) throws URISyntaxException {
        log.debug("REST request to save ItemObject : {}", itemObjectDTO);
        if (itemObjectDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemObject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemObjectDTO result = itemObjectService.save(itemObjectDTO);
        return ResponseEntity.created(new URI("/api/item-objects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-objects} : Updates an existing itemObject.
     *
     * @param itemObjectDTO the itemObjectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemObjectDTO,
     * or with status {@code 400 (Bad Request)} if the itemObjectDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemObjectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-objects")
    public ResponseEntity<ItemObjectDTO> updateItemObject(@RequestBody ItemObjectDTO itemObjectDTO) throws URISyntaxException {
        log.debug("REST request to update ItemObject : {}", itemObjectDTO);
        if (itemObjectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemObjectDTO result = itemObjectService.save(itemObjectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, itemObjectDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /item-objects} : get all the itemObjects.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemObjects in body.
     */
    @GetMapping("/item-objects")
    public List<ItemObjectDTO> getAllItemObjects() {
        log.debug("REST request to get all ItemObjects");
        return itemObjectService.findAll();
    }

    /**
     * {@code GET  /item-objects/:id} : get the "id" itemObject.
     *
     * @param id the id of the itemObjectDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemObjectDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-objects/{id}")
    public ResponseEntity<ItemObjectDTO> getItemObject(@PathVariable Long id) {
        log.debug("REST request to get ItemObject : {}", id);
        Optional<ItemObjectDTO> itemObjectDTO = itemObjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemObjectDTO);
    }

    /**
     * {@code DELETE  /item-objects/:id} : delete the "id" itemObject.
     *
     * @param id the id of the itemObjectDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-objects/{id}")
    public ResponseEntity<Void> deleteItemObject(@PathVariable Long id) {
        log.debug("REST request to delete ItemObject : {}", id);
        itemObjectService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    //new api

    @PostMapping("/item-objects/create-new-item")
    public ResponseEntity<CreateItemObjectResponse> createItemObject(@RequestBody CreateItemObjectRequest request) throws URISyntaxException {
        log.debug("REST request to save ItemObject : {}", request);

        CreateItemObjectResponse result = itemObjectService.createItemObject(request);
        return ResponseEntity.created(new URI("OK"))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, request.toString()))
            .body(result);
    }
}
