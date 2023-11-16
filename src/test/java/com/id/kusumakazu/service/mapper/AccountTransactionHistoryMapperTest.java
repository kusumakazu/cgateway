package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTransactionHistoryMapperTest {

    private AccountTransactionHistoryMapper accountTransactionHistoryMapper;

    @BeforeEach
    public void setUp() {
        accountTransactionHistoryMapper = new AccountTransactionHistoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(accountTransactionHistoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountTransactionHistoryMapper.fromId(null)).isNull();
    }
}
