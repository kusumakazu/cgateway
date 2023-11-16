package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MasterAccountMapperTest {

    private MasterAccountMapper masterAccountMapper;

    @BeforeEach
    public void setUp() {
        masterAccountMapper = new MasterAccountMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(masterAccountMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(masterAccountMapper.fromId(null)).isNull();
    }
}
