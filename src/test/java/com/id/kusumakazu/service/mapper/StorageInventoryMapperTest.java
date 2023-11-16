package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StorageInventoryMapperTest {

    private StorageInventoryMapper storageInventoryMapper;

    @BeforeEach
    public void setUp() {
        storageInventoryMapper = new StorageInventoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(storageInventoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(storageInventoryMapper.fromId(null)).isNull();
    }
}
