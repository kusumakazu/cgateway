package com.id.kusumakazu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class PlayerDetlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlayerDetl.class);
        PlayerDetl playerDetl1 = new PlayerDetl();
        playerDetl1.setId(1L);
        PlayerDetl playerDetl2 = new PlayerDetl();
        playerDetl2.setId(playerDetl1.getId());
        assertThat(playerDetl1).isEqualTo(playerDetl2);
        playerDetl2.setId(2L);
        assertThat(playerDetl1).isNotEqualTo(playerDetl2);
        playerDetl1.setId(null);
        assertThat(playerDetl1).isNotEqualTo(playerDetl2);
    }
}
