package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerCurrencyMapperTest {

    private PlayerCurrencyMapper playerCurrencyMapper;

    @BeforeEach
    public void setUp() {
        playerCurrencyMapper = new PlayerCurrencyMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(playerCurrencyMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(playerCurrencyMapper.fromId(null)).isNull();
    }
}
