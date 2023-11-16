package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ArmorDetlMapperTest {

    private ArmorDetlMapper armorDetlMapper;

    @BeforeEach
    public void setUp() {
        armorDetlMapper = new ArmorDetlMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(armorDetlMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(armorDetlMapper.fromId(null)).isNull();
    }
}
