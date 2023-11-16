package com.id.kusumakazu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class AccountTransactionHistoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountTransactionHistory.class);
        AccountTransactionHistory accountTransactionHistory1 = new AccountTransactionHistory();
        accountTransactionHistory1.setId(1L);
        AccountTransactionHistory accountTransactionHistory2 = new AccountTransactionHistory();
        accountTransactionHistory2.setId(accountTransactionHistory1.getId());
        assertThat(accountTransactionHistory1).isEqualTo(accountTransactionHistory2);
        accountTransactionHistory2.setId(2L);
        assertThat(accountTransactionHistory1).isNotEqualTo(accountTransactionHistory2);
        accountTransactionHistory1.setId(null);
        assertThat(accountTransactionHistory1).isNotEqualTo(accountTransactionHistory2);
    }
}
