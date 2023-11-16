package com.id.kusumakazu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class WeaponDetlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WeaponDetl.class);
        WeaponDetl weaponDetl1 = new WeaponDetl();
        weaponDetl1.setId(1L);
        WeaponDetl weaponDetl2 = new WeaponDetl();
        weaponDetl2.setId(weaponDetl1.getId());
        assertThat(weaponDetl1).isEqualTo(weaponDetl2);
        weaponDetl2.setId(2L);
        assertThat(weaponDetl1).isNotEqualTo(weaponDetl2);
        weaponDetl1.setId(null);
        assertThat(weaponDetl1).isNotEqualTo(weaponDetl2);
    }
}
