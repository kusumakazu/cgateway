package com.id.kusumakazu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class WeaponTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Weapon.class);
        Weapon weapon1 = new Weapon();
        weapon1.setId(1L);
        Weapon weapon2 = new Weapon();
        weapon2.setId(weapon1.getId());
        assertThat(weapon1).isEqualTo(weapon2);
        weapon2.setId(2L);
        assertThat(weapon1).isNotEqualTo(weapon2);
        weapon1.setId(null);
        assertThat(weapon1).isNotEqualTo(weapon2);
    }
}
