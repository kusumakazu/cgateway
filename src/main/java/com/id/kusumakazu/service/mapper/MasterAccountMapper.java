package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.MasterAccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MasterAccount} and its DTO {@link MasterAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MasterAccountMapper extends EntityMapper<MasterAccountDTO, MasterAccount> {


    @Mapping(target = "gameAccounts", ignore = true)
    @Mapping(target = "removeGameAccount", ignore = true)
    @Mapping(target = "accountTransactionHistories", ignore = true)
    @Mapping(target = "removeAccountTransactionHistory", ignore = true)
    MasterAccount toEntity(MasterAccountDTO masterAccountDTO);

    default MasterAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        MasterAccount masterAccount = new MasterAccount();
        masterAccount.setId(id);
        return masterAccount;
    }
}
