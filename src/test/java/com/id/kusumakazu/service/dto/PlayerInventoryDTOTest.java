package com.id.kusumakazu.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class PlayerInventoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlayerInventoryDTO.class);
        PlayerInventoryDTO playerInventoryDTO1 = new PlayerInventoryDTO();
        playerInventoryDTO1.setId(1L);
        PlayerInventoryDTO playerInventoryDTO2 = new PlayerInventoryDTO();
        assertThat(playerInventoryDTO1).isNotEqualTo(playerInventoryDTO2);
        playerInventoryDTO2.setId(playerInventoryDTO1.getId());
        assertThat(playerInventoryDTO1).isEqualTo(playerInventoryDTO2);
        playerInventoryDTO2.setId(2L);
        assertThat(playerInventoryDTO1).isNotEqualTo(playerInventoryDTO2);
        playerInventoryDTO1.setId(null);
        assertThat(playerInventoryDTO1).isNotEqualTo(playerInventoryDTO2);
    }
}
