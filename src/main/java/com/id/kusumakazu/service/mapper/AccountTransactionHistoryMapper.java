package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.AccountTransactionHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountTransactionHistory} and its DTO {@link AccountTransactionHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AccountTransactionHistoryMapper extends EntityMapper<AccountTransactionHistoryDTO, AccountTransactionHistory> {



    default AccountTransactionHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountTransactionHistory accountTransactionHistory = new AccountTransactionHistory();
        accountTransactionHistory.setId(id);
        return accountTransactionHistory;
    }
}
