package com.id.kusumakazu.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class WeaponDetlDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WeaponDetlDTO.class);
        WeaponDetlDTO weaponDetlDTO1 = new WeaponDetlDTO();
        weaponDetlDTO1.setId(1L);
        WeaponDetlDTO weaponDetlDTO2 = new WeaponDetlDTO();
        assertThat(weaponDetlDTO1).isNotEqualTo(weaponDetlDTO2);
        weaponDetlDTO2.setId(weaponDetlDTO1.getId());
        assertThat(weaponDetlDTO1).isEqualTo(weaponDetlDTO2);
        weaponDetlDTO2.setId(2L);
        assertThat(weaponDetlDTO1).isNotEqualTo(weaponDetlDTO2);
        weaponDetlDTO1.setId(null);
        assertThat(weaponDetlDTO1).isNotEqualTo(weaponDetlDTO2);
    }
}
