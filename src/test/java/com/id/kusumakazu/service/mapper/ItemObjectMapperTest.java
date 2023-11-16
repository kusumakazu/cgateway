package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemObjectMapperTest {

    private ItemObjectMapper itemObjectMapper;

    @BeforeEach
    public void setUp() {
        itemObjectMapper = new ItemObjectMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(itemObjectMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(itemObjectMapper.fromId(null)).isNull();
    }
}
