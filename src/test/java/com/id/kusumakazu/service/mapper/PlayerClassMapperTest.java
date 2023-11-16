package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerClassMapperTest {

    private PlayerClassMapper playerClassMapper;

    @BeforeEach
    public void setUp() {
        playerClassMapper = new PlayerClassMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(playerClassMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(playerClassMapper.fromId(null)).isNull();
    }
}
