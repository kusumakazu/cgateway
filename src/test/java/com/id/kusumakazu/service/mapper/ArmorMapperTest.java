package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ArmorMapperTest {

    private ArmorMapper armorMapper;

    @BeforeEach
    public void setUp() {
        armorMapper = new ArmorMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(armorMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(armorMapper.fromId(null)).isNull();
    }
}
