package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EnchantmentMapperTest {

    private EnchantmentMapper enchantmentMapper;

    @BeforeEach
    public void setUp() {
        enchantmentMapper = new EnchantmentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(enchantmentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(enchantmentMapper.fromId(null)).isNull();
    }
}
