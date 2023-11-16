package com.id.kusumakazu.service.impl;

import com.id.kusumakazu.service.MasterAccountService;
import com.id.kusumakazu.domain.MasterAccount;
import com.id.kusumakazu.repository.MasterAccountRepository;
import com.id.kusumakazu.service.dto.MasterAccountDTO;
import com.id.kusumakazu.service.mapper.MasterAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MasterAccount}.
 */
@Service
@Transactional
public class MasterAccountServiceImpl implements MasterAccountService {

    private final Logger log = LoggerFactory.getLogger(MasterAccountServiceImpl.class);

    private final MasterAccountRepository masterAccountRepository;

    private final MasterAccountMapper masterAccountMapper;

    public MasterAccountServiceImpl(MasterAccountRepository masterAccountRepository, MasterAccountMapper masterAccountMapper) {
        this.masterAccountRepository = masterAccountRepository;
        this.masterAccountMapper = masterAccountMapper;
    }

    @Override
    public MasterAccountDTO save(MasterAccountDTO masterAccountDTO) {
        log.debug("Request to save MasterAccount : {}", masterAccountDTO);
        MasterAccount masterAccount = masterAccountMapper.toEntity(masterAccountDTO);
        masterAccount = masterAccountRepository.save(masterAccount);
        return masterAccountMapper.toDto(masterAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MasterAccountDTO> findAll() {
        log.debug("Request to get all MasterAccounts");
        return masterAccountRepository.findAll().stream()
            .map(masterAccountMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MasterAccountDTO> findOne(Long id) {
        log.debug("Request to get MasterAccount : {}", id);
        return masterAccountRepository.findById(id)
            .map(masterAccountMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MasterAccount : {}", id);
        masterAccountRepository.deleteById(id);
    }
}
