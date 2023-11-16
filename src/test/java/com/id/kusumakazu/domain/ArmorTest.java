package com.id.kusumakazu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class ArmorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Armor.class);
        Armor armor1 = new Armor();
        armor1.setId(1L);
        Armor armor2 = new Armor();
        armor2.setId(armor1.getId());
        assertThat(armor1).isEqualTo(armor2);
        armor2.setId(2L);
        assertThat(armor1).isNotEqualTo(armor2);
        armor1.setId(null);
        assertThat(armor1).isNotEqualTo(armor2);
    }
}
