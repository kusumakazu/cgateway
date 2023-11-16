package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerDetlMapperTest {

    private PlayerDetlMapper playerDetlMapper;

    @BeforeEach
    public void setUp() {
        playerDetlMapper = new PlayerDetlMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(playerDetlMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(playerDetlMapper.fromId(null)).isNull();
    }
}
