package com.id.kusumakazu.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.id.kusumakazu.web.rest.TestUtil;

public class PlayerClassDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlayerClassDTO.class);
        PlayerClassDTO playerClassDTO1 = new PlayerClassDTO();
        playerClassDTO1.setId(1L);
        PlayerClassDTO playerClassDTO2 = new PlayerClassDTO();
        assertThat(playerClassDTO1).isNotEqualTo(playerClassDTO2);
        playerClassDTO2.setId(playerClassDTO1.getId());
        assertThat(playerClassDTO1).isEqualTo(playerClassDTO2);
        playerClassDTO2.setId(2L);
        assertThat(playerClassDTO1).isNotEqualTo(playerClassDTO2);
        playerClassDTO1.setId(null);
        assertThat(playerClassDTO1).isNotEqualTo(playerClassDTO2);
    }
}
