package com.id.kusumakazu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class PlayerClassTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlayerClass.class);
        PlayerClass playerClass1 = new PlayerClass();
        playerClass1.setId(1L);
        PlayerClass playerClass2 = new PlayerClass();
        playerClass2.setId(playerClass1.getId());
        assertThat(playerClass1).isEqualTo(playerClass2);
        playerClass2.setId(2L);
        assertThat(playerClass1).isNotEqualTo(playerClass2);
        playerClass1.setId(null);
        assertThat(playerClass1).isNotEqualTo(playerClass2);
    }
}
