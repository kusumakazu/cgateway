package com.id.kusumakazu.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class EnchantmentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EnchantmentDTO.class);
        EnchantmentDTO enchantmentDTO1 = new EnchantmentDTO();
        enchantmentDTO1.setId(1L);
        EnchantmentDTO enchantmentDTO2 = new EnchantmentDTO();
        assertThat(enchantmentDTO1).isNotEqualTo(enchantmentDTO2);
        enchantmentDTO2.setId(enchantmentDTO1.getId());
        assertThat(enchantmentDTO1).isEqualTo(enchantmentDTO2);
        enchantmentDTO2.setId(2L);
        assertThat(enchantmentDTO1).isNotEqualTo(enchantmentDTO2);
        enchantmentDTO1.setId(null);
        assertThat(enchantmentDTO1).isNotEqualTo(enchantmentDTO2);
    }
}
