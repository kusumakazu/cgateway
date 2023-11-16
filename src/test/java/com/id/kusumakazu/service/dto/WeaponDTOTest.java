package com.id.kusumakazu.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class WeaponDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WeaponDTO.class);
        WeaponDTO weaponDTO1 = new WeaponDTO();
        weaponDTO1.setId(1L);
        WeaponDTO weaponDTO2 = new WeaponDTO();
        assertThat(weaponDTO1).isNotEqualTo(weaponDTO2);
        weaponDTO2.setId(weaponDTO1.getId());
        assertThat(weaponDTO1).isEqualTo(weaponDTO2);
        weaponDTO2.setId(2L);
        assertThat(weaponDTO1).isNotEqualTo(weaponDTO2);
        weaponDTO1.setId(null);
        assertThat(weaponDTO1).isNotEqualTo(weaponDTO2);
    }
}
