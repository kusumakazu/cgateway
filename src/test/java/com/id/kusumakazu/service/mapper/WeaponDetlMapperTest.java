package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WeaponDetlMapperTest {

    private WeaponDetlMapper weaponDetlMapper;

    @BeforeEach
    public void setUp() {
        weaponDetlMapper = new WeaponDetlMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(weaponDetlMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(weaponDetlMapper.fromId(null)).isNull();
    }
}
