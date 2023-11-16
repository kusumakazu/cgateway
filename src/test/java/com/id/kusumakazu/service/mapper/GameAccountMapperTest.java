package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GameAccountMapperTest {

    private GameAccountMapper gameAccountMapper;

    @BeforeEach
    public void setUp() {
        gameAccountMapper = new GameAccountMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(gameAccountMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(gameAccountMapper.fromId(null)).isNull();
    }
}
