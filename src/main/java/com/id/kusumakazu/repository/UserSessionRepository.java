package com.id.kusumakazu.repository;

import com.id.kusumakazu.domain.UserSession;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserSession entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {
}
