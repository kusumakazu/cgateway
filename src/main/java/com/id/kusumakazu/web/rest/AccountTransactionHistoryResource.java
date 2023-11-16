package com.id.kusumakazu.web.rest;

import com.id.kusumakazu.service.AccountTransactionHistoryService;
import com.id.kusumakazu.web.rest.errors.BadRequestAlertException;
import com.id.kusumakazu.service.dto.AccountTransactionHistoryDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.id.kusumakazu.domain.AccountTransactionHistory}.
 */
@RestController
@RequestMapping("/api")
public class AccountTransactionHistoryResource {

    private final Logger log = LoggerFactory.getLogger(AccountTransactionHistoryResource.class);

    private static final String ENTITY_NAME = "cgatewaysvcAccountTransactionHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountTransactionHistoryService accountTransactionHistoryService;

    public AccountTransactionHistoryResource(AccountTransactionHistoryService accountTransactionHistoryService) {
        this.accountTransactionHistoryService = accountTransactionHistoryService;
    }

    /**
     * {@code POST  /account-transaction-histories} : Create a new accountTransactionHistory.
     *
     * @param accountTransactionHistoryDTO the accountTransactionHistoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountTransactionHistoryDTO, or with status {@code 400 (Bad Request)} if the accountTransactionHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-transaction-histories")
    public ResponseEntity<AccountTransactionHistoryDTO> createAccountTransactionHistory(@RequestBody AccountTransactionHistoryDTO accountTransactionHistoryDTO) throws URISyntaxException {
        log.debug("REST request to save AccountTransactionHistory : {}", accountTransactionHistoryDTO);
        if (accountTransactionHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountTransactionHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountTransactionHistoryDTO result = accountTransactionHistoryService.save(accountTransactionHistoryDTO);
        return ResponseEntity.created(new URI("/api/account-transaction-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-transaction-histories} : Updates an existing accountTransactionHistory.
     *
     * @param accountTransactionHistoryDTO the accountTransactionHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountTransactionHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the accountTransactionHistoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountTransactionHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-transaction-histories")
    public ResponseEntity<AccountTransactionHistoryDTO> updateAccountTransactionHistory(@RequestBody AccountTransactionHistoryDTO accountTransactionHistoryDTO) throws URISyntaxException {
        log.debug("REST request to update AccountTransactionHistory : {}", accountTransactionHistoryDTO);
        if (accountTransactionHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountTransactionHistoryDTO result = accountTransactionHistoryService.save(accountTransactionHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountTransactionHistoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-transaction-histories} : get all the accountTransactionHistories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountTransactionHistories in body.
     */
    @GetMapping("/account-transaction-histories")
    public ResponseEntity<List<AccountTransactionHistoryDTO>> getAllAccountTransactionHistories(Pageable pageable) {
        log.debug("REST request to get a page of AccountTransactionHistories");
        Page<AccountTransactionHistoryDTO> page = accountTransactionHistoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /account-transaction-histories/:id} : get the "id" accountTransactionHistory.
     *
     * @param id the id of the accountTransactionHistoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountTransactionHistoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-transaction-histories/{id}")
    public ResponseEntity<AccountTransactionHistoryDTO> getAccountTransactionHistory(@PathVariable Long id) {
        log.debug("REST request to get AccountTransactionHistory : {}", id);
        Optional<AccountTransactionHistoryDTO> accountTransactionHistoryDTO = accountTransactionHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountTransactionHistoryDTO);
    }

    /**
     * {@code DELETE  /account-transaction-histories/:id} : delete the "id" accountTransactionHistory.
     *
     * @param id the id of the accountTransactionHistoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-transaction-histories/{id}")
    public ResponseEntity<Void> deleteAccountTransactionHistory(@PathVariable Long id) {
        log.debug("REST request to delete AccountTransactionHistory : {}", id);
        accountTransactionHistoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
