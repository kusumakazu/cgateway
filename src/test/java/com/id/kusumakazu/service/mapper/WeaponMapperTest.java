package com.id.kusumakazu.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WeaponMapperTest {

    private WeaponMapper weaponMapper;

    @BeforeEach
    public void setUp() {
        weaponMapper = new WeaponMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(weaponMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(weaponMapper.fromId(null)).isNull();
    }
}
