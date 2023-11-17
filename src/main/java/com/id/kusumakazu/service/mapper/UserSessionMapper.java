package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.UserSessionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserSession} and its DTO {@link UserSessionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserSessionMapper extends EntityMapper<UserSessionDTO, UserSession> {



    default UserSession fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserSession userSession = new UserSession();
        userSession.setId(id);
        return userSession;
    }
}
