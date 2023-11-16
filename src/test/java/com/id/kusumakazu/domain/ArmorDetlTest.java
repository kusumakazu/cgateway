package com.id.kusumakazu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class ArmorDetlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArmorDetl.class);
        ArmorDetl armorDetl1 = new ArmorDetl();
        armorDetl1.setId(1L);
        ArmorDetl armorDetl2 = new ArmorDetl();
        armorDetl2.setId(armorDetl1.getId());
        assertThat(armorDetl1).isEqualTo(armorDetl2);
        armorDetl2.setId(2L);
        assertThat(armorDetl1).isNotEqualTo(armorDetl2);
        armorDetl1.setId(null);
        assertThat(armorDetl1).isNotEqualTo(armorDetl2);
    }
}
