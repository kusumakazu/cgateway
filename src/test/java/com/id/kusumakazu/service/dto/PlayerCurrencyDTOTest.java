package com.id.kusumakazu.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class PlayerCurrencyDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlayerCurrencyDTO.class);
        PlayerCurrencyDTO playerCurrencyDTO1 = new PlayerCurrencyDTO();
        playerCurrencyDTO1.setId(1L);
        PlayerCurrencyDTO playerCurrencyDTO2 = new PlayerCurrencyDTO();
        assertThat(playerCurrencyDTO1).isNotEqualTo(playerCurrencyDTO2);
        playerCurrencyDTO2.setId(playerCurrencyDTO1.getId());
        assertThat(playerCurrencyDTO1).isEqualTo(playerCurrencyDTO2);
        playerCurrencyDTO2.setId(2L);
        assertThat(playerCurrencyDTO1).isNotEqualTo(playerCurrencyDTO2);
        playerCurrencyDTO1.setId(null);
        assertThat(playerCurrencyDTO1).isNotEqualTo(playerCurrencyDTO2);
    }
}
