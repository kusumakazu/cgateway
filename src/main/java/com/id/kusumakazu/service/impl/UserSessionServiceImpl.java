package com.id.kusumakazu.service.impl;

import com.id.kusumakazu.service.UserSessionService;
import com.id.kusumakazu.domain.UserSession;
import com.id.kusumakazu.repository.UserSessionRepository;
import com.id.kusumakazu.service.dto.UserSessionDTO;
import com.id.kusumakazu.service.mapper.UserSessionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link UserSession}.
 */
@Service
@Transactional
public class UserSessionServiceImpl implements UserSessionService {

    private final Logger log = LoggerFactory.getLogger(UserSessionServiceImpl.class);

    private final UserSessionRepository userSessionRepository;

    private final UserSessionMapper userSessionMapper;

    public UserSessionServiceImpl(UserSessionRepository userSessionRepository, UserSessionMapper userSessionMapper) {
        this.userSessionRepository = userSessionRepository;
        this.userSessionMapper = userSessionMapper;
    }

    @Override
    public UserSessionDTO save(UserSessionDTO userSessionDTO) {
        log.debug("Request to save UserSession : {}", userSessionDTO);
        UserSession userSession = userSessionMapper.toEntity(userSessionDTO);
        userSession = userSessionRepository.save(userSession);
        return userSessionMapper.toDto(userSession);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserSessionDTO> findAll() {
        log.debug("Request to get all UserSessions");
        return userSessionRepository.findAll().stream()
            .map(userSessionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UserSessionDTO> findOne(Long id) {
        log.debug("Request to get UserSession : {}", id);
        return userSessionRepository.findById(id)
            .map(userSessionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserSession : {}", id);
        userSessionRepository.deleteById(id);
    }
}
