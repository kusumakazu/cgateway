package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerMapperTest {

    private PlayerMapper playerMapper;

    @BeforeEach
    public void setUp() {
        playerMapper = new PlayerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(playerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(playerMapper.fromId(null)).isNull();
    }
}
