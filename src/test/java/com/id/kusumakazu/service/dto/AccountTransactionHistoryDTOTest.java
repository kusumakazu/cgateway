package com.id.kusumakazu.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class AccountTransactionHistoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountTransactionHistoryDTO.class);
        AccountTransactionHistoryDTO accountTransactionHistoryDTO1 = new AccountTransactionHistoryDTO();
        accountTransactionHistoryDTO1.setId(1L);
        AccountTransactionHistoryDTO accountTransactionHistoryDTO2 = new AccountTransactionHistoryDTO();
        assertThat(accountTransactionHistoryDTO1).isNotEqualTo(accountTransactionHistoryDTO2);
        accountTransactionHistoryDTO2.setId(accountTransactionHistoryDTO1.getId());
        assertThat(accountTransactionHistoryDTO1).isEqualTo(accountTransactionHistoryDTO2);
        accountTransactionHistoryDTO2.setId(2L);
        assertThat(accountTransactionHistoryDTO1).isNotEqualTo(accountTransactionHistoryDTO2);
        accountTransactionHistoryDTO1.setId(null);
        assertThat(accountTransactionHistoryDTO1).isNotEqualTo(accountTransactionHistoryDTO2);
    }
}
