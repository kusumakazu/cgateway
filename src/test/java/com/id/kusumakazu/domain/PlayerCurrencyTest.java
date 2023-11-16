package com.id.kusumakazu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class PlayerCurrencyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlayerCurrency.class);
        PlayerCurrency playerCurrency1 = new PlayerCurrency();
        playerCurrency1.setId(1L);
        PlayerCurrency playerCurrency2 = new PlayerCurrency();
        playerCurrency2.setId(playerCurrency1.getId());
        assertThat(playerCurrency1).isEqualTo(playerCurrency2);
        playerCurrency2.setId(2L);
        assertThat(playerCurrency1).isNotEqualTo(playerCurrency2);
        playerCurrency1.setId(null);
        assertThat(playerCurrency1).isNotEqualTo(playerCurrency2);
    }
}
