package com.id.kusumakazu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class GameAccountTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GameAccount.class);
        GameAccount gameAccount1 = new GameAccount();
        gameAccount1.setId(1L);
        GameAccount gameAccount2 = new GameAccount();
        gameAccount2.setId(gameAccount1.getId());
        assertThat(gameAccount1).isEqualTo(gameAccount2);
        gameAccount2.setId(2L);
        assertThat(gameAccount1).isNotEqualTo(gameAccount2);
        gameAccount1.setId(null);
        assertThat(gameAccount1).isNotEqualTo(gameAccount2);
    }
}
