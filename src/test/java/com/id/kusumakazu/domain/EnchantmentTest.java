package com.id.kusumakazu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class EnchantmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Enchantment.class);
        Enchantment enchantment1 = new Enchantment();
        enchantment1.setId(1L);
        Enchantment enchantment2 = new Enchantment();
        enchantment2.setId(enchantment1.getId());
        assertThat(enchantment1).isEqualTo(enchantment2);
        enchantment2.setId(2L);
        assertThat(enchantment1).isNotEqualTo(enchantment2);
        enchantment1.setId(null);
        assertThat(enchantment1).isNotEqualTo(enchantment2);
    }
}
