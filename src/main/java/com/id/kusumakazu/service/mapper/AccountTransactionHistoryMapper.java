package com.id.kusumakazu.service.mapper;


import com.id.kusumakazu.domain.*;
import com.id.kusumakazu.service.dto.AccountTransactionHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountTransactionHistory} and its DTO {@link AccountTransactionHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {MasterAccountMapper.class})
public interface AccountTransactionHistoryMapper extends EntityMapper<AccountTransactionHistoryDTO, AccountTransactionHistory> {

    @Mapping(source = "masterAccount.id", target = "masterAccountId")
    AccountTransactionHistoryDTO toDto(AccountTransactionHistory accountTransactionHistory);

    @Mapping(source = "masterAccountId", target = "masterAccount")
    AccountTransactionHistory toEntity(AccountTransactionHistoryDTO accountTransactionHistoryDTO);

    default AccountTransactionHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        AccountTransactionHistory accountTransactionHistory = new AccountTransactionHistory();
        accountTransactionHistory.setId(id);
        return accountTransactionHistory;
    }
}
