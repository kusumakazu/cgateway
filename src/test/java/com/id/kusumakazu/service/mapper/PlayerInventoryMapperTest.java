package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerInventoryMapperTest {

    private PlayerInventoryMapper playerInventoryMapper;

    @BeforeEach
    public void setUp() {
        playerInventoryMapper = new PlayerInventoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(playerInventoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(playerInventoryMapper.fromId(null)).isNull();
    }
}
