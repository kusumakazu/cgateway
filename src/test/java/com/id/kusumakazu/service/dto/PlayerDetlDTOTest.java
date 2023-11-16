package com.id.kusumakazu.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class PlayerDetlDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlayerDetlDTO.class);
        PlayerDetlDTO playerDetlDTO1 = new PlayerDetlDTO();
        playerDetlDTO1.setId(1L);
        PlayerDetlDTO playerDetlDTO2 = new PlayerDetlDTO();
        assertThat(playerDetlDTO1).isNotEqualTo(playerDetlDTO2);
        playerDetlDTO2.setId(playerDetlDTO1.getId());
        assertThat(playerDetlDTO1).isEqualTo(playerDetlDTO2);
        playerDetlDTO2.setId(2L);
        assertThat(playerDetlDTO1).isNotEqualTo(playerDetlDTO2);
        playerDetlDTO1.setId(null);
        assertThat(playerDetlDTO1).isNotEqualTo(playerDetlDTO2);
    }
}
