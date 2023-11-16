package com.id.kusumakazu.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class ArmorDetlDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArmorDetlDTO.class);
        ArmorDetlDTO armorDetlDTO1 = new ArmorDetlDTO();
        armorDetlDTO1.setId(1L);
        ArmorDetlDTO armorDetlDTO2 = new ArmorDetlDTO();
        assertThat(armorDetlDTO1).isNotEqualTo(armorDetlDTO2);
        armorDetlDTO2.setId(armorDetlDTO1.getId());
        assertThat(armorDetlDTO1).isEqualTo(armorDetlDTO2);
        armorDetlDTO2.setId(2L);
        assertThat(armorDetlDTO1).isNotEqualTo(armorDetlDTO2);
        armorDetlDTO1.setId(null);
        assertThat(armorDetlDTO1).isNotEqualTo(armorDetlDTO2);
    }
}
