package com.id.kusumakazu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class PlayerInventoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlayerInventory.class);
        PlayerInventory playerInventory1 = new PlayerInventory();
        playerInventory1.setId(1L);
        PlayerInventory playerInventory2 = new PlayerInventory();
        playerInventory2.setId(playerInventory1.getId());
        assertThat(playerInventory1).isEqualTo(playerInventory2);
        playerInventory2.setId(2L);
        assertThat(playerInventory1).isNotEqualTo(playerInventory2);
        playerInventory1.setId(null);
        assertThat(playerInventory1).isNotEqualTo(playerInventory2);
    }
}
